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
 * @filename：GetHnXgmnsr.java
 * @Company:dfwyBank
 * @Created: 2017-4-21下午下午4:15:282:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName GetHnXgmnsr
 */
public class GetHnXgmnsr implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(GetHnYnbsr.class);
    @Override
    public void run() {
    	System.out.printf( "GetHnYnbsr start !");
    }
    /**
     * @Author Zhongyj
     * @date 2017-4-21 下午4:15:42
     * @param parameter
     * @param userId
     * @param taxDataSaveService
     * @param sqxh
     * @param invokeRecodeService
     * @return
     * @throws Exception
     */
    public String getHnXgmnsr(Parameter parameter,String userId,TaxDataSaveService taxDataSaveService,String sqxh,InvokeRecodeService invokeRecodeService) throws Exception{
    	logger.info("getHnXgmnsr start !");
    	StringBuffer sb = new StringBuffer("success");
		Gson gson = new Gson();
		
		//获取小规模纳税人申报明细主表
		RequestConfig requestConfig_xgmnsr = new RequestConfig();
		requestConfig_xgmnsr.setServiceId(ServiceCommon.SERVICE_ID_XGMNSR);
		requestConfig_xgmnsr.setChannelId("dfwyhn");
		requestConfig_xgmnsr.setPassword(RandomUtil.randomFor8());
		requestConfig_xgmnsr.setContent(gson.toJson(parameter));
		List<Map<String, String>> xgmnsr = null;
		try {
			xgmnsr = Commons.achieveData(requestConfig_xgmnsr, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
			}
			logger.error("获取小规模纳税人申报明细主表表,信息未取到");
			sb.append("获取小规模纳税人申报明细主表表,信息未取到");
		}
		taxDataSaveService.insertXgmnsr(xgmnsr);
		
		logger.info("getHnXgmnsr end !");
		return sb.toString();
    }
}
