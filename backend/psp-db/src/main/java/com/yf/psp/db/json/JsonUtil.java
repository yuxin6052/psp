package com.yf.psp.db.json;

import java.io.IOException; 
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
  

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 返回前端的数据的工具类。
 *
 */
public class JsonUtil {


	private static ObjectMapper mapper = new  ObjectMapper();

	static {
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
	}

	public static String toJsonString(Object object){
		String str = null;
		try {
			str = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	public static <T> T toObject(String json,Class<T> clazz){
		try {
			return (T)mapper.readValue(json.getBytes("UTF-8"), clazz);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取可传输的 json value 的字符串
	 * @param value
	 * @return
	 */
	public static String getJsonValueStr(String value){
		if(value==null){
			return null;
		}
		Map<String,String> tmp = new HashMap<>();
		tmp.put("T", value);
		String str = toJsonString(tmp );
		//{"T":""}
		//System.out.println(str);
		if(str != null) {
			int len = str.length();
			str = str.substring(6,len-2);
		}
		return str;
	}

	private static class TimestampConvert{
		Timestamp tm;

		public Timestamp getTm() {
			return tm;
		}

		public void setTm(Timestamp tm) {
			this.tm = tm;
		}
	}

	public static Timestamp toTimestamp(String tm){
		String tmp =  "{\"tm\":\""+ tm +  "\"}";
		TimestampConvert tc;
		try {
			tc = mapper.readValue(tmp, TimestampConvert.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return tc.getTm();
	}

	 


}
