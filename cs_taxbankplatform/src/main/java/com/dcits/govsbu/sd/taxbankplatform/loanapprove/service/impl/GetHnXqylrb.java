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
 * @filename：GetHnXqylrb.java
 * @Company:dfwyBank
 * @Created: 2017-4-21下午下午4:16:162:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName GetHnXqylrb
 */
public class GetHnXqylrb implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(GetHnXqylrb.class);
    @Override
    public void run() {
    	System.out.printf( "GetHnXqylrb start !");
    }
    /**
     * @Author Zhongyj
     * @date 2017-4-21 下午4:16:29
     * @param parameter
     * @param userId
     * @param taxDataSaveService
     * @param sqxh
     * @param invokeRecodeService
     * @return
     * @throws Exception
     */
    public String getHnXqylrb(Parameter parameter,String userId,TaxDataSaveService taxDataSaveService,String sqxh,InvokeRecodeService invokeRecodeService) throws Exception{
    	logger.info("getHnXqylrb start !");
    	StringBuffer sb = new StringBuffer("success");
		Gson gson = new Gson();
		//获取企业利润表(小企业会计制度)
		RequestConfig requestConfig_xqylrb = new RequestConfig();
		requestConfig_xqylrb.setServiceId(ServiceCommon.SERVICE_ID_XQYLRB);
		requestConfig_xqylrb.setChannelId("dfwyhn");
		requestConfig_xqylrb.setPassword(RandomUtil.randomFor8());
		requestConfig_xqylrb.setContent(gson.toJson(parameter));
		List<Map<String, String>> xqylrb = null;
		try {
			xqylrb = Commons.achieveData(requestConfig_xqylrb, userId, sqxh, invokeRecodeService);
		} catch (Exception e) {
			if("success".equals(sb.toString())){
				sb=new StringBuffer();
			}
			logger.error("获取企业利润表(小企业会计制度)表,信息未取到");
			sb.append("获取企业利润表(小企业会计制度)表,信息未取到");
		}
		taxDataSaveService.insertXqylrb(xqylrb);
		logger.info("getHnXqylrb end !");
		return sb.toString();
    }
}
