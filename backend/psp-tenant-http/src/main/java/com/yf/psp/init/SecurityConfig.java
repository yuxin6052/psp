package com.yf.psp.init;

 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsUtils;

import com.yf.psp.service.staticdatamgr.RbacRoleStaticDataService;
import com.yf.psp.staticdata.rbac.RoleService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   
 
	@Autowired
	RoleService roleService;
	@Autowired
	RbacRoleStaticDataService rbacRoleStaticDataService;

    @Autowired 
    private UserDetailsServiceImpl userDetailsServiceImpl;
   
    
   
 

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	 
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	 http.antMatcher("/**")
         .addFilterAfter(new JwtAuthenticationTokenFilter(authenticationManager(), roleService, rbacRoleStaticDataService),AbstractPreAuthenticatedProcessingFilter.class)
         .addFilterAfter(new UrlAccessFilter(authenticationManager(), roleService), JwtAuthenticationTokenFilter.class)
         .httpBasic()
         .and()
         .authorizeRequests()
        // .antMatchers("/","/index*").permitAll()
         .anyRequest().authenticated().
         requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
         .and().csrf().disable(); 
    	
      
    }

 
    
    
    
}