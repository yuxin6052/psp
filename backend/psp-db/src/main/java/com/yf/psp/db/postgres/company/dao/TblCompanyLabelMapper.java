package com.yf.psp.db.postgres.company.dao;

import com.yf.psp.db.postgres.company.TblCompanyLabel;

import java.util.List;


public interface TblCompanyLabelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_company_label
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long labelId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_company_label
     *
     * @mbggenerated
     */
    int insert(TblCompanyLabel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_company_label
     *
     * @mbggenerated
     */
    int insertSelective(TblCompanyLabel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_company_label
     *
     * @mbggenerated
     */
    TblCompanyLabel selectByPrimaryKey(Long labelId);
    List<TblCompanyLabel> list( );

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_company_label
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TblCompanyLabel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_company_label
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TblCompanyLabel record);
}