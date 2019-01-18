package com.yf.psp.company.service;

import com.yf.psp.common.login.UserSession;
import com.yf.psp.db.postgres.company.TblCompany;
import com.yf.psp.db.postgres.company.TblCompanyLabel;
import com.yf.psp.db.postgres.company.dao.TblCompanyLabelMapper;
import com.yf.psp.db.postgres.company.dao.TblCompanyMapper;
import com.yf.psp.db.util.plugin.PageByPageNo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Service
public class CompanyService {
	private final static Logger logger = LoggerFactory.getLogger(CompanyService.class);
//	@Autowired
//	ProductDataCategoryDefineStaticDataService productDataCategoryDefineStaticDataService;
//	@Autowired
//	DeviceDao deviceDao;
//	@Autowired
//	DeviceGroupMemberDao deviceGroupMemberDao;
//	@Autowired
//	DeviceGroupDao deviceGroupDao;
//	@Autowired
//	CtrDeviceService ctrDeviceService;
//	@Autowired
//	ProductSkuStaticDataService productSkuStaticDataService;
//	@Autowired
//	DeviceCtrLogDao deviceCtrLogDao;
//	@Autowired
//	TemplateStaticDataService templateStaticDataService;
//	@Autowired
//	CsmUserDao csmUserDao;
//	@Autowired
//	DeviceEnrollProperties deviceEnrollProperties;
//	private Device getDevice( UserSession us, Long deviceId) {
//		Device d = deviceDao.getByDeviceId(deviceId);
//		if(d==null || !d.getTenantId().equals(us.getTenantId())) {
//			return null;
//		}
//		return d;
//	}
//
//	public Map<String,Object> list(Short isOnline, Long groupId, String searchString, Long deviceType, Integer pageNo, Integer pageSize,UserSession us){
//		List<DeviceResponse>  dl = new ArrayList<>();
//
//
//		PageByPageNo page = new PageByPageNo();
//		page.setPageNo(pageNo);
//		page.setPageSize(pageSize);
//		page.setParam("groupId", groupId);
//		page.setParam("searchString", searchString);
//		page.setParam("deviceType", deviceType);
//		page.setParam("isOnline", isOnline);
//		page.setParam("tenantId", us.getTenantId());
//		page.setOrderBy("enroll_time desc");
//		List<Device> tsdl = deviceDao.listByPage(page);
//
//		Map<Long, Long> dgMap = new HashMap<>();
//		Map<Long, String> gMap = new HashMap<>();
//		if (tsdl != null && !tsdl.isEmpty()) {
//			Set<Long> deviceIdSet = new HashSet<>();
//			for (Device d : tsdl) {
//				deviceIdSet.add(d.getDeviceId());
//			}
//
//			Set<Long> groupIdSet = new HashSet<>();
//			List<DeviceGroupMember> retMemberList = deviceGroupMemberDao.listByDeviceIdList(deviceIdSet);
//			if (retMemberList != null && !retMemberList.isEmpty()) {
//				for (DeviceGroupMember retMember : retMemberList) {
//					if(!groupIdSet.contains(retMember.getGroupId())) {
//						groupIdSet.add(retMember.getGroupId());
//					}
//					dgMap.put(retMember.getDeviceId(), retMember.getGroupId());
//				}
//
//				List<DeviceGroup> retGroupList = deviceGroupDao.listByDeviceGroupIdList(groupIdSet);
//				if (retGroupList != null && !retGroupList.isEmpty()) {
//					for (DeviceGroup retGroup : retGroupList) {
//						gMap.put(retGroup.getGroupId(), retGroup.getGroupName());
//					}
//				}
//			}
//		}
//
//	    for(Device d:tsdl) {
//	    	DeviceResponse dr = new DeviceResponse();
//	    	dr.setDeviceId(d.getDeviceId());
//	    	dr.setDeviceName(d.getDeviceName());
//	    	dr.setDeviceSku(d.getSku());
//	    	dr.setDeviceType(d.getDeviceType());
//	    	dr.setEnrollTime(d.getEnrollTime().getTime());
//	    	dr.setLastOnlineTime(d.getOnlineTime().getTime());
//	    	if (dgMap.containsKey(d.getDeviceId())) {
//	    		Long deviceGroupId = dgMap.get(d.getDeviceId());
//	    		dr.setDeviceGroupId(deviceGroupId);
//	    		dr.setDeviceGroupName(gMap.get(deviceGroupId));
//	    	} else {
//	    		dr.setDeviceGroupId(-1L);
//	    	}
//	    	//dr.setOwnerUserId(-1L);
//	    	//dr.setOwnerUserName("");
//	    	dr.setSerialNo(d.getConnectId());
//	    	dr.setIsOnline(d.getIsOnline());
//	    	dl.add(dr);
//	    }
//		Map<String,Object> ret = new HashMap<>();
//		ret.put("deviceList", dl);
//    	ret.put("total", page.getTotal());
//		return ret;
//	}
//
//	public void get(Long deviceId,UserSession us) throws MdmForbiddenException{
//		Device d = this.getDevice(us, deviceId);
//		if(d==null) {
//			throw MdmException.EXCEPTION_CODE_FORBIDDEN;
//		}
//	}
//
//	public DeviceLocationResponse getLocation(Long deviceId,UserSession us) throws MdmForbiddenException{
//		Device d = this.getDevice(us, deviceId);
//		if(d==null) {
//			throw MdmException.EXCEPTION_CODE_FORBIDDEN;
//		}
//		DeviceLocationResponse dlr = new DeviceLocationResponse();
//		dlr.setLatitude(d.getLatitude());
//		dlr.setLongitude(d.getLongtitude());
//		dlr.setRecvTime(d.getLocationUpdateTime()==null?null:d.getLocationUpdateTime().getTime());
//		return dlr;
//	}
//
//	public void delete(Long deviceId,UserSession us) throws MdmForbiddenException{
//		Device dvc = this.getDevice(us, deviceId);
//		if(dvc==null) {
//			throw MdmException.EXCEPTION_CODE_FORBIDDEN;
//		}
//		Device d = new Device();
//		d.setDeviceId(deviceId);
//		d.setDeleteFlag(DbConstDef.DELETE);
//		deviceDao.updateByDeviceId(d);
//	}
//
//	public Map<String,Object> command(CommandRequestMessage crm,UserSession us) throws MdmForbiddenException{
//		List<ErrorDevicesResponse> edrl = new ArrayList<>();
//		List<ErrorDevicesResponse> okDvc = new ArrayList<>();
//		Map<String,Object> ret = new HashMap<>();
//		ret.put("okDevices", okDvc);
//		ret.put("errorDevices", edrl);
//		for(CommandRequestMessageData cr:crm.getMsgData()) {
//			ErrorDevicesResponse edr = new ErrorDevicesResponse();
//			Device dvc = this.getDevice(us, cr.getDeviceId());
//			/*if(dvc==null) { //对于攻击情况，不要返回任何值
//				logger.error("operation forbidden,deviceId {},userId {}",dvc.getDeviceId(), us.getUserId());
//				edr.setDeviceId(dvc.getDeviceId());
//				edr.setSerialNo(dvc.getConnectId());
//				edr.setDeviceName(dvc.getDeviceName());
//				edr.setErrorCode(3);
//				edr.setErrorMsg("fobidden");
//				edrl.add(edr);
//				continue;
//			}*/
//
//			if(Device.IsOnline.OFFLINE.equals(dvc.getIsOnline())) {
//				logger.error("device is not online,deviceId {} ",dvc.getDeviceId());
//				edr.setDeviceId(dvc.getDeviceId());
//				edr.setSerialNo(dvc.getConnectId());
//				edr.setDeviceName(dvc.getDeviceName());
//				edr.setErrorCode(MdmException.EXCEPTION_DEVICE_SEND_COMMAND_ERROR_OFF_LINE.getErrCode());
//				edr.setErrorMsg("device is not online");
//				edrl.add(edr);
//				continue;
//			}
//
//			ProductSku pSku = productSkuStaticDataService.get(dvc.getSku());
//			if(pSku==null) {
//				logger.error("sku not exist ,deviceId {}",dvc.getDeviceId());
//				edr.setDeviceId(dvc.getDeviceId());
//				edr.setSerialNo(dvc.getConnectId());
//				edr.setDeviceName(dvc.getDeviceName());
//				edr.setErrorCode(MdmException.EXCEPTION_CODE_STATIC_DATA_ERR.getErrCode());
//				edr.setErrorMsg("device sku not exist");
//				edrl.add(edr);
//				continue;
//			}
//			Product prod = pSku.getProduct();
//			if(prod==null) {
//				logger.error("sku error,deviceId {}",dvc.getDeviceId());
//				edr.setDeviceId(dvc.getDeviceId());
//				edr.setSerialNo(dvc.getConnectId());
//				edr.setDeviceName(dvc.getDeviceName());
//				edr.setErrorCode(MdmException.EXCEPTION_CODE_STATIC_DATA_ERR.getErrCode());
//				edr.setErrorMsg("device sku error");
//				edrl.add(edr);
//				continue;
//			}
//			ProductCommandToApp pCmd = prod.findProductCommandToApp(ConstDef.MessageType.COMMAND_MSG_TYPE, cr.getAction(), ConstDef.REQUEST);
//			if(pCmd==null) {
//				logger.error("command not found,deviceId {}",dvc.getDeviceId());
//				edr.setDeviceId(dvc.getDeviceId());
//				edr.setSerialNo(dvc.getConnectId());
//				edr.setDeviceName(dvc.getDeviceName());
//				edr.setErrorCode(MdmException.EXCEPTION_CODE_STATIC_DATA_ERR.getErrCode());
//				edr.setErrorMsg("device command not exist");
//				edrl.add(edr);
//				continue;
//			}
//			Map<String,Object> commandParams = new HashMap<>();
//			commandParams.put(ConstDef.TemplateArgs.DATA, cr.getArgs()==null?new HashMap<String,Object>():cr.getArgs());
//			Map<String,Object> ctxMap = new HashMap<>();
//			ctxMap.put(ConstDef.DEVICE_CTRL_MSG_ID_CTX_KEY, crm.getMsgId());
//			try {
//				Long unqId = ctrDeviceService.sendCommand(DeviceCtrLog.SrcType.USER_ID, us.getUserId(), dvc, pCmd, commandParams, ctxMap, null);
//				edr.setHoldUniqId(unqId);
//				okDvc.add(edr);
//			} catch (IOException e) {
//				logger.error("send command error,deviceId {}",dvc.getDeviceId());
//				edr.setDeviceId(dvc.getDeviceId());
//				edr.setSerialNo(dvc.getConnectId());
//				edr.setDeviceName(dvc.getDeviceName());
//				edr.setErrorCode(MdmException.EXCEPTION_DEVICE_SEND_COMMAND_ERROR.getErrCode());
//				edr.setErrorMsg("send command error");
//				edrl.add(edr);
//			}
//		}
//		return ret;
//	}
//
//	public String getEnrollString(UserSession us,HttpSession session) throws MdmException {
//		//TODO:Long configFileId = session.getAttribute("userConfigFileId");
//		Map<String,Object> params = new HashMap<>();
//		params.put(ConstDef.TemplateArgs.ENROLL_HTTP_ADDRESS, this.deviceEnrollProperties.getHttpAddress());
//		params.put(ConstDef.TemplateArgs.ENROLL_MQTT_ADDRESS, this.deviceEnrollProperties.getMqttAddress());
//		params.put(ConstDef.TemplateArgs.ENROLL_TENANT_KEY, us.getTenantId().toString());
//		Template temp = this.templateStaticDataService.get(Template.DEFAULT_DEVICE_ENROLL_TEMPLATE_ID);
//		return temp.getStr(false, params);
//    }
//
//
//
//	private Map<String,Object> formateKV(String name,Object value){
//		Map<String,Object> ret = new HashMap<>(2);
//		ret.put("name", name);
//		ret.put("value", value);
//		return ret;
//	}
//
//	public Object getOverview(Long deviceId,UserSession us,String language) throws MdmForbiddenException, MdmException {
//		Device dvc = this.getDevice(us, deviceId);
//		if(dvc==null) {
//			throw MdmException.EXCEPTION_CODE_FORBIDDEN;
//		}
//		ProductSku pSku = this.productSkuStaticDataService.get(dvc.getSku());
//		if(pSku==null) {
//			throw MdmException.EXCEPTION_CODE_STATIC_DATA_ERR;
//		}
//		Product prd = pSku.getProduct();
//		if(prd==null) {
//			throw MdmException.EXCEPTION_CODE_STATIC_DATA_ERR;
//		}
//		Map<Integer/*dataCategory*/,List<Map<String,Object>>> retTmp = new HashMap<>();
//		for(Map.Entry<Integer/*data_type_category*/,ProductDataCategoryDefine> it:prd.getProductDataTypeCategoryMap().entrySet()){
//			if(!ProductDataCategoryDefine.DataCategory.SENSORS.equals(it.getKey())) {
//				retTmp.put(it.getKey(),new ArrayList<>());
//			}
//		}
//
//		for(Map.Entry<Integer/*data_type_category*/,ProductDataType> it:prd.getProductDataTypeMap().entrySet()){
//			ProductDataTypeDefine pdtDef = it.getValue().getDataTypeDefine();
//			if(!pdtDef.getIsShow()) {
//				continue;
//			}
//			DwsDataEntity dws = dvc.returnDws(it.getKey(), pdtDef.getDataCategory());
//			if(dws==null) {
//				continue;
//			}
//			Object value = dws.getValue();
//			if(pdtDef.getUnit()!=null && value!=null) {
//				value = value.toString() + pdtDef.getUnit();
//			}
//			if(ConstDef.LANGUAGE_ZH_CN.equals(language)) {
//				retTmp.get(pdtDef.getDataCategory()).add(this.formateKV(pdtDef.getShowNameCn(), value));
//			}else {
//				retTmp.get(pdtDef.getDataCategory()).add(this.formateKV(pdtDef.getShowNameEn(), value));
//			}
//		}
//		DwsDataEntityCollectionMap sensorsMap = dvc.getSensorData();
//		List<Map<String,Object>> deviceData = new ArrayList<>();
//		for(Map.Entry<Integer/*dataCategory*/,List<Map<String,Object>>> it: retTmp.entrySet()) {
//			if(ConstDef.LANGUAGE_ZH_CN.equals(language)) {
//				deviceData.add(this.formateKV(this.productDataCategoryDefineStaticDataService.get(it.getKey()).getCategoryNameCn(), it.getValue()));
//			}else {
//				deviceData.add(this.formateKV(this.productDataCategoryDefineStaticDataService.get(it.getKey()).getCategoryNameEn(), it.getValue()));
//			}
//		}
//
//		Map<String,Object> returnObj = new HashMap<>();
//		returnObj.put("serialNo", dvc.getConnectId());
//		returnObj.put("isOnline",dvc.getIsOnline());
//		returnObj.put("deviceData", deviceData);
//		if(dvc.getSensorData()==null || true) {//需求暂时不展示 传感器数据
//			return returnObj;
//		}
//
//		List<Map<String,Object>> sensors = retTmp.get(ProductDataCategoryDefine.DataCategory.SENSORS);
//
//		for(Map.Entry<String/*name etc key*/,DwsDataEntityCollection> it: sensorsMap.entrySet()) {
//			List<Map<String,Object>> tmp = new ArrayList<>();
//			sensors.add(this.formateKV(it.getKey(), tmp));
//			for(Map.Entry<String/*dateType*/,DwsDataEntity> data:it.getValue().entrySet()) {
//				ProductDataType pdt = prd.getProductDataTypeMap().get(Integer.valueOf(data.getKey()));
//				if(pdt==null) {
//					continue;
//				}
//				ProductDataTypeDefine pdtDef = pdt.getDataTypeDefine();
//				if(pdtDef==null || !pdtDef.getIsShow()) {
//					continue;
//				}
//				Object value = data.getValue().getValue();
//				if(pdtDef.getUnit()!=null && value!=null) {
//					value = value.toString() + pdtDef.getUnit();
//				}
//				if(ConstDef.LANGUAGE_ZH_CN.equals(language)) {
//					tmp.add(this.formateKV(pdt.getDataTypeDefine().getShowNameCn(), value));
//				}else {
//					tmp.add(this.formateKV(pdt.getDataTypeDefine().getShowNameEn(), value));
//				}
//			}
//		}
//
//		return returnObj;
//	}
//
//	public Map<String,Object> getCommandLog( Long deviceId, Integer pageNo, Integer pageSize,UserSession us) throws MdmForbiddenException, MdmException {
//		Device dvc = this.getDevice(us, deviceId);
//		if(dvc==null) {
//			throw MdmException.EXCEPTION_CODE_FORBIDDEN;
//		}
//		ProductSku pSku = this.productSkuStaticDataService.get(dvc.getSku());
//		if(pSku==null) {
//			throw MdmException.EXCEPTION_CODE_STATIC_DATA_ERR;
//		}
//		Product prd = pSku.getProduct();
//		if(prd==null) {
//			throw MdmException.EXCEPTION_CODE_STATIC_DATA_ERR;
//		}
//		PageByPageNo page = new PageByPageNo();
//		page.setPageNo(pageNo);
//		page.setPageSize(pageSize);
//		page.setParam("deviceId", deviceId);
//		page.setOrderBy("send_time desc");
//		List<DeviceCtrLog> listCmd = this.deviceCtrLogDao.listByPage(page);
//		Map<Long,CsmUser> mapUsers = new HashMap<>();
//		List<DeviceCmdLogResponse> ret = new ArrayList<>();
//		for(DeviceCtrLog it: listCmd) {
//			DeviceCmdLogResponse cmd = new DeviceCmdLogResponse();
//			ret.add(cmd);
//			cmd.setCmdId(it.getCommandId());
//			ProductCommandToApp pCmd = prd.getProductCommandToAppMap().get(it.getCommandId());
//			if(pCmd!=null) {
//				cmd.setCmdName(pCmd.getCommand() +"-" + pCmd.getAction());
//			}
//			cmd.setCmdStatus(it.getResponseStatus() == null ? "-1":it.getResponseStatus());
//			cmd.setRspTime(it.getReceiveTime()!=null ? it.getReceiveTime().getTime():null);
//			if(DeviceCtrLog.SrcType.USER_ID.equals(it.getSrcType())) {
//				cmd.setSendId(it.getSrcId());
//				CsmUser user = mapUsers.get(it.getSrcId());
//				if(user==null) {
//					user = this.csmUserDao.getByUserId(it.getSrcId());
//					mapUsers.put(it.getSrcId(), user);//不用mybatis 缓存，不如自己写性能高，而且mybatis 该功能我们暂时使用的不够心里有底
//				}
//				if(user!=null) {
//					cmd.setSendname(user.getUserName());
//				}
//			}else if(DeviceCtrLog.SrcType.CLOUD_RSP.equals(it.getSrcType())) {
//				cmd.setSendId(deviceId);
//				cmd.setSendname(dvc.getDeviceName());
//			}else {
//				//暂不支持
//			}
//			cmd.setSendTime(it.getSendTime().getTime());
//			cmd.setSendType(it.getSrcType());
//		}
//		Map<String,Object> retObj = new HashMap<>();
//		retObj.put("logList", ret);
//		retObj.put("total", page.getTotal());
//
//		return retObj;
//	}
//
//	public Map<String,Object> getCommandStatus( Long deviceId, Long uniqId,UserSession us) throws MdmForbiddenException, MdmException {
//		Device dvc = this.getDevice(us, deviceId);
//		if(dvc==null) {
//			throw MdmException.EXCEPTION_CODE_FORBIDDEN;
//		}
//		ProductSku pSku = this.productSkuStaticDataService.get(dvc.getSku());
//		if(pSku==null) {
//			throw MdmException.EXCEPTION_CODE_STATIC_DATA_ERR;
//		}
//		DeviceCtrLog log = this.deviceCtrLogDao.getByUniqueId(uniqId);
//		Map<String,Object> retObj = new HashMap<>();
//		if(log==null || !log.getDeviceId().equals(deviceId)) {
//			retObj.put("cmdStatus", "unknown");
//			logger.error("portal call err,deviceId {} has no cmd uniqId {}" ,deviceId,uniqId);
//		}else {
//			String cmdStatus = log.getResponseStatus() == null ? "-1":log.getResponseStatus();
//			retObj.put("cmdStatus", cmdStatus);
//		}
//		return retObj;
//	}
	@Autowired
	TblCompanyLabelMapper  tblCompanyLabelMapper;

    @Autowired
    TblCompanyMapper  tblCompanyMapper;
	public Map<String,List<TblCompanyLabel>> listLabel(){
		Map<String,List<TblCompanyLabel>> retMap = new HashMap<>();
		retMap.put("labels",tblCompanyLabelMapper.list());
		return retMap;
	}

    public  Map<String,Object> list(String companyName,Long companyLabel,String unifiedSocialCreditCode,Integer pageNo,Integer pageSize,Integer queryType){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        UserSession us = (UserSession)a.getPrincipal();
        PageByPageNo page = new PageByPageNo();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setParam("companyName", companyName);
        page.setParam("unifiedSocialCreditCode", unifiedSocialCreditCode);
        page.setParam("companyLabel", companyLabel);
        page.setParam("queryType", queryType);
        page.setOrderBy("create_time desc");
        List<TblCompany> tcl = tblCompanyMapper.listByPage(page);
        List<Map<String,Object>> companys= new ArrayList<>();

//        businessScope:"asdasd" //业务范围
//        companyId:1
//        companyName:"asdda"
//        companyQualification:"sadsadsa"//企业资质
//        legalPerson:"afasf" //法人
//        legalPersonIdentityNo:"afasf" //法人身份证号
//        legalPersonAuthorizer："sadasd" //法人授权人
//        authorizerPhoneNo:"sadsaf"//授权人手机号
//        authorizerEmail:"asdsadsa"//授权人邮箱
//        registerArea:"asfasfas" //注册地
//        registerCapital:"asfasf" //注册资本
//        addressNj:"asfsafas"// 驻宁地址
//
//        status:1 //启用禁用状态 1：启用 0：暂停 2：暂停中 3：注销
//        audit_status：0 // 审核状态  0:未提交审核 1:待审核 1：审核通过 2：审核不通过
//        unifiedSocialCreditCode:"asdasd" //社会统一信用代码
//        remark:"阿斯顿撒"  //多行文本区域(备注)
//        companyLabels:[   //公司包含的职能标签
//        {
//            "labelId":11,
//                "labelName":"工程监理"
//        }
//]
//        users:[
//        {
//            userId:1
//            username:"afsa" //登陆账号
//            realName:"asdas" //姓名
//            role:"proxy"
//            status:1 //启用禁用状态 1：启用 0：暂停 2：作废
//        }
//]

for(TblCompany tc:tcl){
    Map<String,Object> m =    new HashMap<>();
    m.put("businessScope",tc.getBusinessScope());
    m.put("companyId",tc.getCompanyId());
    m.put("companyName",tc.getCompanyName());
    m.put("companyQualification",tc.getCompanyQualification());
    m.put("legalPerson",tc.getLegalPerson());
    m.put("legalPersonIdentityNo",tc.getLegalPersonIdentityNo());
    m.put("legalPersonAuthorizer",tc.getLegalPersonAuthorizer());
    m.put("authorizerPhoneNo",tc.getAuthorizerPhoneNo());
    m.put("authorizerEmail",tc.getAuthorizerEmail());
    m.put("registerArea",tc.getRegisterArea());
    m.put("registerCapital",tc.getRegisterCapital());
    m.put("addressNj",tc.getAddressNj());
    m.put("status",tc.getStatus());
    m.put("audit_status",tc.getAuditStatus());
    companys.add(m);
}
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("total", page.getTotal());
        retMap.put("companys",companys);
        return retMap;
    }
}
