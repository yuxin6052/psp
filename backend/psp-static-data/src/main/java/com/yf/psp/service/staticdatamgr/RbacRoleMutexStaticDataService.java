package com.yf.psp.service.staticdatamgr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yf.psp.staticdata.base.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yf.psp.db.postgres.rbac.RbacRoleMutex;
import com.yf.psp.db.postgres.rbac.dao.RbacRoleMutexDao;


@Service
public class RbacRoleMutexStaticDataService extends ServiceBase<RbacRoleMutex> {

	private Map<Integer,Set<String>> mapD = null;
	

	@Autowired
    private RbacRoleMutexDao rbacRoleMutexDao;
	
	
	@Override
	public List<RbacRoleMutex> getListFromDb() { 
		return rbacRoleMutexDao.select();
	}
	

	@Override
	public void afterGetListFromDb() {
		Map<Integer,Set<String>> map = new  HashMap<>();
		List<RbacRoleMutex> list = getList();
		for(RbacRoleMutex it:list){
			Set<String> set = map.get(it.getRoleMutexId());
			if(set==null) {
				set = new HashSet<>();
				map.put(it.getRoleMutexId(), set);
			}
			set.add(it.getRoleName());
		}
		mapD = map;
	}

	Map<Integer,Set<String>> getMapD(){
		return this.mapD;
	}
	

	@Override
	public void clear() {
		this.mapD = null;//for gc
	}
 

}
