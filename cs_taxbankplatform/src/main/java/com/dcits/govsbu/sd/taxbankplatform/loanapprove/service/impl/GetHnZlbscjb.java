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
 * @filename：GetHnZlbscjb.java
 * @Company:dfwyBank
 * @Created: 2017-4-21下午下午4:21:242:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName GetHnZlbscjb
 */
public class GetHnZlbscjb implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(GetHnZlbscjb.class);
    @Override
    public void run() {
    	System.out.printf( "GetHnZlbscjb start !");
    }
    /**
     * @Author Zhongyj
     * @date 2017-4-21 下午4:21:33
     * @param parameter
     * @param userId
     * @param taxDataSaveService
     * @param sqxh
     * @param invokeRecodeService
     * @return
     * @throws Exception
     */
    public String getHnZlbscjb(Parameter parameter,String userId,TaxDataSaveService taxDataSaveService,String sqxh,InvokeRecodeService invokeRecodeService) throws Exception{
    	logger.info("getHnZlbscjb start !");
    	StringBuffer sb = new StringBuffer("success");
		Gson gson = new Gson();
		//获取财报申报主表
		RequestConfig requestConfig_zlbscjb = new RequestConfig();
		requestConfig_zlbscjb.setServiceId(ServiceCommon.SERVICE_ID_ZLBSCJB);
		requestConfig_zlbscjb.setChannelId("dfwyhn");
		requestConfig_zlbscjb.setPassword(RandomUtil.randomFor8());
		requestConfig_zlbscjb.setContent(gson.toJson(parameter));
		List<Map<String, String>> zlbscjb = null;
		try {
			zlbscjb = Commons.achieveData(requestConfig_zlbscjb, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=null;
				sb = new StringBuffer();
				sb.append("获取财报申报主表表,信息未取到");
			}
			logger.error("获取财报申报主表表,信息未取到");
		}
		taxDataSaveService.insertZlbscjb(zlbscjb);
		
		logger.info("getHnZlbscjb end !");
		return sb.toString();
    }
}
