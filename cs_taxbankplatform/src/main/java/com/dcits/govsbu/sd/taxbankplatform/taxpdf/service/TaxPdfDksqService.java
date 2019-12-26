package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service;

import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfDksqEntity;

/**
 * 
 * @author Administrator
 *
 */
public interface TaxPdfDksqService {
	//获取贷款申请信息
	public TaxPdfDksqEntity findById(Map<String,Object> params);
	
	public String getHkfsName(String id);
}
