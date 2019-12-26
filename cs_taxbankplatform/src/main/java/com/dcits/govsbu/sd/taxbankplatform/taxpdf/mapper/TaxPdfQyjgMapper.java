package com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.qytzf.model.TaxQytzfEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfQyjgEntity;

@Repository
public interface TaxPdfQyjgMapper extends BaseMapper<TaxPdfQyjgEntity, String> {
	public TaxPdfQyjgEntity findByDjxh(Map<String,Object> params);
	//获取股东信息
	public List<TaxQytzfEntity> findByUserid(Map<String,Object> params);
}
