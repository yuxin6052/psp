package com.yf.psp.db.util.plugin;

 
public class PageByPageNo extends Page { 
	//从 1 开始
	private Integer pageNo;
	
	private String orderBy;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}


	
	

}
