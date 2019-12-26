package com.dcits.govsbu.sd.taxbankplatform.gxgsService.common.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;

public class MaptoJsonUtil {
	
	/**
	 * Json数据转成 Map数据
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> Json2Map(String json){
		return JSON.parseObject(json, Map.class);
	}
	
	
	/**
	 * Map数据转成 Json 数据
	 * @param object
	 * @return
	 */
	public static String Map2Json(Object object){
		return JSON.toJSONString(object);
	}
	
}
