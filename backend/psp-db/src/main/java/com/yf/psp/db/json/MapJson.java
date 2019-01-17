package com.yf.psp.db.json;

import java.util.HashMap;
import java.util.Map;
/**
 * Mp<String,Object> 对象转换成 postgres json 字段的实体类
 * @author wangtao
 *
 */
public class MapJson implements MyJsonBase{
	
	Map<String,Object> mapData ;
	
	public MapJson() {
		
	}
	
	public MapJson(Map<String,Object> mapData ) {
		this.mapData = mapData;
	}

	public Map<String, Object> getMapData() {
		return mapData;
	}
	
	public void put(String key,Object value) {
		if(this.mapData==null) {
			this.mapData = new HashMap<>();
		}
		this.mapData.put(key, value);
	}

	public void setMapData(Map<String, Object> mapData) {
		this.mapData = mapData;
	}

	public Long getLong(String argName){
		if(this.mapData==null){
			return null;
		}
		Number nm = (Number) this.mapData.get(argName);
		if(nm==null){
			return null;
		}
		if(nm instanceof Long){
			return (Long) nm;
		}else{
			return nm.longValue();
		}
	}
	
	public Integer getInt(String argName){
		if(this.mapData==null){
			return null;
		}
		Number nm = (Number) this.mapData.get(argName);
		if(nm==null){
			return null;
		}
		if(nm instanceof Integer){
			return (Integer) nm;
		}else{
			return nm.intValue();
		}
	}
	
	@Override
	public String toJsonStr() {
		if(this.mapData==null){
			return null;
		}
		return JsonUtil.toJsonString(this.mapData);
	}

	@Override
	public MyJsonBase fromJsonStr(String json) {
		if(json==null){
			return null;
		}
		this.mapData = JsonUtil.toObject(json, HashMap.class);
		return this;
		
	}

}
