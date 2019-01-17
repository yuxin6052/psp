package com.yf.psp.init;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yf.psp.db.postgres.csm.CsmUser;

public class User implements UserDetails {

    private static final long serialVersionUID = 1L;
    
 
     

    private String username; 
    private String email; 
    private String password;

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    private String tenantName;

    //下面是业务自带的模型属性
    private Long userId; 
	private String roleName;
	private Long tenantId;
 

    public User(CsmUser u) {
 
        this.email = u.getEmail();
        this.username = u.getUserName();
        this.userId=u.getUserId();
 
        this.tenantId = u.getTenantId();
        this.roleName = u.getRoleName();
        this.password = u.getPassword();
    }

  

	 



 


 


	public String getRoleName() {
		return roleName;
	}



	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}



	public Long getTenantId() {
		return tenantId;
	}



	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}


 


	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	@Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(/*"ROLE_USER"*/  this.roleName)); //ROLE_ 前缀是springsecurity 自定义的
    }

 
 



 
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }













	public Long getUserId() {
		return userId;
	}













	public void setUserId(Long userId) {
		this.userId = userId;
	}













	public void setPassword(String password) {
		this.password = password;
	}

}
