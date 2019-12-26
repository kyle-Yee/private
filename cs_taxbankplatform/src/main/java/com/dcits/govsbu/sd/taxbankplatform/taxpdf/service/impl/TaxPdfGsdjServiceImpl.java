package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper.TaxPdfGsdjMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfGsdjEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfGsdjService;

@Service("taxPdfGsdjService")
public class TaxPdfGsdjServiceImpl extends AbstractService<TaxPdfGsdjEntity, String> implements TaxPdfGsdjService {
	@Autowired
	private TaxPdfGsdjMapper taxPdfGsdjMapper;
	

	public TaxPdfGsdjMapper getTaxPdfGsdjMapper() {
		return taxPdfGsdjMapper;
	}

	public void setTaxPdfGsdjMapper(TaxPdfGsdjMapper taxPdfGsdjMapper) {
		this.taxPdfGsdjMapper = taxPdfGsdjMapper;
	}

	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(taxPdfGsdjMapper);
	}
	
	@Override
	public List<TaxPdfGsdjEntity> findByDjxh(Map<String,Object> params){
		return taxPdfGsdjMapper.findByDjxh(params);
	}
}
