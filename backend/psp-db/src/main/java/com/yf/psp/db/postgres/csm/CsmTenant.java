package com.yf.psp.db.postgres.csm;


import java.sql.Timestamp;

/**
 * Model class of 租户.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class CsmTenant  {
	public static class  TenantType{
	//租户类型 1:标准版， 2：专业版,3:霍尼韦尔默认租户,为3的只有一个租户
		public static final Short STANDARD = 1;
		public static final Short PROFESSIONAL = 2;
		public static final Short ADMIN = 3;
		
	}
	public static class  TenantID{		 
		public static final Long XX = -1L;
	}

	/** 租户id. */
	private Long tenantId;

	/** 租户名. */
	private String tenantName;

	/** tenant_type. */
	private Short tenantType;

	/** responsible_person. */
	private String responsiblePerson;

	/** tel_no. */
	private String telNo;

	/** tenant_abbreviation. */
	private String tenantAbbreviation;

	/** create_time. */
	private Timestamp createTime;

	/** update_time. */
	private Timestamp updateTime;

	/** delete_flag. */
	private Short deleteFlag;

	/** delete_time. */
	private Timestamp deleteTime;

 
 

	/**
	 * Set the 租户id.
	 * 
	 * @param tenantId
	 *            租户id
	 */
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * Get the 租户id.
	 * 
	 * @return 租户id
	 */
	public Long getTenantId() {
		return this.tenantId;
	}

	/**
	 * Set the 租户名.
	 * 
	 * @param tenantName
	 *            租户名
	 */
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	/**
	 * Get the 租户名.
	 * 
	 * @return 租户名
	 */
	public String getTenantName() {
		return this.tenantName;
	}

	/**
	 * Set the tenant_type.
	 * 
	 * @param tenantType
	 *            tenant_type
	 */
	public void setTenantType(Short tenantType) {
		this.tenantType = tenantType;
	}

	/**
	 * Get the tenant_type.
	 * 
	 * @return tenant_type
	 */
	public Short getTenantType() {
		return this.tenantType;
	}

	/**
	 * Set the responsible_person.
	 * 
	 * @param responsiblePerson
	 *            responsible_person
	 */
	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	/**
	 * Get the responsible_person.
	 * 
	 * @return responsible_person
	 */
	public String getResponsiblePerson() {
		return this.responsiblePerson;
	}

	/**
	 * Set the tel_no.
	 * 
	 * @param telNo
	 *            tel_no
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	/**
	 * Get the tel_no.
	 * 
	 * @return tel_no
	 */
	public String getTelNo() {
		return this.telNo;
	}

	/**
	 * Set the tenant_abbreviation.
	 * 
	 * @param tenantAbbreviation
	 *            tenant_abbreviation
	 */
	public void setTenantAbbreviation(String tenantAbbreviation) {
		this.tenantAbbreviation = tenantAbbreviation;
	}

	/**
	 * Get the tenant_abbreviation.
	 * 
	 * @return tenant_abbreviation
	 */
	public String getTenantAbbreviation() {
		return this.tenantAbbreviation;
	}

	/**
	 * Set the create_time.
	 * 
	 * @param createTime
	 *            create_time
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * Get the create_time.
	 * 
	 * @return create_time
	 */
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	/**
	 * Set the update_time.
	 * 
	 * @param updateTime
	 *            update_time
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * Get the update_time.
	 * 
	 * @return update_time
	 */
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * Set the delete_flag.
	 * 
	 * @param deleteFlag
	 *            delete_flag
	 */
	public void setDeleteFlag(Short deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * Get the delete_flag.
	 * 
	 * @return delete_flag
	 */
	public Short getDeleteFlag() {
		return this.deleteFlag;
	}

	/**
	 * Set the delete_time.
	 * 
	 * @param deleteTime
	 *            delete_time
	 */
	public void setDeleteTime(Timestamp deleteTime) {
		this.deleteTime = deleteTime;
	}

	/**
	 * Get the delete_time.
	 * 
	 * @return delete_time
	 */
	public Timestamp getDeleteTime() {
		return this.deleteTime;
	}

	  

}