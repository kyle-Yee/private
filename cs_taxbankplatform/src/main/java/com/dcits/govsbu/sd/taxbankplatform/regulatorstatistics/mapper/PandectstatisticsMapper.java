package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.count.model.DataCountEntity;
import com.dcits.govsbu.sd.taxbankplatform.count.model.PandectEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;

@Repository 
public interface PandectstatisticsMapper extends BaseMapper<DataCountEntity, String> {
//	List<DataCountEntity> userstatistics(Map<String, Object> parameters);
	List<PandectEntity> monthstatistics(Map<String, Object> parameters);
	List<BanklistInfoEntity> banklistinfo(Map<String, Object> parameters);
	
	/****************************统计功能优化**************************************/
	/*add by xiecui 2018/04/04 begin*/
	Map<String, Object> userstatistics (Map<String, Object> parameters);
	Map<String, Object> loanstatistics (Map<String, Object> parameters);
	Map<String, Object> bankstatistics (Map<String, Object> parameters);
	
	List<Map<String, Object>> usermonthstatistics (Map<String, Object> parameters);
	List<Map<String, Object>> loanmonthstatistics (Map<String, Object> parameters);
	/*add by xiecui 2018/04/04 end*/

	/****************************new 统计功能**************************************/
	Map<String, Object> statisticsinfo(Map<String, Object> param);
	List<Map<String, Object>> loanamountstatisticsinfo(Map<String, Object> param);
	List<Map<String, Object>> loancountstatisticsinfo(Map<String, Object> param);
	List<Map<String, Object>> bankproduckstatisticsinfo(Map<String, Object> param);
}
