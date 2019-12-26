package com.dcits.govsbu.sd.taxbankplatform.dataExchange.common;

import com.dcits.govsbu.sd.taxbankplatform.dataExchange.common.request.Parameter;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.common.request.RequestConfig;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.TirIpPackage;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.service.impl.RequestServiceImpl;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RequestServiceImpl rsi = new RequestServiceImpl();
		
		RequestConfig requestConfig = new RequestConfig();
		requestConfig.setServiceId(ServiceCommon.SERVICE_ID_NSRJCXX);
		requestConfig.setPassword("999999");
		Parameter parameter = new Parameter();
		parameter.setNSRSBH("440203199212034398");
		parameter.setLPBM("授权编码");
		
		requestConfig.setParameter(parameter);
		
		TirIpPackage tirIpPackage = rsi.requestInfo(requestConfig);
		System.out.println("响应报文:"+tirIpPackage.getBusinessContent().getSubPackage().get(0));
	}
}
