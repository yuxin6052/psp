package com.yf.psp.db.postgres.company;

import java.util.Date;

public class TblCompany {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.company_id
     *
     * @mbggenerated
     */
    private Long companyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.company_name
     *
     * @mbggenerated
     */
    private String companyName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.company_qualification
     *
     * @mbggenerated
     */
    private String companyQualification;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.unified_social_credit_code
     *
     * @mbggenerated
     */
    private String unifiedSocialCreditCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.register_area
     *
     * @mbggenerated
     */
    private String registerArea;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.address_nj
     *
     * @mbggenerated
     */
    private String addressNj;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.register_capital
     *
     * @mbggenerated
     */
    private String registerCapital;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.legal_person
     *
     * @mbggenerated
     */
    private String legalPerson;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.legal_person_identity_no
     *
     * @mbggenerated
     */
    private String legalPersonIdentityNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.legal_person_authorizer
     *
     * @mbggenerated
     */
    private String legalPersonAuthorizer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.authorizer_phone_no
     *
     * @mbggenerated
     */
    private String authorizerPhoneNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.authorizer_email
     *
     * @mbggenerated
     */
    private String authorizerEmail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.business_scope
     *
     * @mbggenerated
     */
    private String businessScope;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.status
     *
     * @mbggenerated
     */
    private Short status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.audit_status
     *
     * @mbggenerated
     */
    private Short auditStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.rela_company_id
     *
     * @mbggenerated
     */
    private Integer relaCompanyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.delete_flag
     *
     * @mbggenerated
     */
    private Short deleteFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.delete_time
     *
     * @mbggenerated
     */
    private Date deleteTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.company_id
     *
     * @return the value of tbl_company.company_id
     *
     * @mbggenerated
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.company_id
     *
     * @param companyId the value for tbl_company.company_id
     *
     * @mbggenerated
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.company_name
     *
     * @return the value of tbl_company.company_name
     *
     * @mbggenerated
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.company_name
     *
     * @param companyName the value for tbl_company.company_name
     *
     * @mbggenerated
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.company_qualification
     *
     * @return the value of tbl_company.company_qualification
     *
     * @mbggenerated
     */
    public String getCompanyQualification() {
        return companyQualification;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.company_qualification
     *
     * @param companyQualification the value for tbl_company.company_qualification
     *
     * @mbggenerated
     */
    public void setCompanyQualification(String companyQualification) {
        this.companyQualification = companyQualification == null ? null : companyQualification.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.unified_social_credit_code
     *
     * @return the value of tbl_company.unified_social_credit_code
     *
     * @mbggenerated
     */
    public String getUnifiedSocialCreditCode() {
        return unifiedSocialCreditCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.unified_social_credit_code
     *
     * @param unifiedSocialCreditCode the value for tbl_company.unified_social_credit_code
     *
     * @mbggenerated
     */
    public void setUnifiedSocialCreditCode(String unifiedSocialCreditCode) {
        this.unifiedSocialCreditCode = unifiedSocialCreditCode == null ? null : unifiedSocialCreditCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.register_area
     *
     * @return the value of tbl_company.register_area
     *
     * @mbggenerated
     */
    public String getRegisterArea() {
        return registerArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.register_area
     *
     * @param registerArea the value for tbl_company.register_area
     *
     * @mbggenerated
     */
    public void setRegisterArea(String registerArea) {
        this.registerArea = registerArea == null ? null : registerArea.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.address_nj
     *
     * @return the value of tbl_company.address_nj
     *
     * @mbggenerated
     */
    public String getAddressNj() {
        return addressNj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.address_nj
     *
     * @param addressNj the value for tbl_company.address_nj
     *
     * @mbggenerated
     */
    public void setAddressNj(String addressNj) {
        this.addressNj = addressNj == null ? null : addressNj.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.register_capital
     *
     * @return the value of tbl_company.register_capital
     *
     * @mbggenerated
     */
    public String getRegisterCapital() {
        return registerCapital;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.register_capital
     *
     * @param registerCapital the value for tbl_company.register_capital
     *
     * @mbggenerated
     */
    public void setRegisterCapital(String registerCapital) {
        this.registerCapital = registerCapital == null ? null : registerCapital.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.legal_person
     *
     * @return the value of tbl_company.legal_person
     *
     * @mbggenerated
     */
    public String getLegalPerson() {
        return legalPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.legal_person
     *
     * @param legalPerson the value for tbl_company.legal_person
     *
     * @mbggenerated
     */
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.legal_person_identity_no
     *
     * @return the value of tbl_company.legal_person_identity_no
     *
     * @mbggenerated
     */
    public String getLegalPersonIdentityNo() {
        return legalPersonIdentityNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.legal_person_identity_no
     *
     * @param legalPersonIdentityNo the value for tbl_company.legal_person_identity_no
     *
     * @mbggenerated
     */
    public void setLegalPersonIdentityNo(String legalPersonIdentityNo) {
        this.legalPersonIdentityNo = legalPersonIdentityNo == null ? null : legalPersonIdentityNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.legal_person_authorizer
     *
     * @return the value of tbl_company.legal_person_authorizer
     *
     * @mbggenerated
     */
    public String getLegalPersonAuthorizer() {
        return legalPersonAuthorizer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.legal_person_authorizer
     *
     * @param legalPersonAuthorizer the value for tbl_company.legal_person_authorizer
     *
     * @mbggenerated
     */
    public void setLegalPersonAuthorizer(String legalPersonAuthorizer) {
        this.legalPersonAuthorizer = legalPersonAuthorizer == null ? null : legalPersonAuthorizer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.authorizer_phone_no
     *
     * @return the value of tbl_company.authorizer_phone_no
     *
     * @mbggenerated
     */
    public String getAuthorizerPhoneNo() {
        return authorizerPhoneNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.authorizer_phone_no
     *
     * @param authorizerPhoneNo the value for tbl_company.authorizer_phone_no
     *
     * @mbggenerated
     */
    public void setAuthorizerPhoneNo(String authorizerPhoneNo) {
        this.authorizerPhoneNo = authorizerPhoneNo == null ? null : authorizerPhoneNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.authorizer_email
     *
     * @return the value of tbl_company.authorizer_email
     *
     * @mbggenerated
     */
    public String getAuthorizerEmail() {
        return authorizerEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.authorizer_email
     *
     * @param authorizerEmail the value for tbl_company.authorizer_email
     *
     * @mbggenerated
     */
    public void setAuthorizerEmail(String authorizerEmail) {
        this.authorizerEmail = authorizerEmail == null ? null : authorizerEmail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.business_scope
     *
     * @return the value of tbl_company.business_scope
     *
     * @mbggenerated
     */
    public String getBusinessScope() {
        return businessScope;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.business_scope
     *
     * @param businessScope the value for tbl_company.business_scope
     *
     * @mbggenerated
     */
    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope == null ? null : businessScope.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.remark
     *
     * @return the value of tbl_company.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.remark
     *
     * @param remark the value for tbl_company.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.status
     *
     * @return the value of tbl_company.status
     *
     * @mbggenerated
     */
    public Short getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.status
     *
     * @param status the value for tbl_company.status
     *
     * @mbggenerated
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.audit_status
     *
     * @return the value of tbl_company.audit_status
     *
     * @mbggenerated
     */
    public Short getAuditStatus() {
        return auditStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.audit_status
     *
     * @param auditStatus the value for tbl_company.audit_status
     *
     * @mbggenerated
     */
    public void setAuditStatus(Short auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.rela_company_id
     *
     * @return the value of tbl_company.rela_company_id
     *
     * @mbggenerated
     */
    public Integer getRelaCompanyId() {
        return relaCompanyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.rela_company_id
     *
     * @param relaCompanyId the value for tbl_company.rela_company_id
     *
     * @mbggenerated
     */
    public void setRelaCompanyId(Integer relaCompanyId) {
        this.relaCompanyId = relaCompanyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.delete_flag
     *
     * @return the value of tbl_company.delete_flag
     *
     * @mbggenerated
     */
    public Short getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.delete_flag
     *
     * @param deleteFlag the value for tbl_company.delete_flag
     *
     * @mbggenerated
     */
    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.delete_time
     *
     * @return the value of tbl_company.delete_time
     *
     * @mbggenerated
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.delete_time
     *
     * @param deleteTime the value for tbl_company.delete_time
     *
     * @mbggenerated
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company.create_time
     *
     * @return the value of tbl_company.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company.create_time
     *
     * @param createTime the value for tbl_company.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}