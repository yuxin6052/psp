package com.yf.psp.db.json;

import java.sql.CallableStatement; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes; 
import org.postgresql.util.PGobject;
 
/**
 * postgres json 字段转换类,不同于DirectJsonTypeHandler，本类用于将数据实体压缩 key 存储
 * @author Wangtao
 *
 * @param <T>
 */
@MappedJdbcTypes(JdbcType.OTHER) // 可有可无
//@MappedTypes(T.class) 
public class JsonTypeHandler<T extends MyJsonBase> extends BaseTypeHandler<T> {
   
	Class<T> clazz;
	
	public JsonTypeHandler(Class<T> clazz){
		this.clazz = clazz;
	}
	
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
    	if(parameter==null){
    		return;
    	}
    	PGobject jsonObject = new PGobject();
        jsonObject.setType("json");
        jsonObject.setValue(parameter.toJsonStr());
        ps.setObject(i, jsonObject);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    	String str =  rs.getString(columnIndex);
    	if(str==null||str.length()<4){
    		return null;
    	}
    	try {
			T ret = clazz.newInstance();
	    	return (T)ret.fromJsonStr(str);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    @SuppressWarnings("unchecked")
	@Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    	String str =  cs.getString(columnIndex);
    	if(str==null||str.length()<4){
    		return null;
    	}
    	try {
			T ret = clazz.newInstance();
	    	return (T)ret.fromJsonStr(str);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
    	String str =  rs.getString(columnName);
    	if(str==null||str.length()<4){
    		return null;
    	}
    	try {
			T ret = clazz.newInstance();
	    	return (T)ret.fromJsonStr(str);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

}