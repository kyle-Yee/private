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
 * @filename：GetHnYjsf.java
 * @Company:dfwyBank
 * @Created: 2017-4-21下午下午4:19:472:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName GetHnYjsf
 */
public class GetHnYjsf implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(GetHnYjsf.class);
    @Override
    public void run() {
    	System.out.printf( "GetHnYjsf start !");
    }
    /**
     * @Author Zhongyj
     * @date 2017-4-21 下午4:19:54
     * @param parameter
     * @param userId
     * @param taxDataSaveService
     * @param sqxh
     * @param invokeRecodeService
     * @return
     * @throws Exception
     */
    public String getHnYjsf(Parameter parameter,String userId,TaxDataSaveService taxDataSaveService,String sqxh,InvokeRecodeService invokeRecodeService) throws Exception{
    	logger.info("getHnYjsf start !");
    	StringBuffer sb = new StringBuffer("success");
		Gson gson = new Gson();
		
		RequestConfig requestConfig_yjsf = new RequestConfig();
		requestConfig_yjsf.setServiceId(ServiceCommon.SERVICE_ID_YJSF);
		requestConfig_yjsf.setChannelId("dfwyhn");
		requestConfig_yjsf.setPassword(RandomUtil.randomFor8());
		requestConfig_yjsf.setContent(gson.toJson(parameter));
		List<Map<String, String>> yjsf = null;
		try {
			yjsf = Commons.achieveData(requestConfig_yjsf, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
			}
			logger.error("获取企业征收信息表,信息未取到");
			sb.append("获取企业征收信息表,信息未取到");
		}
		taxDataSaveService.insertYjsf(yjsf);
		logger.info("getHnYjsf end !");
		return sb.toString();
    }
}
