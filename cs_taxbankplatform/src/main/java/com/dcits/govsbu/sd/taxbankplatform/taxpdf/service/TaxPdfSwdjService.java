package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service;

import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfSwdjEntity;

public interface TaxPdfSwdjService {
	public TaxPdfSwdjEntity findByDjxh(Map<String,Object> params);
}
