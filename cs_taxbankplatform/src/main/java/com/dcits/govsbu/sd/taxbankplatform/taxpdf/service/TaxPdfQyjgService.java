package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.qytzf.model.TaxQytzfEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfQyjgEntity;

public interface TaxPdfQyjgService {
	public TaxPdfQyjgEntity findByDjxh(Map<String,Object> params);
	//获取股东信息
	public List<TaxQytzfEntity> findByUserid(Map<String,Object> params);
}
