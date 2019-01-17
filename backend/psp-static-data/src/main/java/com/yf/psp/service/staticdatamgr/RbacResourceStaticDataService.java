package com.yf.psp.service.staticdatamgr;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import com.yf.psp.staticdata.base.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yf.psp.db.postgres.rbac.RbacResource;
import com.yf.psp.db.postgres.rbac.dao.RbacResourceDao;


@Service
public class RbacResourceStaticDataService extends ServiceBase<RbacResource> {

	private Map<Integer/*resid*/,RbacResource> mapD = null;
	
	private Map<String/*res*/,RbacResource> mapS = null;

	@Autowired
    private RbacResourceDao rbacResourceDao;
	
	
	@Override
	public List<RbacResource> getListFromDb() { 
		return rbacResourceDao.select();
	}
	

	@Override
	public void afterGetListFromDb() {
		Map<Integer,RbacResource> map = new  HashMap<>();
		Map<String,RbacResource> map1 = new  HashMap<>();
		List<RbacResource> list = getList();
		for(RbacResource it:list){
		 	map.put(it.getResId(), it);
		 	map1.put(it.getRes(), it);
		}
		for(RbacResource it:list){
		 	if(it.getParentResId()!=null) {
		 		RbacResource parent = map.get(it.getParentResId());
		 		parent.getChildren().add(it);
		 		it.setParentRes(parent);
		 	}  
		}
		mapD = map;
		mapS = map1;
	}

	public RbacResource get(String res){
		return mapS.get(res);
	}
	
	public RbacResource get(Integer resId){
		return mapD.get(resId);
	}

	@Override
	public void clear() { 
	}
 

}
