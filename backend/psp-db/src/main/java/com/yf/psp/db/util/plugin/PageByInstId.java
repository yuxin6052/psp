package com.yf.psp.db.util.plugin;

 

public class PageByInstId extends Page { 
	private Long startId;
	private String primaryKey;
	private boolean isDesc = true;
	public boolean isDesc() {return isDesc;}
	public void setDesc(boolean desc) {isDesc = desc;}

	public Long getStartId() {
		return startId;
	}
	public void setStartId(Long startId) {
		this.startId = startId;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	

}
