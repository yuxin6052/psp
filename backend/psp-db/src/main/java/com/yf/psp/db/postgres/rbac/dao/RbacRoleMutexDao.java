package com.yf.psp.db.postgres.rbac.dao;
 
 
 
import java.util.List; 

import org.apache.ibatis.annotations.Mapper;

import com.yf.psp.db.postgres.rbac.RbacRoleMutex;

 

@Mapper
public interface RbacRoleMutexDao {
	 
	public List<RbacRoleMutex> select();  
	
}
