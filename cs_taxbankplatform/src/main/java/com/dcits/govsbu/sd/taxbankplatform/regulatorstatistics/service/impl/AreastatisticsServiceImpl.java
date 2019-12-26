package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.mapper.AreastatisticsMapper;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.AreastatisticsService;

@Service("areastatisticsService")
public class AreastatisticsServiceImpl  extends AbstractService<BanklistInfoEntity, String> implements AreastatisticsService {

	@Autowired
	private AreastatisticsMapper areastatisticsMapper;
	
	@Autowired
	public void setBaseMapper() {		
		super.setBaseMapper(areastatisticsMapper);		
	}

	@Override
	public List<BanklistInfoEntity> areastatistics(
			Map<String, Object> parameters) {
		return areastatisticsMapper.areastatistics(parameters);
	}

	@Override
	public List<BanklistInfoEntity> areastatistics1(Map<String, Object> parameters) {
		return areastatisticsMapper.areastatistics1(parameters);
	}

	

}
