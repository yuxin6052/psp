package com.yf.psp.init;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

 
public class AnonymousUrlInfo {
    private static Set<String> urlSet = new HashSet<>();
    private AnonymousUrlInfo() {
        
      }

    static {
    	urlSet.add("/tenant/serverlogin");
    	urlSet.add("/druid/**");
    	urlSet.add("/tenant/serverlogout");
    	urlSet.add("/tenant/user/v1/getPageInfo");
    }
    
    public static boolean canAccess(String url) {
    	PathMatcher pathMatcher = new AntPathMatcher();
    	for(String pattern:urlSet) {
    		if(pathMatcher.match(pattern, url)) {
    			return true;
    		}
    	}
    	return urlSet.contains(url);
    }
}
