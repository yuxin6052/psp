package com.yf.psp.init;

import java.io.IOException;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.yf.psp.common.login.UserSession;
import com.yf.psp.staticdata.rbac.RoleService;

import io.jsonwebtoken.Claims; 

  

 
public class UrlAccessFilter extends BasicAuthenticationFilter  {
	private final static Logger logger = LoggerFactory.getLogger(UrlAccessFilter.class);
	private RoleService roleService;

	 public UrlAccessFilter(AuthenticationManager authenticationManager,RoleService roleService) {
	        super(authenticationManager);
	        this.roleService = roleService;
	    }

	
	 @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain chain) throws IOException, ServletException {

		   if(AnonymousUrlInfo.canAccess(request.getRequestURI())){
				//所有用户都拥有登陆登出权限，在 SecurityConfig。configure(HttpSecurity http)
				// 方法里面尝试多次，总是存在 被 /** 覆盖的情况, 所以在此实现
				chain.doFilter(request,response);
	            return;
			}
		 
	        String tokenHeader = request.getHeader(JwtHelper.JWT_HTTP_HEADER_AUTHORIZATION);
	        // 如果请求头中没有Authorization信息则直接放行了
	        if (tokenHeader == null || !tokenHeader.startsWith(JwtHelper.JWT_TOKEN_PREFIX)) {
	        	HttpServletResponse rsp = (HttpServletResponse)response;
	        	rsp.sendError(HttpServletResponse.SC_FORBIDDEN);
	        	return;
	        }
	        // 如果请求头中有token，则进行解析，并且设置认证信息
	        UsernamePasswordAuthenticationToken authentication = getAuthentication(tokenHeader);
	        if(authentication != null) {
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	        	String role = "";
	 	        for (GrantedAuthority authority : authentication.getAuthorities()){
	 	            role = authority.getAuthority();
	 	        }
	 	       
	 	        if(roleService.canAccessUrl(role, request.getRequestURI())) {
	 	        	chain.doFilter(request,response);
	 	        }else {
	 	        	HttpServletResponse rsp = (HttpServletResponse)response;
		        	rsp.sendError(HttpServletResponse.SC_FORBIDDEN);
	 	        }
	        }else {
	        	HttpServletResponse rsp = (HttpServletResponse)response;
	        	rsp.sendError(HttpServletResponse.SC_FORBIDDEN);
	        }
	    }
	 
	 private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
	        String token = tokenHeader.substring(JwtHelper.JWT_TOKEN_PREFIX.length());
	        Claims claims = JwtHelper.parseJWT(token, JwtHelper.BASE64SECURITY);
	        Map<String,Object> obj =  (Map<String, Object>)claims.get(JwtHelper.JWT_ATTR);
	        UserSession us = new UserSession(Long.parseLong(obj.get("userId").toString()) ,obj.get("userName").toString(),obj.get("role").toString(),Long.parseLong(obj.get("tenantId").toString()));
	        if (us != null){
	            return new UsernamePasswordAuthenticationToken(us, null, Arrays.asList(new SimpleGrantedAuthority(us.getRole())));
	        }
	        return null;
	    }
}
