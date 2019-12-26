package com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.govsbu.sd.taxbankplatform.dataExchange.common.ServiceCommon;
import com.dcits.govsbu.sd.taxbankplatform.datastorage.service.TaxDataSaveService;
import com.dcits.govsbu.sd.taxbankplatform.invokeRecord.service.InvokeRecodeService;
import com.dcits.govsbu.sd.taxbankplatform.sendmessage.templet.RandomUtil;
import com.east.microsilver.common.Commons;
import com.east.microsilver.common.request.Parameter;
import com.east.microsilver.common.request.RequestConfig;
import com.google.gson.Gson;
/**
 * @versions:1.0 
 * @filename：GetHnNsrzgxxjgb.java
 * @Company:dfwyBank
 * @Created: 2017-4-21下午下午4:10:282:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName GetHnNsrzgxxjgb
 */
public class GetHnNsrzgxxjgb implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(GetHnNsrzgxxjgb.class);
    @Override
    public void run() {
    	System.out.printf( "GetHnNsrzgxxjgb start !");
    }
    /**
     * 
     * @Author Zhongyj
     * @date 2017-4-21 下午4:10:45
     * @param parameter
     * @param userId
     * @param taxDataSaveService
     * @param sqxh
     * @param invokeRecodeService
     * @return
     * @throws Exception
     */
    public String getHnNsrzgxxjgb(Parameter parameter,String userId,TaxDataSaveService taxDataSaveService,String sqxh,InvokeRecodeService invokeRecodeService) throws Exception{
    	logger.info("getHnNsrzgxxjgb start !");
    	StringBuffer sb = new StringBuffer("success");
		Gson gson = new Gson();
		
		//获取纳税人资格信息结果表
		RequestConfig requestConfig_nsrzgxxjgb = new RequestConfig();
		requestConfig_nsrzgxxjgb.setServiceId(ServiceCommon.SERVICE_ID_NSRZGXXJGB);
		requestConfig_nsrzgxxjgb.setChannelId("dfwyhn");
		requestConfig_nsrzgxxjgb.setPassword(RandomUtil.randomFor8());
		requestConfig_nsrzgxxjgb.setContent(gson.toJson(parameter));
		List<Map<String, String>> nsrzgxxjgb = null;
		try {
			nsrzgxxjgb = Commons.achieveData(requestConfig_nsrzgxxjgb, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
			}
			logger.error("获取纳税人资格信息结果表表,信息未取到");
			sb.append("获取纳税人资格信息结果表表,信息未取到");
		}
		taxDataSaveService.insertNsrzgxxjgb(nsrzgxxjgb);
		
		logger.info("getHnNsrzgxxjgb end !");
		return sb.toString();
    }
}
