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
 * @filename：GetHnYbqyzcfzb.java
 * @Company:dfwyBank
 * @Created: 2017-4-21下午下午4:18:562:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName GetHnYbqyzcfzb
 */
public class GetHnYbqyzcfzb implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(GetHnYbqyzcfzb.class);
    @Override
    public void run() {
    	System.out.printf( "GetHnYbqyzcfzb start !");
    }
    /**
     * @Author Zhongyj
     * @date 2017-4-21 下午4:19:06
     * @param parameter
     * @param userId
     * @param taxDataSaveService
     * @param sqxh
     * @param invokeRecodeService
     * @return
     * @throws Exception
     */
    public String getHnYbqyzcfzb(Parameter parameter,String userId,TaxDataSaveService taxDataSaveService,String sqxh,InvokeRecodeService invokeRecodeService) throws Exception{
    	logger.info("getHnYbqyzcfzb start !");
    	StringBuffer sb = new StringBuffer("success");
		Gson gson = new Gson();
		//获取企业资产负债表(一般企业会计制度)
		RequestConfig requestConfig_ybqyzcfzb = new RequestConfig();
		requestConfig_ybqyzcfzb.setServiceId(ServiceCommon.SERVICE_ID_YBQYZCFZB);
		requestConfig_ybqyzcfzb.setChannelId("dfwyhn");
		requestConfig_ybqyzcfzb.setPassword(RandomUtil.randomFor8());
		requestConfig_ybqyzcfzb.setContent(gson.toJson(parameter));
		List<Map<String, String>> ybqyzcfzb = null;
		try {
			ybqyzcfzb = Commons.achieveData(requestConfig_ybqyzcfzb, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
			}
			logger.error("获取企业资产负债表(一般企业会计制度)表,信息未取到");
			sb.append("获取企业资产负债表(一般企业会计制度)表,信息未取到");
		}
		taxDataSaveService.insertYbqyzcfzb(ybqyzcfzb);
		
		logger.info("getHnYbqyzcfzb end !");
		return sb.toString();
    }
}
