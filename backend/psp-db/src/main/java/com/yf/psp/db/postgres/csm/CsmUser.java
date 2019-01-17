package com.yf.psp.db.postgres.csm;


import java.sql.Timestamp;
 
 

/**
 * Model class of 用户.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class CsmUser  {

	/** 用户id. */
	private Long userId;

	/** 用户名. */
	private String userName;

	/** password. */
	private String password;

	/** email. */
	private String email;

	/** 租户. */
	private Long tenantId;

	/** 角色表. */
	private String roleName;

	/** delete_flag. */
	private Short deleteFlag;

	/** delete_time. */
	private Timestamp deleteTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Short getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Short deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Timestamp getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Timestamp deleteTime) {
		this.deleteTime = deleteTime;
	}

	
	  
 

}