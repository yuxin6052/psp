package com.yf.psp.staticdata.rbac;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yf.psp.db.postgres.rbac.RbacPrivilege;
import com.yf.psp.db.postgres.rbac.RbacResource;
import com.yf.psp.db.postgres.rbac.RbacRole;
@JsonInclude(JsonInclude.Include.NON_EMPTY) 
public class PageResource { 
		//private Integer index; 
 
		//private Integer id; 
 
		private String nameCode;
		
		private Short enable;
		@JsonIgnore
		private Integer resType;
		
		private List<PageResource> children;
		
		
		public static class Enable{
			/* 
			0:不可见
			1:可见可用
			2:可见不可用
			对
			4:页面(页面可以嵌套页面,菜单，按钮)
			5:页面菜单
			6:页面按钮 有效
			*/
			public static final Short VIEWABLE_DISABLE  = 2; 	
			public static final Short ENABLE = 1;
			public static final Short DISABLE  = 0; 		
		}

		public List<PageResource> getChildren() {
			return children;
		}

		public void setChildren(List<PageResource> children) {
			this.children = children;
		}
		
		 

		public static PageResource fromRbacResource(RbacRole role,RbacResource res) {			 
			PageResource ret = null;
			Short enable = null;
			if(RbacResource.ResType.PAGE_BUTTON.equals(res.getResType())) {
				RbacPrivilege pri = RbacPrivilege.getRbacPrivilege(role,res);
				if(pri.canRead()) {
					if(pri.canWrite()) {
						enable = Enable.ENABLE;
					}else {
						enable = Enable.VIEWABLE_DISABLE;
					}
				}else {
					enable =  Enable.DISABLE;
				} 
			}
			ret = new PageResource(/*res.getPageIndex(),res.getResId(),*/res.getResType(),res.getRes(),enable);
			if(res.getChildren()==null) {
				return ret;
			}
			for(RbacResource ch : res.getChildren()) {
				if(ret.children==null) {
					ret.children = new ArrayList<>();
				}
				//此处不用考虑 栈溢出情况，因为用户的某一个资源必定嵌套深度不多 
				ret.children.add(fromRbacResource(role,ch));
			}
			return ret;
		}
		
		/*public static List<PageResource> fromRbacResourceList(RbacRole role,List<RbacResource> resList) {
			 
		}*/

		
		public PageResource(/*Integer index, Integer id,*/ Integer resType,String nameCode, Short enable) {
			super();
			//this.index = index;
			//this.id = id;
			this.resType = resType;
			this.nameCode = nameCode;
			this.enable = enable;
		}

		public Integer getResType() {
			return resType;
		}

		public void setResType(Integer resType) {
			this.resType = resType;
		}

		public Short getEnable() {
			return enable;
		}

		public void setEnable(Short enable) {
			this.enable = enable;
		}

		/*	public Integer getIndex() {
			return index;
		}

		public void setIndex(Integer index) {
			this.index = index;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}*/

		public String getNameCode() {
			return nameCode;
		}

		public void setNameCode(String nameCode) {
			this.nameCode = nameCode;
		}
		
		
}
