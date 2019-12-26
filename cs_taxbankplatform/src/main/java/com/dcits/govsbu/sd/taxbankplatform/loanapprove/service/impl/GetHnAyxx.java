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
 * @filename：GetHnAyxx.java
 * @Company:dfwyBank
 * @Created: 2017-4-21下午下午4:08:162:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName GetHnAyxx
 */
public class GetHnAyxx implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(GetHnAyxx.class);
    @Override
    public void run() {
    	System.out.printf( "GetHnAyxx start !");
    }
    /**
     * @Author Zhongyj
     * @date 2017-4-21 下午4:09:18
     * @param parameter
     * @param userId
     * @param taxDataSaveService
     * @param sqxh
     * @param invokeRecodeService
     * @return
     * @throws Exception
     */
    public String getHnAyxx(Parameter parameter,String userId,TaxDataSaveService taxDataSaveService,String sqxh,InvokeRecodeService invokeRecodeService) throws Exception{
    	logger.info("getHnAyxx start !");
    	StringBuffer sb = new StringBuffer("success");
		Gson gson = new Gson();
		//案源信息
		RequestConfig requestConfig_ayxx = new RequestConfig();
		requestConfig_ayxx.setServiceId(ServiceCommon.SERVICE_ID_AYXX);
		requestConfig_ayxx.setChannelId("dfwyhn");
		requestConfig_ayxx.setPassword(RandomUtil.randomFor8());
		requestConfig_ayxx.setContent(gson.toJson(parameter));
		List<Map<String, String>> ayxx = null;
		try {
			ayxx = Commons.achieveData(requestConfig_ayxx, userId ,sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
			}
			logger.error("案源信息表,信息未取到");
			sb.append("案源信息表,信息未取到");
		}
		taxDataSaveService.insertAyxx(ayxx);
		
		logger.info("getHnYbqylrb end !");
		return sb.toString();
    }
}
