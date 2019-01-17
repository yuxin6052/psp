package com.yf.psp.staticdata.base;


import java.util.List;

/**
 * 功能:作为所有静态数据服务提供类的父类
 * @author WangTao
 * 2017-6-7 09:07:51
 * @param <T>
 */
public abstract class ServiceBase<T> {
	private String tableName;
	  
	/**
	 * 本数据表在整个静态数据加载过程的顺序
	 */
	private Integer order;
	
	public Integer getOrder() {
		return order;
	}


	public void setOrder(Integer order) {
		this.order = order;
	}


	 

	private List<T> list;
	
	public String getTableName() {
		return tableName;
	}
	
	 
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;//此处涉及到java对象赋值操作的原子性，是否需要加锁，有待理论考证,  阅读了jvm官方解释，本操作是原子性的 ，另外从cpu cache 原理也是解释的通的，2017-12-1 16:21:14
	}

	public abstract List<T> getListFromDb();
	
	public abstract void afterGetListFromDb();
	
	//完成一次更新后清理临时数据等操作
	public abstract void clear();
	
	public void doJob(){
		List<T> lt = getListFromDb();
		this.setList(lt);
		afterGetListFromDb();
	}
}
