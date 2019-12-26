package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.LoansdetailsEntity;

public interface LoansdetailsstatisticsService {
	public List<LoansdetailsEntity> loansdetailsstatistics(Map<String, Object> parameters);

}
