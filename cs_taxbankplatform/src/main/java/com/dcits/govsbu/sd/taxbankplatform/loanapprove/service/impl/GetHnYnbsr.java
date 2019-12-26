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
 * @filename：GetHnYnbsr.java
 * @Company:dfwyBank
 * @Created: 2017-4-21下午下午4:20:282:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName GetHnYnbsr
 */
public class GetHnYnbsr implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(GetHnYnbsr.class);
    @Override
    public void run() {
    	System.out.printf( "GetHnYnbsr start !");
    }
    /**
     * @Author Zhongyj
     * @date 2017-4-21 下午4:20:38
     * @param parameter
     * @param userId
     * @param taxDataSaveService
     * @param sqxh
     * @param invokeRecodeService
     * @return
     * @throws Exception
     */
    public String getHnYnbsr(Parameter parameter,String userId,TaxDataSaveService taxDataSaveService,String sqxh,InvokeRecodeService invokeRecodeService) throws Exception{
    	logger.info("getHnYnbsr start !");
    	StringBuffer sb = new StringBuffer("success");
		Gson gson = new Gson();
		//获取一般纳税人申报明细主表
		RequestConfig requestConfig_ynbsr = new RequestConfig();
		requestConfig_ynbsr.setServiceId(ServiceCommon.SERVICE_ID_YNBSR);
		requestConfig_ynbsr.setChannelId("dfwyhn");
		requestConfig_ynbsr.setPassword(RandomUtil.randomFor8());
		requestConfig_ynbsr.setContent(gson.toJson(parameter));
		List<Map<String, String>> ynbsr = null;
		try {
			ynbsr = Commons.achieveData(requestConfig_ynbsr, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
			}
			logger.error("获取一般纳税人申报明细主表表,信息未取到");
			sb.append("获取一般纳税人申报明细主表表,信息未取到");
		}
		taxDataSaveService.insertYnbsr(ynbsr);
		
		logger.info("getHnYnbsr end !");
		return sb.toString();
    }
    
}
