package com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfGsdjEntity;

@Repository
public interface TaxPdfGsdjMapper extends BaseMapper<TaxPdfGsdjEntity, String> {
	public List<TaxPdfGsdjEntity> findByDjxh(Map<String,Object> params);
}
