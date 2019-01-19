package com.yf.psp.company.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class CompanyRequest {

//	companyName:"asdda"
//	legalPersonAuthorizer："sadasd" //法人授权人
//	authorizerPhoneNo:"sadsaf"//授权人手机号
//	remark:"阿斯顿撒"  //多行文本区域(备注)
//	unifiedSocialCreditCode:"asdasd" //社会统一信用代码
//	labelIds:[   //公司包含的职能标签
//			11,22,33
//			]
private Long companyId;
	private String 	businessScope; //业务范围
	private String companyQualification;//企业资质
	private String legalPerson;//法人
	private String legalPersonIdentityNo; //法人身份证号
	private String authorizerEmail;//授权人邮箱
	private String registerArea; //注册地
	private String registerCapital;//注册资本
	private String addressNj;// 驻宁地址

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getCompanyQualification() {
		return companyQualification;
	}

	public void setCompanyQualification(String companyQualification) {
		this.companyQualification = companyQualification;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLegalPersonIdentityNo() {
		return legalPersonIdentityNo;
	}

	public void setLegalPersonIdentityNo(String legalPersonIdentityNo) {
		this.legalPersonIdentityNo = legalPersonIdentityNo;
	}

	public String getAuthorizerEmail() {
		return authorizerEmail;
	}

	public void setAuthorizerEmail(String authorizerEmail) {
		this.authorizerEmail = authorizerEmail;
	}

	public String getRegisterArea() {
		return registerArea;
	}

	public void setRegisterArea(String registerArea) {
		this.registerArea = registerArea;
	}

	public String getRegisterCapital() {
		return registerCapital;
	}

	public void setRegisterCapital(String registerCapital) {
		this.registerCapital = registerCapital;
	}

	public String getAddressNj() {
		return addressNj;
	}

	public void setAddressNj(String addressNj) {
		this.addressNj = addressNj;
	}

	private String companyName;
	private String legalPersonAuthorizer;
	private String authorizerPhoneNo;
	private String remark;
	private String unifiedSocialCreditCode;
	private List<Integer> labelIds;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLegalPersonAuthorizer() {
		return legalPersonAuthorizer;
	}

	public void setLegalPersonAuthorizer(String legalPersonAuthorizer) {
		this.legalPersonAuthorizer = legalPersonAuthorizer;
	}

	public String getAuthorizerPhoneNo() {
		return authorizerPhoneNo;
	}

	public void setAuthorizerPhoneNo(String authorizerPhoneNo) {
		this.authorizerPhoneNo = authorizerPhoneNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUnifiedSocialCreditCode() {
		return unifiedSocialCreditCode;
	}

	public void setUnifiedSocialCreditCode(String unifiedSocialCreditCode) {
		this.unifiedSocialCreditCode = unifiedSocialCreditCode;
	}

	public List<Integer> getLabelIds() {
		return labelIds;
	}

	public void setLabelIds(List<Integer> labelIds) {
		this.labelIds = labelIds;
	}
}
