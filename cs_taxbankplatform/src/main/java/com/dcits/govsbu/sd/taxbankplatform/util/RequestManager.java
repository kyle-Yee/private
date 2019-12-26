package com.dcits.govsbu.sd.taxbankplatform.util;



import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/***
 * 
 * @author dms
 * @date 2019年1月15日
 * @Description:小工具类
 */

public final class RequestManager {

	private RequestManager() {}

	@SuppressWarnings({"unchecked", "rawtypes" })
	public static HashMap<String, Object> getParams() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Map properties = request.getParameterMap();
		Iterator entries = properties.entrySet().iterator();
		// 遍历Map
		Map.Entry<String,Object> entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = null;
			} else if (valueObj instanceof String[]) { // 如果参数是数组
				String values[] = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					try {
						value = new String(values[i].toString().getBytes("ISO8859-1"),"UTF-8") + ",";
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				
					value = value.substring(0, value.length() - 1);
				// 截取最后一个逗号
			} else {
	
					try {
						value = new String(valueObj.toString().getBytes("ISO8859-1"),"UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				
			}
			returnMap.put(name, value); // 封装返回结果
		}
		// 可以考虑增加一下 授权码验证步骤
		return  (HashMap<String, Object>) returnMap;
	}

}
