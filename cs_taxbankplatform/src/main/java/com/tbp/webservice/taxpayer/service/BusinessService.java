package com.tbp.webservice.taxpayer.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.TirIpPackage;

/**
 * 功能:
 * 发送业务请求服务接口
 * @author Administrator
 */
@WebService 
public interface BusinessService {
	//业务信息
	public String BusinessInfo(@WebParam(name = "TirIpPackage")TirIpPackage tirIpPackage); 
}
