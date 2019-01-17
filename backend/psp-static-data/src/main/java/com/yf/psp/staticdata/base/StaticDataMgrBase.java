package com.yf.psp.staticdata.base;


import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author WangTao
 *
 * @param <T>
 */
public abstract class StaticDataMgrBase {
	 
	
	private String nodeId;

	private Map<String, ServiceBase<?>> mapSvc = new HashMap<String, ServiceBase<?>>();
	
	private Map<Integer, ServiceBase<?>> orderMapSvc = new HashMap<Integer, ServiceBase<?>>();
	

	private boolean quit = false;// 记住此处是非包装类
	
	private Long flag = -1L;
 
	public abstract void afterGetAllTables(); 
	
	private void  afterGetAllTables1() {
		this.afterGetAllTables();
		for (Map.Entry<String, ServiceBase<?>> et : this.mapSvc.entrySet()) {
			if (et.getValue() != null) {
				et.getValue().clear();
			}
		}
	}

	public Long getFlag() {
		return flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}


	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public Map<String, ServiceBase<?>> getMapSvc() {
		return mapSvc;
	}

	public void setMapSvc(Map<String, ServiceBase<?>> mapSvc) {
		this.mapSvc = mapSvc;
	}

	/**
	 * 
	 * @param tab 数据表名字
	 * @param sb  对应的加载服务对象
	 * @param order 数据表的加载顺序
	 */
	public void addServiceBase(String tab, ServiceBase<?> sb,Integer order) {
		this.mapSvc.put(tab, sb);
		sb.setTableName(tab);
		orderMapSvc.put(order, sb);
		sb.setOrder(order);
	}

 
	
 

	 

	public void run() {
		 
		for (Map.Entry<String, ServiceBase<?>> et : this.mapSvc.entrySet()) {
			if (et.getValue() != null) {
				et.getValue().doJob();
			}
		}
		afterGetAllTables1();
		 /*new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					update();
				} catch (Exception e) {//catch 所有异常，日志告警，并不退出
					e.printStackTrace();
					log.error("static data update fatal");
				} 
			}
		}).start();
		log.debug("start static update thread");*/
	}
}
