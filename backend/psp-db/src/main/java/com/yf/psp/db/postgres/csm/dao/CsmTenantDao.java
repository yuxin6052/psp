package com.yf.psp.db.postgres.csm.dao;
 
 
  

import com.yf.psp.db.postgres.csm.CsmTenant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface CsmTenantDao {
 
	CsmTenant getByTenantId(@Param("tenantId")Long tenantId);
	

 
	

	  
 
	 
}

