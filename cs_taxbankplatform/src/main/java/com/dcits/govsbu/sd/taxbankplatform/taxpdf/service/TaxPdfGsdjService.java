package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfGsdjEntity;

public interface TaxPdfGsdjService {
	public List<TaxPdfGsdjEntity> findByDjxh(Map<String,Object> params);
}
