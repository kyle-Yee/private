package com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfDksqEntity;

/**
 * 
 * @author Administrator
 *
 */
@Repository
public interface TaxPdfDksqMapper extends BaseMapper<TaxPdfDksqEntity, String> {
	//获取贷款申请信息
	public TaxPdfDksqEntity findById(Map<String,Object> params);
	
	public String getHkfsName(String id);
}
