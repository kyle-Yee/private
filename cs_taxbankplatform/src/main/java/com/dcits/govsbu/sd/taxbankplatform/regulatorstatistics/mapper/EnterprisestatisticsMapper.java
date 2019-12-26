package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model.AuthorizationrecordEntity;
import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.EnterpriseInfoEntity;

@Repository
public interface EnterprisestatisticsMapper extends BaseMapper<EnterpriseInfoEntity, String> {
	public List<EnterpriseInfoEntity> enterprisestatistics(Map<String, Object> parameters);

	public List<EnterpriseInfoEntity> findcheckrecord(Map<String, Object> parameters);

	public int updateRZxx(Map<String, Object> parameters);

	public List<EnterpriseInfoEntity> queryRzByPage(Map<String, Object> parameter);


}
