package com.yf.psp.init;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yf.psp.common.login.UserSession;
import com.yf.psp.service.staticdatamgr.RbacRoleStaticDataService;
import com.yf.psp.staticdata.rbac.RoleService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter  {
     
	   private AuthenticationManager authenticationManager;
	   private RoleService roleService;
	   private RbacRoleStaticDataService rbacRoleStaticDataService;
 
	   
	    public JwtAuthenticationTokenFilter(AuthenticationManager authenticationManager, RoleService roleService, RbacRoleStaticDataService rbacRoleStaticDataService) {
	        this.authenticationManager = authenticationManager;
	        this.roleService = roleService;
	        this.rbacRoleStaticDataService = rbacRoleStaticDataService;
	        super.setFilterProcessesUrl("/tenant/v1/user/login");
	    }

	    @Override
	    public Authentication attemptAuthentication(HttpServletRequest request,
	                                                HttpServletResponse response) throws AuthenticationException {

	        // 从输入流中获取到登录的信息
	        try {
	            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
	            return authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
	            );
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    // 成功验证后调用的方法
	    // 如果验证成功，就生成token并返回
	    @Override
	    protected void successfulAuthentication(HttpServletRequest request,
	                                            HttpServletResponse response,
	                                            FilterChain chain,
	                                            Authentication authResult) throws IOException, ServletException {

	        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
	        // 所以就是JwtUser啦
	    	User jwtUser = (User) authResult.getPrincipal();
	        UserSession us = new UserSession(jwtUser.getUserId(), jwtUser.getUsername(),jwtUser.getRoleName(), jwtUser.getTenantId());
	        
	        
	        String token = JwtHelper.createJWT(jwtUser.getUsername(), us)  ;
	        // 返回创建成功的token
	        // 但是这里创建的token只是单纯的token
	        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
	        
 
	        
	        response.setHeader("Content-Type", "application/json;charset=utf-8");
	        response.setHeader(JwtHelper.JWT_HTTP_HEADER_AUTHORIZATION, JwtHelper.JWT_TOKEN_PREFIX + token);
	        
	        Map<String,Object> pageInfo = new HashMap<>();

			pageInfo.put("username",jwtUser.getUsername());
			pageInfo.put("userId",jwtUser.getUserId());
			pageInfo.put("role",jwtUser.getRoleName());
	        
	        Map<String,Object> ret = new HashMap<>();
	        ret.put("errorCode", 0);
	        ret.put("errorMsg", "Success");
	        ret.put("data",pageInfo);
	       
	        response.getWriter().write(JSON.toJSONString(ret));
	        response.getWriter().flush();
	    }

	    // 这是验证失败时候调用的方法
	    @Override
	    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	Map<String,Object> ret = new HashMap<>();
	        ret.put("errorCode", 1);
	        ret.put("errorMsg", "username not exist or password error");
	        ret.put("data",new HashMap<>());
	        response.getWriter().write(JSON.toJSONString(ret));
	        response.getWriter().flush();
	    }
	 
 
 
 
}
