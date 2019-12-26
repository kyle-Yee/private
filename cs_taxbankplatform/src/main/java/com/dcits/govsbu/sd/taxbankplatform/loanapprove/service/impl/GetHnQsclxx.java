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
 * @filename：GetHnQsclxx.java
 * @Company:dfwyBank
 * @Created: 2017-4-21下午下午4:11:322:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName GetHnQsclxx
 */
public class GetHnQsclxx implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(GetHnQsclxx.class);
    @Override
    public void run() {
    	System.out.printf( "GetHnQsclxx start !");
    }
    /**
     * @Author Zhongyj
     * @date 2017-4-21 下午4:12:00
     * @param parameter
     * @param userId
     * @param taxDataSaveService
     * @param sqxh
     * @param invokeRecodeService
     * @return
     * @throws Exception
     */
    public String getHnQsclxx(Parameter parameter,String userId,TaxDataSaveService taxDataSaveService,String sqxh,InvokeRecodeService invokeRecodeService) throws Exception{
    	logger.info("getHnQsclxx start !");
    	StringBuffer sb = new StringBuffer("success");
		Gson gson = new Gson();
		//获取欠税信息
		RequestConfig requestConfig_qsclxx = new RequestConfig();
		requestConfig_qsclxx.setServiceId(ServiceCommon.SERVICE_ID_QSCLXX);
		requestConfig_qsclxx.setChannelId("dfwyhn");
		requestConfig_qsclxx.setPassword(RandomUtil.randomFor8());
		requestConfig_qsclxx.setContent(gson.toJson(parameter));
		List<Map<String, String>> qsclxx = null;
		try {
			qsclxx = Commons.achieveData(requestConfig_qsclxx, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
			}
			logger.error("获取欠税信息表,信息未取到");
			sb.append("获取欠税信息表,信息未取到");
		}
		taxDataSaveService.insertQsclxx(qsclxx);
		
		logger.info("getHnQsclxx end !");
		return sb.toString();
    }
}
