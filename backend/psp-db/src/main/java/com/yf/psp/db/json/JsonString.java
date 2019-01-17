package com.yf.psp.db.json;


/**
 * json string
 * @author wangtao
 *
 */
public class JsonString implements MyJsonBase{
	
	String json;
	
	
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	@Override
	public String toJsonStr() {
		return json;
	}

	@Override
	public MyJsonBase fromJsonStr(String json) {
		this.json = json;
		return this;
	}

}
