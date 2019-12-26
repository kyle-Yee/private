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
 * @filename：GetHnQyzcfzb.java
 * @Company:dfwyBank
 * @Created: 2017-4-21下午下午4:13:392:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName GetHnQyzcfzb
 */
public class GetHnQyzcfzb implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(GetHnZlbscjb.class);
    @Override
    public void run() {
    	System.out.printf( "GetHnZlbscjb start !");
    }
    /**
     * @Author Zhongyj
     * @date 2017-4-21 下午4:13:53
     * @param parameter
     * @param userId
     * @param taxDataSaveService
     * @param sqxh
     * @param invokeRecodeService
     * @return
     * @throws Exception
     */
    public String getHnQyzcfzb(Parameter parameter,String userId,TaxDataSaveService taxDataSaveService,String sqxh,InvokeRecodeService invokeRecodeService) throws Exception{
    	logger.info("getHnQyzcfzb start !");
    	StringBuffer sb = new StringBuffer("success");
		Gson gson = new Gson();
		//获取企业资产负债表(企业会计制度)
		RequestConfig requestConfig_qyzcfzb = new RequestConfig();
		requestConfig_qyzcfzb.setServiceId(ServiceCommon.SERVICE_ID_QYZCFZB);
		requestConfig_qyzcfzb.setChannelId("dfwyhn");
		requestConfig_qyzcfzb.setPassword(RandomUtil.randomFor8());
		requestConfig_qyzcfzb.setContent(gson.toJson(parameter));
		List<Map<String, String>> qyzcfzb = null;
		try {
			qyzcfzb = Commons.achieveData(requestConfig_qyzcfzb, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
			}
			logger.error("获取企业资产负债表(企业会计制度)表,信息未取到");
			sb.append("获取企业资产负债表(企业会计制度)表,信息未取到");
		}
		taxDataSaveService.insertQyzcfzb(qyzcfzb);
		
		logger.info("getHnQyzcfzb end !");
		return sb.toString();
    }
}
