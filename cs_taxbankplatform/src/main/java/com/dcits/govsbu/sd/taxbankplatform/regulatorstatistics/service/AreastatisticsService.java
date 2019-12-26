package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;

public interface AreastatisticsService {
	public List<BanklistInfoEntity> areastatistics(Map<String, Object> parameters);
	public List<BanklistInfoEntity> areastatistics1(Map<String, Object> parameters);
}
