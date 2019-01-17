package com.yf.psp.db.postgres.csm.dao;
 
 
 
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yf.psp.db.postgres.DeleteFlag;
import com.yf.psp.db.postgres.csm.CsmUser;


@Mapper
public interface CsmUserDao {
 
	CsmUser getByUserId(@Param("userId")Long userId);
	

	CsmUser getByUsername(@Param("username")String username);
	

	  
 
	
	Long updateByUserId(CsmUser param);
	
	Long insert(CsmUser param) throws SQLException;
	
	default Long deleteByUserId(Long userId) {
		CsmUser user = new CsmUser();
		user.setUserId(userId);
		user.setDeleteFlag(DeleteFlag.Y);
		user.setDeleteTime(new Timestamp(System.currentTimeMillis()));
		return this.updateByUserId(user);
	}
}

