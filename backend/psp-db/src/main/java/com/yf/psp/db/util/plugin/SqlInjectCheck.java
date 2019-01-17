package com.yf.psp.db.util.plugin;

import java.util.Collection;
import java.util.Map;

public class SqlInjectCheck {
	/***
	 *  @完成参数校验
	 */
	public static void check(Object param) {
		if(param==null) {
			return ;
		}
		if(param instanceof Map.Entry) {
			Map.Entry en = (Map.Entry)param;
			check(en.getKey());
			check(en.getValue());
		}else if(param instanceof Map) {
			Map<String,Object> map = (Map<String,Object>)param;
			for(Map.Entry<String,Object> it : map.entrySet()) {
				check(it);
			}
		}else if (param instanceof String) {
			checkString((String) param);
		}else if (param instanceof Page) {
			checkPage((Page) param);
		}else if(param instanceof Collection) {
			Collection cl = (Collection)param;
			for(Object it : cl) {
				check(it);
			}
		}
		
		//...
	}
	
	private  static void checkString(String param) {
		//TODO:根据业务一步步来完善 检查 逻辑
		if(param.contains("'")) { 
			throw new IllegalArgumentException(param + " has sql inject risk");
		}
	}
	
	private  static void checkPage(Page param) {
		check(param.getParams()); 
		if (param instanceof PageByPageNo) { 
		}else if(param instanceof PageByInstId) {
			PageByInstId page = (PageByInstId)param; 
			if(page.getPrimaryKey()!=null && page.getPrimaryKey().trim().contains(" ")) {
				throw new IllegalArgumentException(page.getPrimaryKey() + " has sql inject risk");
			}
		} 
	}
}
