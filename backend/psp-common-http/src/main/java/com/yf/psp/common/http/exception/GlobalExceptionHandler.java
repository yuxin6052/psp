package com.yf.psp.common.http.exception;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局的异常处理类
 * @author H220870
 *
 */
@ControllerAdvice

public class GlobalExceptionHandler{
	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
 

	@ResponseStatus(value=HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public Object httpRequestMethodNotSupportedHandler(Exception e,HttpServletRequest request) {
//		e.printStackTrace(); 
		logger.error(" [HttpRequestMethodNotSupportedException] errorCode:{},uri:{}", PspException.METHOD_NOT_ALLOWED.getErrCode(),request.getRequestURI());
		HttpResult hr = new HttpResult();
		hr.setErrorCode(PspException.METHOD_NOT_ALLOWED.getErrCode());
		hr.setErrorMsg(PspException.METHOD_NOT_ALLOWED.getErrCodeMsg());
		hr.setData(new HashMap<>());
		return hr;
	}

	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseBody
	public Object jsonParseExceptionHandler(Exception e,HttpServletRequest request) {
//		e.printStackTrace();
		logger.error(" [HttpMessageNotReadableException] errorCode:{}", PspException.EXCEPTION_CODE_JSON_ERROR.getErrCode());
		HttpResult hr = new HttpResult();
		hr.setErrorCode(PspException.EXCEPTION_CODE_JSON_ERROR.getErrCode());
		hr.setErrorMsg(PspException.EXCEPTION_CODE_JSON_ERROR.getErrCodeMsg());
		hr.setData(new HashMap<>());
		return hr;
	}
	
	@ResponseStatus(value=HttpStatus.FORBIDDEN)
	@ExceptionHandler(value = PspException.PspForbiddenException.class)
	@ResponseBody
	public Object uatForbiddenExceptionHandler(Exception e,HttpServletRequest request) {
//		e.printStackTrace();
		logger.error(" [HttpMessageNotReadableException] errorCode:{}", PspException.EXCEPTION_CODE_FORBIDDEN.getErrCode());
		HttpResult hr = new HttpResult();
		hr.setErrorCode(PspException.EXCEPTION_CODE_FORBIDDEN.getErrCode());
		hr.setErrorMsg(PspException.EXCEPTION_CODE_FORBIDDEN.getErrCodeMsg());
		hr.setData(new HashMap<>());
		return hr;
	}
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = PspException.PspParameterValidateException.class)
	@ResponseBody
	public Object uatBadRequestExceptionHandler(Exception e,HttpServletRequest request) {
//		e.printStackTrace();
		logger.error(" [HttpMessageNotReadableException] errorCode:{}", PspException.EXCEPTION_CODE_PARAM_ERROR.getErrCode());
		HttpResult hr = new HttpResult();
		hr.setErrorCode(PspException.EXCEPTION_CODE_PARAM_ERROR.getErrCode());
		hr.setErrorMsg(PspException.EXCEPTION_CODE_PARAM_ERROR.getErrCodeMsg());
		hr.setData(new HashMap<>());
		return hr;
	}

	@ResponseStatus(value=HttpStatus.OK)
    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    @ResponseBody
    public Object MethodArgumentNotValidHandler(HttpServletRequest request,  
            MethodArgumentNotValidException exception) throws Exception  
    {   
//		exception.printStackTrace();
		logger.error(" [MethodArgumentNotValidException] errorCode:{}",400);
		HttpResult hr = new HttpResult();
		hr.setErrorCode(PspException.EXCEPTION_CODE_PARAM_ERROR.getErrCode());
		StringBuffer errDetail = new StringBuffer();
		for (FieldError error : exception.getBindingResult().getFieldErrors()) { 
        	errDetail.append("default message:");
        	errDetail.append(error.getDefaultMessage());
        	errDetail.append(",");
        	errDetail.append("field:");
        	errDetail.append(error.getField());
        	errDetail.append(",");
        	errDetail.append("rejected value:");
        	errDetail.append(error.getRejectedValue());
        	errDetail.append(";");
	    }
		hr.setErrorMsg(errDetail.toString());
		hr.setData(new HashMap<>());
		return hr;
    } 
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
    public Object defaultExceptionHandler(Exception e,HttpServletRequest request) {
		e.printStackTrace();
		logger.error(" [Exception] errorMsg:{},uri:{}",e.getMessage(),request.getRequestURI());
		HttpResult hr = new HttpResult();
		hr.setErrorCode(PspException.EXCEPTION_CODE_INTERNAL_ERROR.getErrCode());
		hr.setErrorMsg(PspException.EXCEPTION_CODE_INTERNAL_ERROR.getErrCodeMsg());
		hr.setData(new HashMap<>());
		return hr;
    }

	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(value = PspException.class)
	@ResponseBody
    public Object defaultHomeExceptionHandler(PspException e, HttpServletRequest request) {
		e.printStackTrace();
		logger.error(" [Exception] errorMsg:{},uri:{}",e.getMessage(),request.getRequestURI());
		HttpResult hr = new HttpResult(); 
		PspException he = (PspException)e;
		hr.setErrorCode(he.getErrCode());
		hr.setErrorMsg(he.getErrCodeMsg());
		hr.setData(new HashMap<>());
		return hr;
    }
}