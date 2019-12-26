package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.mapper.ActualissuanceLoansMapper;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model.ActualissuanceLoansEntity;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.ActualissuanceLoansService;

@Service("actualissuanceLoansService")
public class ActualissuanceLoansServiceImpl  extends AbstractService<ActualissuanceLoansEntity, String> implements ActualissuanceLoansService {

	@Autowired
	private ActualissuanceLoansMapper  actualissuanceLoansMapper;
	
	@Autowired
	public void setBaseMapper() {		
		super.setBaseMapper(actualissuanceLoansMapper);		
	}

	@Override
	public List<ActualissuanceLoansEntity> actualissuanceLoansEntities(
			Map<String, Object> parameters) {
		return actualissuanceLoansMapper.actualissuanceLoansEntities(parameters);
	}

	@Override
	public ActualissuanceLoansEntity getStaticData(
			Map<String, Object> parameters) {
		return actualissuanceLoansMapper.getStaticData(parameters);
	}

	@Override
	public ActualissuanceLoansEntity getDynamicData(
			Map<String, Object> parameters) {
		return actualissuanceLoansMapper.getDynamicData(parameters);
	}

	@Override
	public List<String> getUnderArea(Map<String, Object> map) {
		return actualissuanceLoansMapper.getUnderArea(map);
	}



	

}
