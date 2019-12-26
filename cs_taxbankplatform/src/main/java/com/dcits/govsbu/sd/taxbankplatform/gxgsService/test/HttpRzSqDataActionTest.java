/**
 * <p>Description: </p>
 * <p>versions:1.0 
 * <p>file name：HttpSXjgTest.java
 * <p>Company:dfwyBank</p>
 * <p>@author: Zhongyj 
 * <p>Created: 2018-3-3下午上午10:43:162:57:43 
 * <p>department:深圳IT部门  
 * <p>Copyright Copyright (c) dfwy. All rights reserved.</p>
 */
package com.dcits.govsbu.sd.taxbankplatform.gxgsService.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.controller.SendHttpPostRequest;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.common.HttpUtils;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.common.util.JsonUtil;

/**
 * @versions:1.0 
 * @filename：HttpSXjgTest.java
 * @Company:dfwyBank
 * @Created: 2018-3-3下午上午10:43:162:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName HttpSXjgTest
 */
public class HttpRzSqDataActionTest {

	/**
	 * @Author Zhongyj
	 * @date 2018-3-3 上午10:43:16
	 * @param args
	 */
	public static void main(String[] args) {
		
		String url = "http://10.10.10.222:8080/tbp/sentRzSqDataAction";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("bh","1234567303555121");
		parameters.put("nsrsbh","123456789");
		parameters.put("cr_qymc","未来科技1");
		parameters.put("cr_nsrsbh","1234567288");
		parameters.put("cr_frsjh","13554716965");
		parameters.put("cr_zjlx","1");
		parameters.put("cr_zjhm","522121111882234567");
		parameters.put("cr_sqsj","2018-03-07");
		parameters.put("cr_rzsj","2018-03-07");
		parameters.put("cr_shjg","rzjg000");
		parameters.put("cr_dbqk","rzjg001");
		parameters.put("at_sqsj","12");
		parameters.put("at_qymc","未来科技");
		parameters.put("at_sqkssj","2018-03-07");
		parameters.put("at_sqjssj","2018-03-07");
		parameters.put("at_sqsyxq","2018-03-07");
		parameters.put("Sqzt","sqjg001");
		parameters.put("Sqjg","初始化");
		parameters.put("at_frxm","王大锤");
		parameters.put("at_frsfz","344533456667765677");
		parameters.put("swjgdm","12345676543");
		parameters.put("bankName","GXJSBANK");
		parameters.put("cpName","GXJS001");
		
		  String params = JsonUtil.MapToJson(parameters);
		  String encoding = "UTF-8";
		  String rusult  = HttpUtils.sendPostMethod(url, params, encoding);
		  System.out.println(">>>>>>>>>>>>1>>>>>>>>>>>."+rusult);
	}

}
