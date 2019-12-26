package com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfSwqtEntity;

@Repository
public interface TaxPdfSwqtMapper extends BaseMapper<TaxPdfSwqtEntity, String> {
	public TaxPdfSwqtEntity findByDjxh(Map<String,Object> params);
	//获取企业欠税信息
	public TaxPdfSwqtEntity findQsxx(String sqxh);
}
