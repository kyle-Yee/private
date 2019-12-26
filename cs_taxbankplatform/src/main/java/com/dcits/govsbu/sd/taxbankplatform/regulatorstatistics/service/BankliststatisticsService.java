package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;

public interface BankliststatisticsService {
	public List<BanklistInfoEntity> bankliststatistics(Map<String, Object> parameters);

}
