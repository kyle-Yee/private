package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.qytzf.model.TaxQytzfEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper.TaxPdfQyjgMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfQyjgEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfQyjgService;

@Service("taxPdfQyjgService")
public class TaxPdfQyjgServiceImpl extends AbstractService<TaxPdfQyjgEntity, String> implements TaxPdfQyjgService {
	@Autowired
	private TaxPdfQyjgMapper taxPdfQyjgMapper;
	

	public TaxPdfQyjgMapper getTaxPdfQyjgMapper() {
		return taxPdfQyjgMapper;
	}

	public void setTaxPdfQyjgMapper(TaxPdfQyjgMapper taxPdfQyjgMapper) {
		this.taxPdfQyjgMapper = taxPdfQyjgMapper;
	}

	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(taxPdfQyjgMapper);
	}
	
	@Override
	public TaxPdfQyjgEntity findByDjxh(Map<String,Object> params){
		return taxPdfQyjgMapper.findByDjxh(params);
	}
	
	//获取股东信息
	@Override
	public List<TaxQytzfEntity> findByUserid(Map<String,Object> params){
		return taxPdfQyjgMapper.findByUserid(params);
	}
}
