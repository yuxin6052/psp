package com.yf.psp.common.http.exception;

/**
 * Created by H154651 on 6/22/2017.
 */
public class HttpResult{
    private int ErrorCode;
    private String ErrorMsg;
    private Object Data;

	public int getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(int errorCode) {
		ErrorCode = errorCode;
	}

	//按照security 要求 不返回错误信息
	public String getErrorMsg() {
		return ErrorMsg;//ErrorMsg;
	}
	//按照security 要求 不返回错误信息
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;//errorMsg;
	}

	public Object getData() {
		return Data;
	}

	public void setData(Object data) {
		Data = data;
	}
}