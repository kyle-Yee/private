package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.mapper.EnterprisestatisticsMapper;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.EnterpriseInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.EnterprisestatisticsService;

@Service("enterprisestatisticsService")
public class EnterprisestatisticsServiceImpl  extends AbstractService<EnterpriseInfoEntity, String> implements EnterprisestatisticsService {

	@Autowired
	private EnterprisestatisticsMapper enterprisestatisticsMapper;
	
	@Autowired
	public void setBaseMapper() {		
		super.setBaseMapper(enterprisestatisticsMapper);		
	}

	@Override
	public List<EnterpriseInfoEntity> enterprisestatistics(
			Map<String, Object> parameters) {
		List<EnterpriseInfoEntity> enterprisestatistics = enterprisestatisticsMapper.enterprisestatistics(parameters);
		for(EnterpriseInfoEntity es:enterprisestatistics) {
			if("rzjg001".equals(es.getSfrz())) {
				es.setYy("");
			}
			if (es.getZcsj() == null){
				es.setZcsj("");
			}
			if (es.getQymc() == null){
				es.setQymc("");
			}
			if (es.getFrxm() == null){
				es.setFrxm("");
			}
			if (es.getFrsjh() == null){
				es.setFrsjh("");
			}
			if (es.getSfrz() == null){
				es.setSfrz("");
			}
			if (es.getYy() == null){
				es.setYy("");
			}
		}
		return enterprisestatistics;
	}

	@Override
	public List<EnterpriseInfoEntity> findcheckrecord(
			Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return enterprisestatisticsMapper.findcheckrecord(parameters);
	}

	@Override
	public int updateRZxx(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return enterprisestatisticsMapper.updateRZxx(parameters);
	}

	@Override
	public List<EnterpriseInfoEntity> queryRzByPage(
			Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return enterprisestatisticsMapper.queryRzByPage(parameter);
	}

	

}
