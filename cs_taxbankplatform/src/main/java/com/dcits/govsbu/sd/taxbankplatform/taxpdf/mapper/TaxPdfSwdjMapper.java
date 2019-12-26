package com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfSwdjEntity;

@Repository
public interface TaxPdfSwdjMapper extends BaseMapper<TaxPdfSwdjEntity, String> {
	public TaxPdfSwdjEntity findByDjxh(Map<String ,Object>params);
}
