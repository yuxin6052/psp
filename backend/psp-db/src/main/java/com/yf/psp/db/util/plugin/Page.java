package com.yf.psp.db.util.plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page {
	private Long total;
	private Integer pageSize;
	//private String primaryKey;
/*	private Long startId;*/
	private Map <String,Object> params=new HashMap<String,Object>();
	private List<?> result;
	
	
	private boolean needCount = true;
	
	public boolean isNeedCount() {
		return needCount;
	}

	public void setNeedCount(boolean needCount) {
		this.needCount = needCount;
	}

	public void  setParam(String key,Object value){
		params.put(key, value);
	}
	
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public List<?> getResult() {
		return result;
	}
	public void setResult(List<?> result) {
		this.result = result;
	}
	/*public Long getStartId() {
		return startId;
	}
	public void setStartId(Long startId) {
		this.startId = startId;
	}*/
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
/*	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}*/
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}




	
	

}
