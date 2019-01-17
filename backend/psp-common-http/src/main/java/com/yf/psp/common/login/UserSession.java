package com.yf.psp.common.login;

import java.io.Serializable;

/**
 * Created by H154651 on 6/23/2017.
 */
public class UserSession implements Serializable {
 
	private static final long serialVersionUID = 1L;
	Long userId;
	String userName;
	String role;
	Long tenantId;
 

 


	public Long getTenantId() {
		return tenantId;
	}


	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}


	public UserSession(Long userId, String userName,String role, Long tenantId  ) {
		this.tenantId = tenantId;
		this.userId = userId;
		this.userName = userName;
		this.role = role;
	 
		 
	}

	
	public UserSession() {
		// TODO Auto-generated constructor stub
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


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

 

}
