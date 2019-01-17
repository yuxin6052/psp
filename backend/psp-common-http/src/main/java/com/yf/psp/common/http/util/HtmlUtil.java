package com.yf.psp.common.http.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

 

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper; 

/**
 * 返回前端的数据的工具类。
 * 
 */
public class HtmlUtil {
	
	
	private static ObjectMapper mapper = new  ObjectMapper();
	
	static {
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>输出json格式<br>
	 * @param response
	 * @param jsonStr
	 * @throws Exception
	 */
	public static void writerJson(HttpServletResponse response,String jsonStr) {
			writer(response,jsonStr);
	}
	
	public static void writerJson(HttpServletResponse response,Object object)  {
			try {
				response.setContentType("application/json");
				String str = mapper.writeValueAsString(object);
				writer(response,/*JSONUtil.toJSONString(object)*/str);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
	}
	
	
	public static void wirteSuccess(HttpServletResponse response,Object datas){
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("Datas", datas);
		//jsonMap.put("Message", message); 
		writerJson(response,jsonMap);
	}
	
//	public static void wirteFailure(HttpServletResponse response,String message){
//		Map<String,Object> jsonMap = new HashMap<String,Object>();
//		jsonMap.put("Success", false);
//		jsonMap.put("Message", message);  
//		writerJson(response,jsonMap);
//	}	
	
	/**
	 * 国际化的消息
	 * @param response
	 * @param message
	 * @param status
	 */
	public static void wirteFailureWithStatus(HttpServletResponse response,String message, int status){
		response.setStatus(500);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("ErroCode",status);
		jsonMap.put("ErroMsg",message);  
		writerJson(response,jsonMap);
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>输出HTML代码<br>
	 * @param response
	 * @param htmlStr
	 * @throws Exception
	 */
	public static void writerHtml(HttpServletResponse response,String htmlStr) {
		writer(response,htmlStr);
	}
	
	private static void writer(HttpServletResponse response,String str){
		try {
			StringBuffer result = new StringBuffer();
			//设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out= null;
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writerJsonWithStatus(HttpServletResponse response, Object json, int status) {
		try {
			response.setStatus(status);
			response.setContentType("application/json");
			HashMap retMap = new HashMap();
			retMap.put("errorCode", 0);
			retMap.put("errorMsg", "Success");
			retMap.put("data", json);
			String str = mapper.writeValueAsString(retMap);
			writer(response,/*JSONUtil.toJSONString(object)*/str);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
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
		if(null != str) {
			int len = str.length();
			str = str.substring(6,len-2);
		}
		return str;
		
	}
}
