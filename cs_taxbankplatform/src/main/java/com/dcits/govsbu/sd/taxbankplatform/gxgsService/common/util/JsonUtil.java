/**
 * <p>Description: </p>
 * <p>versions:1.0 
 * <p>file name：JsonUtil.java
 * <p>Company:dfwyBank</p>
 * <p>@author: Zhongyj 
 * <p>Created: 2018-3-3下午上午10:15:232:57:43 
 * <p>department:深圳IT部门  
 * <p>Copyright Copyright (c) dfwy. All rights reserved.</p>
 */
package com.dcits.govsbu.sd.taxbankplatform.gxgsService.common.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * @versions:1.0 
 * @filename：JsonUtil.java
 * @Company:dfwyBank
 * @Created: 2018-3-3下午上午10:15:232:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName JsonUtil
 */
public class JsonUtil {
	/**
	 * Json数据转成 Map数据
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> JsonToMap(String json){
		
		return JSON.parseObject(json, Map.class);
	}
	
	
	/**
	 * Map数据转成 Json 数据
	 * @param object
	 * @return
	 */
	public static String MapToJson(Object object){
		
		return JSON.toJSONString(object);
	}
}
