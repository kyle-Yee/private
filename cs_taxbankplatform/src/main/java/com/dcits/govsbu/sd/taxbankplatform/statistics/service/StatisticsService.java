package com.dcits.govsbu.sd.taxbankplatform.statistics.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.statistics.model.CommonEntity;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.LoanCompreInfo;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.LoanDetailsInfo;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BusinessRegInfo;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.BankManagerInfo;

/**
 * 统计报表业务接口
 * @author Sigua.Huang
 * @date 2018年6月28日
 */
public interface StatisticsService {

	/**
	 * 查询明细清册数据
	 * @author Sigua.Huang
	 * @date 2018年6月29日
	 */
	List<LoanDetailsInfo> queryLoanDetailsList(Map<String, Object> parameters);

	/**
	 * 获取行业门类
	 * @author Sigua.Huang
	 * @date 2018年6月29日
	 */
	List<CommonEntity> queryHYList();

	/**
	 * 获取登记注册类型大类
	 * @author Sigua.Huang
	 * @date 2018年6月29日
	 */
	List<CommonEntity> queryDJZCLXList();

	/**
	 * 贷款综合情况统计数据
	 * @author Sigua.Huang
	 * @date 2018年6月30日
	 */
	List<LoanCompreInfo> queryLoanCompreList(Map<String, Object> parameters);

	/**
	 * 贷款综合情况统计数据
	 * @author Sigua.Huang
	 * @date 2018年7月1日
	 */
	List<BankManagerInfo> queryBankManagerList(Map<String, Object> parameters);

	/**
	 * 数据项展示
	 * @author Sigua.Huang
	 * @date 2018年7月1日
	 */
	List<BusinessRegInfo> queryBusinessRegInfo(String dataStr);

	/**
	 * 获取所属税务局
	 * @author Sigua.Huang
	 * @date 2018年7月13日
	 */
	List<CommonEntity> querySsswjDmList(String ssswjDm);

}
