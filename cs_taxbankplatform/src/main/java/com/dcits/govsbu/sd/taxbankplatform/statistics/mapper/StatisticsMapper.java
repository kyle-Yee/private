package com.dcits.govsbu.sd.taxbankplatform.statistics.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.statistics.model.CommonEntity;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.LoanCompreInfo;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.LoanDetailsInfo;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.BankManagerInfo;

/**
 * 统计报表数据Dao层接口
 * @author Sigua.Huang
 * @date 2018年6月28日
 */
@Repository 
public interface StatisticsMapper {

	/**
	 * 明细清册
	 * @author Sigua.Huang
	 * @date 2018年6月29日
	 */
	public List<LoanDetailsInfo> queryLoanDetailsList(Map<String, Object> parameters);

	/**
	 * 获取行业门类
	 * @author Sigua.Huang
	 * @date 2018年6月29日
	 */
	public List<CommonEntity> queryHYList();

	/**
	 * 获取登记注册类型大类
	 * @author Sigua.Huang
	 * @date 2018年6月29日
	 */
	public List<CommonEntity> queryDJZCLXList();

	/**
	 * 获取贷款综合情况统计数据(以贷款申请为主表)
	 * @author Sigua.Huang
	 * @date 2018年6月30日
	 */
	public List<LoanCompreInfo> queryLoanCompreListForDKSQ(Map<String, Object> parameters);
	
	/**
	 * 获取贷款综合情况统计数据(以贷后授信为主表)
	 * @author Sigua.Huang
	 * @date 2018年6月30日
	 */
	public List<LoanCompreInfo> queryLoanCompreListForDHSX(Map<String, Object> parameters);

	/**
	 * 获取银行管理统计数据
	 * @author Sigua.Huang
	 * @date 2018年7月1日
	 */
	public List<BankManagerInfo> queryBankManagerList(Map<String, Object> parameters);

	/**
	 * 根据dataDM获取工商表名称
	 * @author Sigua.Huang
	 * @date 2018年7月1日
	 */
	public String queryGSBMCByDataDM(String key);

	/**
	 * 根据dataDM获取数据项名称
	 * @author Sigua.Huang
	 * @date 2018年7月1日
	 */
	public String queryMCByDataDM(String data);

	/**
	 * 获取贷款综合情况统计数据
	 * @author Sigua.Huang
	 * @date 2018年7月2日
	 */
	public List<LoanCompreInfo> queryLoanCompreList(Map<String, Object> parameters);

	/**
	 * 得到为实质性内容数据项
	 * @author Sigua.Huang
	 * @date 2018年7月9日
	 */
	public int findDataCount(String[] split);

	/**
	 * 获取产品信息
	 * @author Sigua.Huang
	 * @date 2018年7月12日
	 */
	public List<LoanCompreInfo> queryFPMsg(Map<String, Object> parameters);

	/**
	 * 调用查询贷款综合情况统计数据
	 * @author liuc
	 * @date 2018年9月8日
	 */
	public LoanCompreInfo callLoanCompre(Map<String, String> map);
	
	/**
	 * 调用查询贷款综合情况统计数据
	 * @author liuc
	 * @date 2018年9月8日
	 */
	public String findSqcxbs(Map<String, String> map);

	/**
	 * 获取所属税务局
	 * @author Sigua.Huang
	 * @date 2018年7月13日
	 */
	public List<CommonEntity> querySsswjDmList(String ssswjDm);

}
