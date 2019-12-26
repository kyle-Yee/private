package com.dcits.govsbu.sd.taxbankplatform.dataExchange.service;

import com.dcits.govsbu.sd.taxbankplatform.dataExchange.common.request.RequestConfig;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.TirIpPackage;

/**
 * 功能:
 * 请求服务
 * @author Administrator
 */
public interface RequestService {
	
	public TirIpPackage requestInfo(RequestConfig requestConfig);
}
