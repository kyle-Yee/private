package com.east.microsilver.webservice;

import javax.jws.WebService;

import com.east.microsilver.common.request.RequestConfig;
import com.east.microsilver.message.TirIpPackage;
 
/**
 * 功能:湖南业务请求接口
 * @author Administrator
 */
@WebService 
public interface MicroServiceHnPort {
	//请求业务方法
	public TirIpPackage Process(RequestConfig requestConfig); 
	//TODO
}
