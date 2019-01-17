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
 * postgres json 字段转换类, 不同于JsonTypeHandler，本类用于直接序列号和反序列化
 * @author Wangtao
 *
 * @param <T>
 */
@MappedJdbcTypes(JdbcType.OTHER) // 可有可无
//@MappedTypes(T.class) 
public class DirectJsonTypeHandler<T> extends BaseTypeHandler<T> {
   
	Class<T> clazz;
	
	public DirectJsonTypeHandler(Class<T> clazz){
		this.clazz = clazz;
	}
	
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
    	if(parameter==null){
    		return;
    	}
    	String jsonStr = JsonUtil.toJsonString(parameter);
    	PGobject jsonObject = new PGobject();
        jsonObject.setType("json");
        jsonObject.setValue(jsonStr);
        ps.setObject(i, jsonObject);
    }
 
    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    	String str =  rs.getString(columnIndex);
    	if(str==null||str.length()<4){
    		return null;
    	}
    	return JsonUtil.toObject(str, clazz); 
    }
 
	@Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    	String str =  cs.getString(columnIndex);
    	if(str==null||str.length()<4){
    		return null;
    	}
    	return JsonUtil.toObject(str, clazz); 
    }
 
    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
    	String str =  rs.getString(columnName);
    	if(str==null||str.length()<4){
    		return null;
    	}
    	return JsonUtil.toObject(str, clazz); 
    }

}