package com.yf.psp.staticdata.rbac;

import java.util.List;

 

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.yf.psp.db.postgres.rbac.RbacPrivilege;
import com.yf.psp.db.postgres.rbac.RbacResource;
import com.yf.psp.db.postgres.rbac.RbacRole;
import com.yf.psp.service.staticdatamgr.RbacResourceStaticDataService;
import com.yf.psp.service.staticdatamgr.RbacRoleStaticDataService;
import com.yf.psp.service.staticdatamgr.StaticDataMgr;

import java.util.Map;
import java.util.ArrayList; 
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap; 
 
 

/**
 * 
 * @author WangTao
 * 静态数据模型建立，非常重要，这里错了，后续业务全错
 * 
 */
@Service 
//@ConditionalOnBean(RbacRoleStaticDataService.class)
public class RoleService  {

	public static Logger logger = LoggerFactory.getLogger(RoleService.class);
	@Autowired
	StaticDataMgr staticDataMgr;//初始化依赖关系定义，不是无效代码，不可删除
	@Autowired
	RbacRoleStaticDataService rbacRoleStaticDataService;
	@Autowired
	RbacResourceStaticDataService rbacResourceStaticDataService;
	
	Map<String/*roleName*/,List<RbacPrivilege>/*根据url 从长到短排序*/> roleUrlMap = new HashMap<>();
	
	Map<String/*roleName*/,List<PageResource>/*根据index 排序，无index 则随机排序*/> rolePageResMap = new HashMap<>();
	
	
	protected PathMatcher pathMatcher = new AntPathMatcher();
	
	private Comparator<RbacResource> resCmp = new Comparator<RbacResource>() {
		@Override
		public int compare(RbacResource o1, RbacResource o2) {
			if(!o1.getResType().equals(o2.getResType())) {
				return o1.getResType() < o2.getResType() ? -1:1;//先按照restype 排序
			}  
			
			if(o1.getPageIndex()==null) {
				if(o2.getPageIndex()==null) {
					return 0;
				}					
				return 1;
			} else {
				if(o2.getPageIndex()==null) {
					return -1;
				}	
				return o1.getPageIndex() < o2.getPageIndex() ? -1:1;
			}
		} 
	};
	
	@PostConstruct
	public void init() {
		Comparator<RbacPrivilege> cmp = new Comparator<RbacPrivilege>() {
			@Override
			public int compare(RbacPrivilege o1, RbacPrivilege o2) {
				return o1.getRbacResource().getRes().length() > o2.getRbacResource().getRes().length() ? -1:1;
			} 
		};
		for(RbacRole it:this.rbacRoleStaticDataService.getList()) {
			Map<String, RbacPrivilege> pri = it.getPrivileges().get(RbacResource.ResType.URL);
			if(pri!=null) {
				List<RbacPrivilege> list = new ArrayList<>();
				this.roleUrlMap.put(it.getRoleName(), list);
				list.addAll(pri.values());
				Collections.sort(list, cmp);//按照url 从长到短排序
			}
			pri = it.getPrivileges().get(RbacResource.ResType.PAGE_MENU);
			if(pri!=null) {
				for(Map.Entry<String, RbacPrivilege> kv:pri.entrySet()) { 
					this.sortPageRes(kv.getValue().getRbacResource().getChildren());
				}
			} 
			pri = it.getPrivileges().get(RbacResource.ResType.PAGE);
			if(pri==null) {
				continue;
			}
			List<RbacResource> tmp = new ArrayList<>();
			for(Map.Entry<String, RbacPrivilege> kv:pri.entrySet()) {
				if(kv.getValue().getRbacResource().getParentResId()==null) {
					tmp.add(kv.getValue().getRbacResource());//顶级元素
				}
				this.sortPageRes(kv.getValue().getRbacResource().getChildren());
			}
			this.sortPageRes(tmp);
			List<PageResource> tmpList1 = new ArrayList<>();
			for(RbacResource rr : tmp) {
				tmpList1.add(PageResource.fromRbacResource(it, rr));
			}
			this.rolePageResMap.put(it.getRoleName(), tmpList1);
		}
		
		 
	}
	

	
	public List<PageResource> getPageInfo(String role){
		return this.rolePageResMap.get(role);
	}
	
 	/**
 	 * 此方法用于判断 角色 roleName 是否能访问url，这样写的好处是避免为 url 写不同的 filter 来判断角色，避免多余重复代码
 	 * @param roleName 角色名
 	 * @param url
 	 * @return
 	 */
	public boolean canAccessUrl(final String roleName,final String url) {
		if(StringUtils.isBlank(roleName) || StringUtils.isBlank(url)) {
			return false;
		}
		RbacRole role = this.rbacRoleStaticDataService.get(roleName);
		if(role == null) {
			return false;
		}
		RbacPrivilege privilege = role.findResource(RbacResource.ResType.URL,url);
		if(privilege != null  ) {
			//精确匹配
			return privilege.canAccess();
		}

		List<RbacPrivilege> listUrl = this.roleUrlMap.get(roleName);
		if(listUrl==null) {
			return false;
		}
		for(RbacPrivilege it:listUrl) {
			if(this.pathMatcher.match(it.getRbacResource().getRes(), url)) {
				return it.canAccess();
			}
		}
		return false;
	}
 

	
	private void sortPageRes(List<RbacResource> res) { 		
		if(res==null || res.isEmpty()) {
			return;
		}
		/*for(RbacResource it:res) {
			System.out.println(it.getParentResId() + ":" + it.getResId());
		}*/
		Collections.sort(res, this.resCmp);  
	}
}
