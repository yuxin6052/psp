package com.yf.psp.service.staticdatamgr;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import com.yf.psp.staticdata.base.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yf.psp.db.postgres.rbac.RbacRole;
import com.yf.psp.db.postgres.rbac.dao.RbacRoleDao;


@Service
public class RbacRoleStaticDataService extends ServiceBase<RbacRole> {

	private Map<String/*roleName*/,RbacRole> mapD = null;
	

	@Autowired
    private RbacRoleDao rbacRoleDao;
	
	
	@Override
	public List<RbacRole> getListFromDb() { 
		return rbacRoleDao.select();
	}
	

	@Override
	public void afterGetListFromDb() {
		Map<String,RbacRole> map = new  HashMap<>();
		List<RbacRole> list = getList();
		for(RbacRole it:list){
		 	map.put(it.getRoleName(), it);
		}
		mapD = map;
	}

	public RbacRole get(String roleName){
		return mapD.get(roleName);
	}


	@Override
	public void clear() {
		
	}
 

}
