package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.mapper.BankliststatisticsMapper;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.BankliststatisticsService;

@Service("bankliststatisticsService")
public class BankliststatisticsServiceImpl  extends AbstractService<BanklistInfoEntity, String> implements BankliststatisticsService {

	@Autowired
	private BankliststatisticsMapper bankliststatisticsMapper;
	
	@Autowired
	public void setBaseMapper() {		
		super.setBaseMapper(bankliststatisticsMapper);		
	}

	@Override
	public List<BanklistInfoEntity> bankliststatistics(
			Map<String, Object> parameters) {
		return bankliststatisticsMapper.bankliststatistics(parameters);
	}
	

}
