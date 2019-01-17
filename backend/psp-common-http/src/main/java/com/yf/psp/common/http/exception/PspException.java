package com.yf.psp.common.http.exception;
 

@SuppressWarnings("serial")
public class PspException extends Exception{
	int errCode;
	String errMsg;
	public PspException(int errCode, String errMsg){
		this.errCode = errCode;
		this.errMsg =  errMsg;
	}
	
 
	public int getErrCode() {
		return errCode;
	}

	 
	public String getErrCodeMsg() {
		return errMsg;
	}
	
	public static class PspJsonErrorException extends Exception{ 
		int errCode=-2;
		String errMsg="json err";

		public int getErrCode() {
			return errCode;
		}

		public String getErrCodeMsg() {
			return errMsg;
		}
	
	}
	
 
	
	public static class PspParameterValidateException extends Exception{ 
		int errCode=-4;
		String errMsg="parameter validate error";

		public int getErrCode() {
			return errCode;
		}

		public String getErrCodeMsg() {
			return errMsg;
		}
	
	}
	
	public static class PspForbiddenException extends Exception{ 
		int errCode=3;
		String errMsg="fobidden";

		public int getErrCode() {
			return errCode;
		}

		public String getErrCodeMsg() {
			return errMsg;
		}
	
	}
	
	public static class PspMethodNotAllowException extends Exception{ 
		int errCode=-3;
		String errMsg="method not allowed err";
		 
		public PspMethodNotAllowException() {
			 
		}
		public int getErrCode() {
			return errCode;
		}

		public String getErrCodeMsg() {
			return errMsg;
		}
	
	}
	public static PspMethodNotAllowException METHOD_NOT_ALLOWED = new PspMethodNotAllowException();
	public static PspJsonErrorException EXCEPTION_CODE_JSON_ERROR = new PspJsonErrorException();
	public static PspForbiddenException EXCEPTION_CODE_FORBIDDEN = new PspForbiddenException();
	public static PspParameterValidateException EXCEPTION_CODE_PARAM_ERROR = new PspParameterValidateException();
	
	public static PspException EXCEPTION_CODE_INTERNAL_ERROR = new PspException(-1,"server intern err");
	public static PspException LOGIN_USERNAME_NOT_EXIST = new PspException(1,"username not exist or password error");
	public static PspException EXCEPTION_JWT_LOGIN_ERR = new PspException(4,"jwt login err");

    public static PspException EXCEPTION_DEVICE_SKU_NOT_FOUND = new PspException(1007,"device sku not exist");
    public static PspException EXCEPTION_DEVICE_SKU_ERROR = new PspException(1008,"device sku error");
    public static PspException EXCEPTION_DEVICE_COMMAND_NOT_FOUND = new PspException(1009,"device command not exist");
    public static PspException EXCEPTION_DEVICE_SEND_COMMAND_ERROR = new PspException(1010,"device send command error");
    public static PspException EXCEPTION_DEVICE_SEND_COMMAND_ERROR_OFF_LINE = new PspException(1011,"device send command error,device offline");
    
    
    public static PspException EXCEPTION_CODE_DB_OPER_ERR = new PspException(2000,"db operation err");
    
    public static PspException EXCEPTION_ROLE_OPER_ERR = new PspException(3000,"role operation err");
    

    
    public static PspException EXCEPTION_CODE_DEVICE_NO_PERMISSION = new PspException(5001,"no permission to device");
    public static PspException EXCEPTION_CODE_DEVICE_GROUP_DUPLICATE_GROUP_NAME = new PspException(5501,"duplicate group name");
    public static PspException EXCEPTION_CODE_DEVICE_GROUP_DEVICE_ALREADY_IN_GROUP = new PspException(5502,"device already in group");
    public static PspException EXCEPTION_CODE_DEVICE_GROUP_DEVICE_NOT_IN_GROUP = new PspException(5503,"device not in group");
    public static PspException EXCEPTION_CODE_DEVICE_GROUP_NOT_EMPTY = new PspException(5504,"group is not empty");
    public static PspException EXCEPTION_CODE_DEVICE_GROUP_NAME_PATTERN_ERROR = new PspException(5505,"group name pattern error");
    

    public static PspException EXCEPTION_CODE_STATIC_DATA_ERR = new PspException(6000,"static data is err");
    
    

}
