package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper.TaxPdfSwdjMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfSwdjEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfSwdjService;

@Service("taxPdfSwdjService")
public class TaxPdfSwdjServiceImpl extends AbstractService<TaxPdfSwdjEntity, String> implements TaxPdfSwdjService {
	@Autowired
	private TaxPdfSwdjMapper taxPdfSwdjMapper;
	

	public TaxPdfSwdjMapper getTaxPdfSwdjMapper() {
		return taxPdfSwdjMapper;
	}

	public void setTaxPdfSwdjMapper(TaxPdfSwdjMapper taxPdfSwdjMapper) {
		this.taxPdfSwdjMapper = taxPdfSwdjMapper;
	}

	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(taxPdfSwdjMapper);
	}
	
	@Override
	public TaxPdfSwdjEntity findByDjxh(Map<String,Object> params){
		return taxPdfSwdjMapper.findByDjxh(params);
	}
}
