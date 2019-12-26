package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model.ReportingEntity;

@Repository 
public interface ReportingMapper extends BaseMapper<ReportingEntity, String> {
	public List<ReportingEntity> reportingDetails(Map<String, Object> parameters);
	public ReportingEntity unauthorizedDetails(Map<String, Object> parameters);
	public ReportingEntity totalEnterprises(Map<String, Object> parameters);
	public List<Map<String,Object>> detailUI(Map<String,Object> map);//展示地区注册用户,注册用户
	public List<Map<String,Object>> detailUIrztg(Map<String,Object> map);//展示地区注册用户,认证通过
	public List<Map<String,Object>> detailUIsxbs(Map<String,Object> map);//展示地区注册用户,授信笔数
	public List<ReportingEntity> taxBankAnaDetails(Map<String, Object> parameters);
}
