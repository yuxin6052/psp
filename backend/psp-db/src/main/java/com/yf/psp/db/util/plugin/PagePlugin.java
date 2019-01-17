package com.yf.psp.db.util.plugin;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
   
/**
 * 
* 类名称：分页插件
* 类描述： 
* @author HoneyWell
*/
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class,Integer.class})})
public class PagePlugin implements Interceptor {

	private   String dialect = "";	//数据库方言
	private   String pageSqlId = ""; //mapper.xml中需要拦截的ID(正则匹配)
	 
	private static final String DIALECT_DRILL = "drill";	
	private static final String DIALECT_POSTGRES = "postgresql";	
	
	void getCountIfNecessary(Page page,Connection connection,MappedStatement mappedStatement,BoundSql boundSql) throws SQLException {
		if(page==null){
			return ;
		}
		if(page.isNeedCount()){
			String sql = boundSql.getSql();
			//String countSql = "select count(0) from (" + sql+ ") as tmp_count"; //记录统计
			String fhsql = sql;
			String countSql = "select count(0) from (" + fhsql+ ")  tmp_count"; //记录统计 == oracle 加 as 报错(SQL command not properly ended)
			PreparedStatement countStmt = null;
			ResultSet rs = null;
			try {
				countStmt = connection.prepareStatement(countSql);
				BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(),countSql,boundSql.getParameterMappings(),boundSql.getParameterObject());
				setParameters(countStmt,mappedStatement,countBS,boundSql.getParameterObject());
				rs = countStmt.executeQuery();
				Long count = 0L;
				if (rs.next()) {
					count = rs.getLong(1);
				}
				page.setTotal(count);
			}finally {
				try {
					if(rs!=null) {
						rs.close();
					}
				}finally {
					if(countStmt!=null) {
						countStmt.close();
					}
				}
			}
		}
	}
	
	public Object intercept(Invocation ivk) throws NoSuchFieldException, IllegalAccessException, SQLException, InvocationTargetException {
		if(ivk.getTarget() instanceof StatementHandler){
			StatementHandler statementHandler = (StatementHandler)ivk.getTarget();
			StatementHandler delegate = (StatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");
			if(DIALECT_DRILL.equals(dialect)) {
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();
				SqlInjectCheck.check(parameterObject);
			}
			if(mappedStatement.getId().matches(pageSqlId)){ //拦截需要分页的SQL
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();//分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
				if(parameterObject==null){
					throw new NullPointerException("parameterObject尚未实例化！");
				}else{
					Connection connection = (Connection) ivk.getArgs()[0];
					String sql = boundSql.getSql();
					 
					Page page = null;
					if(parameterObject instanceof Page){	//参数就是Page实体
						 page = (Page) parameterObject;	 
						 this.getCountIfNecessary(page,connection,mappedStatement,boundSql); 
					}else{	//参数为某个实体，该实体拥有Page属性
						Field pageField = ReflectHelper.getFieldByFieldName(parameterObject,"page");
						if(pageField!=null){
							page = (Page) ReflectHelper.getValueByFieldName(parameterObject,"page");
							if(page==null){
								page = new Page();
							}
							 this.getCountIfNecessary(page,connection,mappedStatement,boundSql); 
							ReflectHelper.setValueByFieldName(parameterObject,"page", page);  
						}else{
							throw new NoSuchFieldException(parameterObject.getClass().getName()+"不存在 page 属性！");
						}
					}
					String pageSql = generatePageSql(sql,page);
					ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql); //将分页sql语句反射回BoundSql.
				}
			}
		}
		return ivk.proceed();
	}

	
	/**
	 * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	private void setParameters(PreparedStatement ps,MappedStatement mappedStatement,BoundSql boundSql,Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null: configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter "+ propertyName + " of statement "+ mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}
	
	/**
	 * 根据数据库方言，生成特定的分页sql
	 * @param sql
	 * @param page
	 * @return
	 */
	private static final String LIMIT = " limit ";
	private static final String OFFSET = " offset ";
	private static final String WHERE = " where ";
	private static final String ORDER_BY = " order by ";
	private String generatePageSql(String sql,Page page){
		if(page!=null && Tools.notEmpty(dialect)){
			StringBuilder pageSql = new StringBuilder();
			pageSql.append("select * from ("+sql+")  AS df");
			if(DIALECT_DRILL.equals(dialect)){
				if(page instanceof PageByPageNo) {
					PageByPageNo pageB = (PageByPageNo)page;
					long total = pageB.getTotal();
					long pageSize = pageB.getPageSize();
					long pageNum = total/pageSize + (total%pageSize>0? 1 : 0);//数据库实际页数
					long pageNo = pageB.getPageNo();
					pageNo =  pageNo > 0? (pageNo - 1) : (pageNum + pageNo)  ;
					long offset = pageSize*pageNo; 
					if(pageB.getOrderBy()!=null) {
						pageSql.append(ORDER_BY + pageB.getOrderBy());
					}
					pageSql.append(LIMIT+pageB.getPageSize() + OFFSET + offset); 
				} 
			}else if(DIALECT_POSTGRES.equals(dialect)){
				if(page instanceof PageByInstId) {
					PageByInstId pageB = (PageByInstId)page;
					if (pageB.isDesc()){
						if (pageB.getStartId() == -1){
							pageSql.append(WHERE+pageB.getPrimaryKey()+ " >= 0" +LIMIT+pageB.getPageSize());
						}
						else{
							pageSql.append(WHERE+pageB.getPrimaryKey()+ " < " + pageB.getStartId()+LIMIT+pageB.getPageSize());
						}
					}
					else{
						pageSql.append(WHERE+pageB.getPrimaryKey()+ " > " + pageB.getStartId()+LIMIT+pageB.getPageSize());
					}
				}else if(page instanceof PageByPageNo) {
					PageByPageNo pageB = (PageByPageNo)page;
					if(pageB.getOrderBy()!=null) {
						pageSql.append(ORDER_BY + pageB.getOrderBy());
					}
					Integer pageSize = pageB.getPageSize();
					Integer pageNo = pageB.getPageNo();
					if(pageSize==null||pageNo==null) {
						return pageSql.toString();
					}
					long total = pageB.getTotal();
					
					long pageNum = (long)(total/pageSize + (total%pageSize>0? 1 : 0));//数据库实际页数
					
					pageNo =  (int)(pageNo > 0? (pageNo - 1) : (pageNum + pageNo))  ;
					long offset = pageSize*pageNo; 
					
					pageSql.append(LIMIT+pageB.getPageSize() + OFFSET + offset); 
				}
			}
			return pageSql.toString();
		}else{
			return sql;
		}
	}
	
	@Override
	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	@Override
	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		if (Tools.isEmpty(dialect)) {
			try {
				throw new PropertyException("dialect property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
		pageSqlId = p.getProperty("pageSqlId");
		if (Tools.isEmpty(pageSqlId)) {
			try {
				throw new PropertyException("pageSqlId property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
	}
	
}

