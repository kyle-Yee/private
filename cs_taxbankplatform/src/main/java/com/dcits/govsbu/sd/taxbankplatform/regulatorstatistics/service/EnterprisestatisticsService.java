package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.EnterpriseInfoEntity;

public interface EnterprisestatisticsService {
	public List<EnterpriseInfoEntity> enterprisestatistics(Map<String, Object> parameters);
	public List<EnterpriseInfoEntity>  findcheckrecord(Map<String, Object> parameters);
	public int updateRZxx(Map<String, Object> parameters);
	public List<EnterpriseInfoEntity> queryRzByPage(Map<String, Object> parameter);

}
