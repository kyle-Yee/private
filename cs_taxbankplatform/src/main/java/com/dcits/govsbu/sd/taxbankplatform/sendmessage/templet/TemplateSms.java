package com.dcits.govsbu.sd.taxbankplatform.sendmessage.templet;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 模板短信DEMO
 *
 */
public class TemplateSms {
//	APP_ID;//应用ID------登录平台在应用设置可以找到
//	APP_SECRET;//应用secret-----登录平台在应用设置可以找到
//	ACCESS_TOKEN;//访问令牌AT-------CC模式，AC模式都可，推荐CC模式获取令牌
//	TEMPLATE_ID;//模板ID
	
	public static String sendSms(Map<String, Object> map, String APP_ID, String APP_SECRET, String ACCESS_TOKEN, String TEMPLATE_ID) throws Exception {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String timestamp = dateFormat.format(date);
		Gson gson = new Gson();
		Map<String, Object> map1 = new HashMap<String, Object>();
		String tel = null;
		//这里存放模板参数，如果模板没有参数直接用template_param={}s
		if ("loanapproveresult".equals(map.get("code"))) {
			map1.put("param1", map.get("qymc"));
			map1.put("param2", (int)map.get("year"));
			map1.put("param3", (int)map.get("month"));
			map1.put("param4", (int)map.get("day"));
			map1.put("param5", map.get("sqyh"));
			tel = (String) map.get("phonenumber");
		}else if ("createuser".equals(map.get("code"))){
			map1.put("param1", map.get("accountName"));
			map1.put("param2", map.get("passWord"));
			tel = (String) map.get("phonenumber");
		}else if("dataRefresh".equals(map.get("code"))){
			tel = (String) map.get("phonenumber");
		}

		String template_param = gson.toJson(map1);
		String postUrl = "http://api.189.cn/v2/emp/templateSms/sendSms";		
		
		String postEntity = "app_id=" + APP_ID + "&access_token="
				+ ACCESS_TOKEN + "&acceptor_tel=" + tel + "&template_id="
				+ TEMPLATE_ID + "&template_param=" + template_param
				+ "&timestamp=" + URLEncoder.encode(timestamp, "utf-8");
		String resJson = "";
		String idertifier = null;
		Map<String, String> map2 =null;
		try {
			resJson = HttpInvoker.httpPost1(postUrl, null, postEntity);
			map2 = gson.fromJson(resJson,
					new TypeToken<Map<String, String>>() {
					}.getType());
			idertifier = map2.get("idertifier").toString();
		} catch (IOException e) {
			System.err.println(resJson);
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(resJson);
			e.printStackTrace();
		}
		System.err.println(resJson);
		return idertifier;

	}
}
