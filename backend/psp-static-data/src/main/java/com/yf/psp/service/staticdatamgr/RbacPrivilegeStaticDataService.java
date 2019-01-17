package com.yf.psp.service.staticdatamgr;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import com.yf.psp.staticdata.base.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yf.psp.db.postgres.rbac.RbacPrivilege;
import com.yf.psp.db.postgres.rbac.dao.RbacPrivilegeDao;


@Service
public class RbacPrivilegeStaticDataService extends ServiceBase<RbacPrivilege> {

	private Map<Integer,RbacPrivilege> mapD = null;
	

	@Autowired
    private RbacPrivilegeDao rbacPrivilegeDao;
	
	
	@Override
	public List<RbacPrivilege> getListFromDb() { 
		return rbacPrivilegeDao.select();
	}
	

	@Override
	public void afterGetListFromDb() {
		Map<Integer,RbacPrivilege> map = new  HashMap<>();
		List<RbacPrivilege> list = getList();
		for(RbacPrivilege it:list){
		 	map.put(it.getPrivilegeId(), it);
		}
		mapD = map;
	}

	public RbacPrivilege get(Integer privilegeId){
		return mapD.get(privilegeId);
	}


	@Override
	public void clear() {
		this.mapD = null;//for gc
	}
 

}
