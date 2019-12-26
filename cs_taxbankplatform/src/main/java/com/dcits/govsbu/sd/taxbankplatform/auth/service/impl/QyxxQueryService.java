package com.dcits.govsbu.sd.taxbankplatform.auth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace="http://service.webservice.wbjh.dfwy.com/")
public interface QyxxQueryService {
	Boolean QyxxQuery(Map<String, Object> paramMap);//参数

}
