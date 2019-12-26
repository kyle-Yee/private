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
 * @filename：GetHnSbxx.java
 * @Company:dfwyBank
 * @Created: 2017-4-21下午下午4:14:392:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName GetHnSbxx
 */
public class GetHnSbxx implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(GetHnSbxx.class);
    @Override
    public void run() {
    	System.out.printf( "GetHnSbxx start !");
    }
    /**
     * @Author Zhongyj
     * @date 2017-4-21 下午4:14:47
     * @param parameter
     * @param userId
     * @param taxDataSaveService
     * @param sqxh
     * @param invokeRecodeService
     * @return
     * @throws Exception
     */
    public String getHnSbxx(Parameter parameter,String userId,TaxDataSaveService taxDataSaveService,String sqxh,InvokeRecodeService invokeRecodeService) throws Exception{
    	logger.info("getHnSbxx start !");
    	StringBuffer sb = new StringBuffer("success");
		Gson gson = new Gson();
		//获取企业申报信息
		RequestConfig requestConfig_sbxx = new RequestConfig();
		requestConfig_sbxx.setServiceId(ServiceCommon.SERVICE_ID_SBXX);
		requestConfig_sbxx.setChannelId("dfwyhn");
		requestConfig_sbxx.setPassword(RandomUtil.randomFor8());
		requestConfig_sbxx.setContent(gson.toJson(parameter));
		List<Map<String, String>> sbxx = null;
    	try {
			sbxx = Commons.achieveData(requestConfig_sbxx, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
			}
			logger.error("获取企业申报信息表,信息未取到");
			sb.append("获取企业申报信息表,信息未取到");
		}
		taxDataSaveService.insertSbxx(sbxx);
		logger.info("getHnSbxx end !");
		return sb.toString();
    }
}
