package com.yf.psp.db.postgres.company.dao;

import com.yf.psp.db.postgres.company.TblCompany;
import com.yf.psp.db.util.plugin.Page;
import com.yf.psp.db.util.plugin.PageByPageNo;

import java.util.List;


public interface TblCompanyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_company
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long companyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_company
     *
     * @mbggenerated
     */
    int insert(TblCompany record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_company
     *
     * @mbggenerated
     */
    int insertSelective(TblCompany record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_company
     *
     * @mbggenerated
     */
    TblCompany selectByPrimaryKey(Long companyId);
    List<TblCompany> listByPage(Page p);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_company
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TblCompany record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_company
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TblCompany record);
}