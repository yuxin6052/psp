package com.yf.psp.staticdata.base;

import org.springframework.beans.factory.annotation.Autowired;

 

/**
 * 功能:作为所有静态数据服务提供类的父类
 * @author WangTao
 * 2017-6-7 09:07:51
 * @param <T>
 */
public abstract class DbServiceBase<T,D>  extends ServiceBase<T> {
	@Autowired
    protected D dao;

	public D getDao() {
		return dao;
	}

	public void setDao(D dao) {
		this.dao = dao;
	}
}
