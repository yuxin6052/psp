package com.yf.psp.db.postgres.rbac.dao;
 
 
 
import java.util.List;

import com.yf.psp.db.postgres.rbac.RbacPrivilege;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RbacPrivilegeDao {
	 
	public List<RbacPrivilege> select();
	
}
