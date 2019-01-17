package com.yf.psp.common.init;

import java.sql.Timestamp;
 

public class ConstDef { 
	
    public static final String LANGUAGE_ZH_CN= "cn";
    public static final String LANGUAGE_EN_US= "en";
	  
	//2199-12-31T23:59:59Z，注意时区问题：
	public static final Timestamp END_TIME = new Timestamp(7258089599000L);

	//中国时区 2199-01-01T08:00:00:000，也就是 GTM 时间 2199-01-01T00:00:00:000
	public static final Timestamp END_TIME2199 = new Timestamp(7226582400000L);

   
	public static final String DRIVER_USER_NAME_0 = "driver0";
	public static final String DRIVER_USER_NAME_1 = "driver1";
	public static final String MGNT_USER_NAME_0 = "mgnt0";
	public static final String MGNT_USER_NAME_1 = "mgnt1";
	

	public static final String ADMIN_USER_NAME = "admin";
 
	public static final Long  DRIVER_USER_ID_0 = 1L ;
	public static final Long  DRIVER_USER_ID_1 = 2L ;
	public static final Long  MGNT_USER_ID_0   = 3L ;
	public static final Long  MGNT_USER_ID_1   = 4L ;
	public static final Long  ADMIN_USER_ID    = 5L ;
	
	
	public static final Long  TENANT_ID_0 = 1L ;
	public static final Long  TENANT_ID_1   = 2L ;
	
	public static final String PASSWORD = "123456";


	public static final String ROLE_MGNT = "mgr";
	public static final String ROLE_DRIVER = "driver";
	public static final String ROLE_ADMIN = "admin";
	
	public static final String ROLE_NAME_MGNT = "租户管理员";
	public static final String ROLE_NAME_DRIVER = "司机";
	public static final String ROLE_NAME_ADMIN = "超级管理员";

    public static final String USER_SESSION_NAME= "userSession";

	public static class WebsocktMsgType {
		 
	}
	
	 


	public static class CommandAction{
		 	
	}

	public static class OpenDoorResult{
		public static final String WORONGPASSWORD   = "wrongPassword";
		public static final String DOOROPENED    = "doorOpened";
		public static final String DOOROPENFAILED    = "doorOpenFailed";
	}

 

	public static class OsType{
		public static final String ANDROID = "android";
		public static final String ANDROID_SUBPHONE = "android_subphone";
		public static final String IOS = "ios";
		public static final String IOS_ENTERPRISE = "ios_enterprise";
		public static final String IOS_SUBPHONE = "ios_subphone";
	}

	  

	public static String getAutoLoginToken(String uuid,Long userId,Timestamp loginTime){
		int hc = (userId.toString() + loginTime.getTime()).toString().hashCode();
		return String.valueOf(hc) + ":"  + uuid;
	}

	public static String getUUIDFromAutoLoginToken(String autoLoginToken){
		int i = autoLoginToken.indexOf(":");
		return autoLoginToken.substring(i+1);
	}

      
}
