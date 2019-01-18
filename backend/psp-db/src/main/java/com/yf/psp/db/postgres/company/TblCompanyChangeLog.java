package com.yf.psp.db.postgres.company;

import java.util.Date;

public class TblCompanyChangeLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company_change_log.log_id
     *
     * @mbggenerated
     */
    private Long logId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company_change_log.company_id
     *
     * @mbggenerated
     */
    private Integer companyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company_change_log.company_name
     *
     * @mbggenerated
     */
    private String companyName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company_change_log.update_field
     *
     * @mbggenerated
     */
    private String updateField;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company_change_log.value_before_update
     *
     * @mbggenerated
     */
    private String valueBeforeUpdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company_change_log.value_after_update
     *
     * @mbggenerated
     */
    private String valueAfterUpdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company_change_log.update_reason
     *
     * @mbggenerated
     */
    private String updateReason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company_change_log.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company_change_log.update_user_id
     *
     * @mbggenerated
     */
    private Integer updateUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_company_change_log.update_username
     *
     * @mbggenerated
     */
    private String updateUsername;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company_change_log.log_id
     *
     * @return the value of tbl_company_change_log.log_id
     *
     * @mbggenerated
     */
    public Long getLogId() {
        return logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company_change_log.log_id
     *
     * @param logId the value for tbl_company_change_log.log_id
     *
     * @mbggenerated
     */
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company_change_log.company_id
     *
     * @return the value of tbl_company_change_log.company_id
     *
     * @mbggenerated
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company_change_log.company_id
     *
     * @param companyId the value for tbl_company_change_log.company_id
     *
     * @mbggenerated
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company_change_log.company_name
     *
     * @return the value of tbl_company_change_log.company_name
     *
     * @mbggenerated
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company_change_log.company_name
     *
     * @param companyName the value for tbl_company_change_log.company_name
     *
     * @mbggenerated
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company_change_log.update_field
     *
     * @return the value of tbl_company_change_log.update_field
     *
     * @mbggenerated
     */
    public String getUpdateField() {
        return updateField;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company_change_log.update_field
     *
     * @param updateField the value for tbl_company_change_log.update_field
     *
     * @mbggenerated
     */
    public void setUpdateField(String updateField) {
        this.updateField = updateField == null ? null : updateField.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company_change_log.value_before_update
     *
     * @return the value of tbl_company_change_log.value_before_update
     *
     * @mbggenerated
     */
    public String getValueBeforeUpdate() {
        return valueBeforeUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company_change_log.value_before_update
     *
     * @param valueBeforeUpdate the value for tbl_company_change_log.value_before_update
     *
     * @mbggenerated
     */
    public void setValueBeforeUpdate(String valueBeforeUpdate) {
        this.valueBeforeUpdate = valueBeforeUpdate == null ? null : valueBeforeUpdate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company_change_log.value_after_update
     *
     * @return the value of tbl_company_change_log.value_after_update
     *
     * @mbggenerated
     */
    public String getValueAfterUpdate() {
        return valueAfterUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company_change_log.value_after_update
     *
     * @param valueAfterUpdate the value for tbl_company_change_log.value_after_update
     *
     * @mbggenerated
     */
    public void setValueAfterUpdate(String valueAfterUpdate) {
        this.valueAfterUpdate = valueAfterUpdate == null ? null : valueAfterUpdate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company_change_log.update_reason
     *
     * @return the value of tbl_company_change_log.update_reason
     *
     * @mbggenerated
     */
    public String getUpdateReason() {
        return updateReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company_change_log.update_reason
     *
     * @param updateReason the value for tbl_company_change_log.update_reason
     *
     * @mbggenerated
     */
    public void setUpdateReason(String updateReason) {
        this.updateReason = updateReason == null ? null : updateReason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company_change_log.update_time
     *
     * @return the value of tbl_company_change_log.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company_change_log.update_time
     *
     * @param updateTime the value for tbl_company_change_log.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company_change_log.update_user_id
     *
     * @return the value of tbl_company_change_log.update_user_id
     *
     * @mbggenerated
     */
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company_change_log.update_user_id
     *
     * @param updateUserId the value for tbl_company_change_log.update_user_id
     *
     * @mbggenerated
     */
    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_company_change_log.update_username
     *
     * @return the value of tbl_company_change_log.update_username
     *
     * @mbggenerated
     */
    public String getUpdateUsername() {
        return updateUsername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_company_change_log.update_username
     *
     * @param updateUsername the value for tbl_company_change_log.update_username
     *
     * @mbggenerated
     */
    public void setUpdateUsername(String updateUsername) {
        this.updateUsername = updateUsername == null ? null : updateUsername.trim();
    }
}