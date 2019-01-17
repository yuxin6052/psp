package com.yf.psp.db.json;
/**
 * postgres json 字段对应的java 实体类 父接口
 * @author wangtao
 *
 */
public interface MyJsonBase {
	public String toJsonStr(); 
	
	public MyJsonBase fromJsonStr(String json);
}
