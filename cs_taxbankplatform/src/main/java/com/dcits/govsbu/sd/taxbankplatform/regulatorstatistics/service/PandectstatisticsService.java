package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.count.model.PandectEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;

public interface PandectstatisticsService {
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

	/**
	 * 贷款信息分析（金额）
	 */
	List<Map<String, Object>> loanamountstatisticsinfo(Map<String, Object> param);

	/**
	 * 贷款信息分析（笔数）
	 */
	List<Map<String, Object>> loancountstatisticsinfo(Map<String, Object> param);

	/**
	 * 银行产品信息分析
	 */
	List<Map<String, Object>> bankproduckstatisticsinfo(Map<String, Object> param);
}
