package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.mapper.ReportingMapper;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model.ReportingEntity;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.ReportingService;

@Service("reportingService")
public class ReportingServiceImpl  extends AbstractService<ReportingEntity, String> implements ReportingService {

	@Autowired
	private ReportingMapper reportingMapper;
	
	@Autowired
	public void setBaseMapper() {		
		super.setBaseMapper(reportingMapper);		
	}

	@Override
	public List<ReportingEntity> reportingDetails(Map<String, Object> parameters) {
		return reportingMapper.reportingDetails(parameters);
	}

	@Override
	public ReportingEntity unauthorizedDetails(Map<String, Object> parameters) {
		ReportingEntity unad = reportingMapper.unauthorizedDetails(parameters);
		unad.setArea("未认证企业");
		unad.setRztgs("0");
		unad.setSxbs("0");
		unad.setSxze("0");
		return unad;
	}

	@Override
	public ReportingEntity totalEnterprises(Map<String, Object> parameters) {
		return reportingMapper.totalEnterprises(parameters);
	}

	@Override
	public List<Map<String, Object>> detailUI(Map<String,Object> map) {
		return reportingMapper.detailUI(map);
	}

	@Override
	public List<Map<String, Object>> detailUIrztg(Map<String, Object> map) {
		return reportingMapper.detailUIrztg(map);
	}

	@Override
	public List<Map<String, Object>> detailUIsxbs(Map<String, Object> map) {
		return reportingMapper.detailUIsxbs(map);
	}

	//税银平台统计分析报表
	@Override
	public List<ReportingEntity> taxBankAnaDetails(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return reportingMapper.taxBankAnaDetails(map);
	}

	

}
