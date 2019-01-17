package com.yf.psp.service.staticdatamgr;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;


import com.yf.psp.staticdata.base.StaticDataMgrBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yf.psp.db.postgres.rbac.RbacPrivilege;
import com.yf.psp.db.postgres.rbac.RbacResource;
import com.yf.psp.db.postgres.rbac.RbacRole;


/**
 * 
 * @author WangTao
 * 静态数据模型建立，非常重要，这里错了，后续业务全错
 * 
 */
@Service
public class StaticDataMgr extends StaticDataMgrBase {

	public static Logger logger = LoggerFactory.getLogger(StaticDataMgr.class);
	 
	
	public void initByYou(){
		
	}
	
	private final static Logger log = LoggerFactory.getLogger(StaticDataMgr.class);

	 

    @Autowired RbacRoleStaticDataService  rbacRoleStaticDataService;
	@Autowired RbacResourceStaticDataService  rbacResourceStaticDataService;
	@Autowired RbacPrivilegeStaticDataService  rbacPrivilegeStaticDataService;
	@Autowired RbacRoleMutexStaticDataService rbacRoleMutexStaticDataService;

	@PostConstruct
	public void init(){

		addServiceBase("tbl_rbac_role"    		      ,       rbacRoleStaticDataService                           ,  9   );  
		addServiceBase("tbl_rbac_resource"    		       ,  rbacResourceStaticDataService                       ,  10  ); 
		addServiceBase("tbl_rbac_privilege"    		       ,  rbacPrivilegeStaticDataService                      ,  11  ); 
		addServiceBase("tbl_rbac_mutex"    		           ,  rbacRoleMutexStaticDataService                      ,  12  ); 

		run(); 
	}

	 

	 

	/**
	 * 在此完成模型建立,此处的模型建立逻辑在配置出错的时候可能会有问题，有的地方写反了，需要修改，现在没时间，wangtao 2017-7-22 13:09:34
	 */
	@Override
	public void afterGetAllTables() {
		buildRbacPrivilege_RbacResource_Relation();
		buildRbacMutex();
		
 

		 
	}
	
	
	private void buildRbacPrivilege_RbacResource_Relation(){
		List<RbacPrivilege> listD =  this.rbacPrivilegeStaticDataService.getList();
		for(RbacPrivilege it : listD){ 
			RbacResource res = this.rbacResourceStaticDataService.get(it.getResId());
			if(res==null){
				log.error("the res id {} of privilege {} not exist ",it.getResId(),it.getPrivilegeId()); 
			}else{
				it.setRbacResource(res); 
				RbacRole role = this.rbacRoleStaticDataService.get(it.getRoleName());
				if(role==null) {
					log.error("the rolename  {} of privilege {} not exist ",it.getRoleName(),it.getPrivilegeId()); 
				}else{
				    role.getResIdPrivileges().put(res.getResId(), it);
					role.addOneResource(res.getResType(), it); 
				}
			}
			
		}
		
		
	}
	
	private void buildRbacMutex(){
		Map<Integer,Set<String>> map = rbacRoleMutexStaticDataService.getMapD();
		for(Map.Entry<Integer,Set<String>> it : map.entrySet()) {
			for(String roleName : it.getValue()) {
				RbacRole role = this.rbacRoleStaticDataService.get(roleName);
				if(role==null) {
					log.error("the rolename  {} not exist ,mutexid {} ",roleName,it.getKey()); 
				}else {
					role.getRoleMutex().put(it.getKey(), it.getValue());
				}
			}
		}
	}
	
	
 


 
 
}
