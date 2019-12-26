package com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfCbxxEntity;

@Repository
public interface TaxPdfCbxxMapper extends BaseMapper<TaxPdfCbxxEntity, String> {
	public TaxPdfCbxxEntity findByDjxh(Map<String,Object> params);
}
