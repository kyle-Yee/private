package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper.TaxPdfDksqMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfDksqEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfDksqService;

@Service("taxPdfService")
public class TaxPdfDksqServiceImpl extends AbstractService<TaxPdfDksqEntity, String> implements TaxPdfDksqService {
	@Autowired
	private TaxPdfDksqMapper taxPdfDksqMapper;

	public TaxPdfDksqMapper getTaxPdfDksqMapper() {
		return taxPdfDksqMapper;
	}

	public void setTaxPdfDksqMapper(TaxPdfDksqMapper taxPdfDksqMapper) {
		this.taxPdfDksqMapper = taxPdfDksqMapper;
	}

	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(taxPdfDksqMapper);
	}
	
	@Override
	public TaxPdfDksqEntity findById(Map<String,Object>params){
		return taxPdfDksqMapper.findById(params);
	}

	@Override
	public String getHkfsName(String id) {
		return taxPdfDksqMapper.getHkfsName(id);
	}
}
