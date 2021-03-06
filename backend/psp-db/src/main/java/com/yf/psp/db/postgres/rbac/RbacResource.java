package com.yf.psp.db.postgres.rbac;

import java.util.ArrayList;
import java.util.List;

 

/**
 * Model class of 资源定义表.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
//@JsonInclude(JsonInclude.Include.NON_EMPTY) 
public class RbacResource  {
	
	public static class ResType{
		/* 
		1:url
		2:file
		3:ip
		4:页面(页面可以嵌套页面,菜单，按钮)
		5:页面菜单
		6:页面按钮
		7:...
		*/
		public static final Integer URL = 1;
		public static final Integer  FILE  = 2;
		public static final Integer  IP  = 3;
		public static final Integer  PAGE  = 4;
		public static final Integer  PAGE_MENU  = 5;
		public static final Integer  PAGE_BUTTON  = 6;
		
	}
	//res 字段前缀
	public static class ResPrefix{
		public static final String URL = "url://";
		public static final String  FILE  = "file://";
		public static final String  IP  = "ip://";
		public static final String  PAGE_ELEMENT  = "page:";
	}
	
	public boolean returnIsPageRes() {
		return ResType.PAGE.equals(this.resType) || 
				ResType.PAGE_BUTTON.equals(this.resType) ||
				ResType.PAGE_MENU.equals(this.resType) ;
	}
	//@JsonProperty(value = "index")
	private Integer pageIndex;
	////@JsonProperty(value = "enable")
	//private Short pageEnable;
	/** serialVersionUID. */
	//private static final long serialVersionUID = 1L;

	/** 资源id. */
	//@JsonProperty(value = "id")
	private Integer resId;

	/** 资源类别. */
	//@JsonProperty(value = "type")
	private Integer resType;

	/** res. */
	//@JsonProperty(value = "nameCode")
	private String res;

	/** 父资源. */
	//@JsonIgnore
	private Integer parentResId;
 
    //@JsonIgnore
	private Short deleteFlag; 
    //@JsonIgnore
	private RbacResource parentRes;
	
	private List<RbacResource> children = new ArrayList<>();

	
	
	public RbacResource getParentRes() {
		return parentRes;
	}

	public void setParentRes(RbacResource parentRes) {
		this.parentRes = parentRes;
	}

	public List<RbacResource> getChildren() {
		return children;
	}

	public void setChildren(List<RbacResource> children) {
		this.children = children;
	}

	public Short getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Short deleteFlag) {
		this.deleteFlag = deleteFlag;
	}


	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	/*public Short getPageEnable() {
		return pageEnable;
	}

	public void setPageEnable(Short pageEnable) {
		this.pageEnable = pageEnable;
	}*/

	/**
	 * Set the 资源id.
	 * 
	 * @param resId
	 *            资源id
	 */
	public void setResId(Integer resId) {
		this.resId = resId;
	}

	/**
	 * Get the 资源id.
	 * 
	 * @return 资源id
	 */
	public Integer getResId() {
		return this.resId;
	}

	/**
	 * Set the 资源类别.
	 * 
	 * @param resType
	 *            资源类别
	 */
	public void setResType(Integer resType) {
		this.resType = resType;
	}

	/**
	 * Get the 资源类别.
	 * 
	 * @return 资源类别
	 */
	public Integer getResType() {
		return this.resType;
	}

	/**
	 * Set the res.
	 * 
	 * @param res
	 *            res
	 */
	public void setRes(String res) {
		this.res = res;
	}

	/**
	 * Get the res.
	 * 
	 * @return res
	 */
	public String getRes() {
		return this.res;
	}

	/**
	 * Set the 父资源.
	 * 
	 * @param parentResId
	 *            父资源
	 */
	public void setParentResId(Integer parentResId) {
		this.parentResId = parentResId;
	}

	/**
	 * Get the 父资源.
	 * 
	 * @return 父资源
	 */
	public Integer getParentResId() {
		return this.parentResId;
	}

	 
	 
}
