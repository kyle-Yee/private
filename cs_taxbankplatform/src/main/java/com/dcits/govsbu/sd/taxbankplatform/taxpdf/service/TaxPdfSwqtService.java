package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service;

import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfSwqtEntity;

public interface TaxPdfSwqtService {
	public TaxPdfSwqtEntity findByDjxh(Map<String,Object> params);
	//获取企业欠税信息
	public TaxPdfSwqtEntity findQsxx(String sqxh);
}
