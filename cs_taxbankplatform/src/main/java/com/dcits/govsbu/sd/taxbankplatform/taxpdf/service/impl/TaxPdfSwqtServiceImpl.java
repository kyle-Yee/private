package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper.TaxPdfSwqtMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfSwqtEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfSwqtService;

@Service("taxPdfSwqtService")
public class TaxPdfSwqtServiceImpl extends AbstractService<TaxPdfSwqtEntity, String> implements TaxPdfSwqtService {
	@Autowired
	private TaxPdfSwqtMapper taxPdfSwqtMapper;
	

	public TaxPdfSwqtMapper getTaxPdfSwqtMapper() {
		return taxPdfSwqtMapper;
	}

	public void setTaxPdfSwqtMapper(TaxPdfSwqtMapper taxPdfSwqtMapper) {
		this.taxPdfSwqtMapper = taxPdfSwqtMapper;
	}

	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(taxPdfSwqtMapper);
	}
	
	@Override
	public TaxPdfSwqtEntity findByDjxh(Map<String, Object> params){
		return taxPdfSwqtMapper.findByDjxh(params);
	}
	//获取企业欠税信息
	@Override
	public TaxPdfSwqtEntity findQsxx(String sqxh){
		return taxPdfSwqtMapper.findQsxx(sqxh);
	};
}
