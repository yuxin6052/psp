package com.yf.psp.company.controller;


import com.yf.psp.common.http.exception.PspException;
import com.yf.psp.company.entity.CompanyRequest;
import com.yf.psp.company.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@Controller
public class CompanyController {
	private final static Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
//	@Autowired
//	DeviceService deviceService;
//
//    @GetMapping("/list")
//    @ResponseBody
//	public Object list(@RequestParam(value="isOnline",required=false)Short isOnline,@RequestParam(value="groupId",required=false)Long groupId ,@RequestParam(value="searchString",required=false)String searchString , @RequestParam(value="deviceType",required=false)Long deviceType ,@RequestParam(value="pageNo",required=false)Integer pageNo, @RequestParam(value="pageSize",required=false)Integer pageSize) throws MdmException {
//
//    	Authentication a = SecurityContextHolder.getContext().getAuthentication();
//    	UserSession us = (UserSession)a.getPrincipal();
//        return deviceService.list(isOnline,groupId, searchString, deviceType, pageNo, pageSize, us);
//	}
//
//    @GetMapping("/delete/{deviceId}")
//    @ResponseBody
//	public Object delete(@PathVariable("deviceId")Long deviceId) throws MdmForbiddenException {
//    	Authentication a = SecurityContextHolder.getContext().getAuthentication();
//    	UserSession us = (UserSession)a.getPrincipal();
//    	deviceService.delete(deviceId,  us);
//        return new HashMap<>();
//	}
//
//    @GetMapping("/detail/{deviceId}")
//    @ResponseBody
//	public Object detail(@PathVariable("deviceId")Long deviceId) throws MdmForbiddenException {
//    	Authentication a = SecurityContextHolder.getContext().getAuthentication();
//    	UserSession us = (UserSession)a.getPrincipal();
//    	deviceService.get(deviceId,  us);
//        return new HashMap<>();
//	}
//
//    @GetMapping("/getLocation/{deviceId}")
//    @ResponseBody
//	public Object getLocation(@PathVariable("deviceId")Long deviceId) throws MdmForbiddenException {
//    	Authentication a = SecurityContextHolder.getContext().getAuthentication();
//    	UserSession us = (UserSession)a.getPrincipal();
//        return deviceService.getLocation(deviceId,  us);
//	}
//
//    @PostMapping("/command")
//    @ResponseBody
//	public Object command(@RequestBody CommandRequestMessage crm) throws MdmForbiddenException {
//    	Authentication a = SecurityContextHolder.getContext().getAuthentication();
//    	UserSession us = (UserSession)a.getPrincipal();
//    	return deviceService.command(crm,  us) ;
//	}
//
//    @GetMapping("/getEnrollString")
//    @ResponseBody
//	public Map<String,String> getEnrollString(HttpSession session) throws MdmException {
//    	Authentication a = SecurityContextHolder.getContext().getAuthentication();
//    	UserSession us = (UserSession)a.getPrincipal();
//    	Map<String,String> ret = new HashMap<>();
//    	ret.put("qrCode",   this.deviceService.getEnrollString(us,session));
//    	return ret;
//    }
//
//    @GetMapping("/getOverview/{language}/{deviceId}")
//    @ResponseBody
//	public Object getOverview(@PathVariable("language")String language,@PathVariable("deviceId")Long deviceId) throws MdmForbiddenException, MdmException {
//    	Authentication a = SecurityContextHolder.getContext().getAuthentication();
//    	UserSession us = (UserSession)a.getPrincipal();
//    	return deviceService.getOverview(deviceId,  us,language);
//	}
//
//    @GetMapping("/getCommandLog/{deviceId}")
//    @ResponseBody
//	public Object getCommandLog(@PathVariable("deviceId")Long deviceId, @RequestParam(value="pageNo" )Integer pageNo, @RequestParam(value="pageSize" )Integer pageSize) throws MdmException, MdmForbiddenException {
//
//    	Authentication a = SecurityContextHolder.getContext().getAuthentication();
//    	UserSession us = (UserSession)a.getPrincipal();
//        return deviceService.getCommandLog(deviceId, pageNo, pageSize, us);
//	}
//
//    @GetMapping("/getCommandStatus/{deviceId}/{uniqId}")
//    @ResponseBody
//	public Object getCommandStatus(@PathVariable("deviceId")Long deviceId, @PathVariable("uniqId")Long uniqId) throws MdmException, MdmForbiddenException {
//
//    	Authentication a = SecurityContextHolder.getContext().getAuthentication();
//    	UserSession us = (UserSession)a.getPrincipal();
//        return deviceService.getCommandStatus(deviceId, uniqId,us);
//	}
    @Autowired
	CompanyService companyService;
	@GetMapping("/tenant/v1/companyLabel/list")
    @ResponseBody
	public Object listLabel( ) throws PspException, PspException.PspForbiddenException {
        return companyService.listLabel();
	}

    @GetMapping("/tenant/v1/company/list")
    @ResponseBody
    public Object list(@RequestParam(value="companyName",required = false)String companyName,@RequestParam(value="companyLabel",required = false)Long companyLabel,@RequestParam(value="unifiedSocialCreditCode",required = false)String unifiedSocialCreditCode,@RequestParam(value="pageNo",required = false)Integer pageNo,@RequestParam(value="pageSize",required = false)Integer pageSize,@RequestParam(value="queryType",required = false)Integer queryType ) throws PspException, PspException.PspForbiddenException {
        return companyService.list(companyName,companyLabel,unifiedSocialCreditCode,pageNo,pageSize,queryType);
    }

    @PostMapping("/tenant/v1/company/create")
    @ResponseBody
    public Object create(@RequestBody CompanyRequest ccr) throws PspException, PspException.PspForbiddenException {
        return companyService.create(ccr);
    }

    @PostMapping("/tenant/v1/company/update")
    @ResponseBody
    public Object create(@RequestParam("updateType")Integer updateType,@RequestBody CompanyRequest ccr) throws PspException, PspException.PspForbiddenException {
         companyService.update(updateType,ccr);
         return new HashMap<>();
    }
}
