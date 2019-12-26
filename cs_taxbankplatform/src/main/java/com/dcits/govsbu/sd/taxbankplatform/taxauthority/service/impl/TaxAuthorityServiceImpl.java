package com.dcits.govsbu.sd.taxbankplatform.taxauthority.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.taxauthority.mapper.TaxAuthorityMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxauthority.model.TaxAuthorityEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxauthority.service.TaxAuthorityService;

@Service("taxauthorityservice")
public class TaxAuthorityServiceImpl extends AbstractService<TaxAuthorityEntity, String> implements TaxAuthorityService {
	@Autowired
	private TaxAuthorityMapper taxAuthorityMapper;
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(taxAuthorityMapper);
	}
	@Override
	public List<TaxAuthorityEntity> queryListAll(Map<String, Object> parameter){
		return taxAuthorityMapper.queryListAll(null);
	}
	
}
