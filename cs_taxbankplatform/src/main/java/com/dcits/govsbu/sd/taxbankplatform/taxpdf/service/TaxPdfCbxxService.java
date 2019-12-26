package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service;

import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfCbxxEntity;

public interface TaxPdfCbxxService {
	public TaxPdfCbxxEntity findByDjxh(Map<String,Object> params);
}
