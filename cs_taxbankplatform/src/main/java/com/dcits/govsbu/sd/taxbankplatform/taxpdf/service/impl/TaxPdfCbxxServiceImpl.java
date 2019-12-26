package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper.TaxPdfCbxxMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfCbxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfCbxxService;

@Service("taxPdfCbxxService")
public class TaxPdfCbxxServiceImpl extends AbstractService<TaxPdfCbxxEntity, String> implements TaxPdfCbxxService {
	@Autowired
	private TaxPdfCbxxMapper taxPdfCbxxMapper;
	

	public TaxPdfCbxxMapper getTaxPdfCbxxMapper() {
		return taxPdfCbxxMapper;
	}

	public void setTaxPdfCbxxMapper(TaxPdfCbxxMapper taxPdfCbxxMapper) {
		this.taxPdfCbxxMapper = taxPdfCbxxMapper;
	}

	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(taxPdfCbxxMapper);
	}
	
	@Override
	public TaxPdfCbxxEntity findByDjxh(Map<String,Object> params){
		return taxPdfCbxxMapper.findByDjxh(params);
	}
}
