package com.dcits.govsbu.sd.taxbankplatform.bankmanager.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BMListModel;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BankOrg;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BusinessRegInfo;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.DataUpdateRecord;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.FPOrg;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.TaxOptionInfo;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.TaxOptionRecord;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.TaxRegInfo;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.CommonEntity;

public interface BankManagerService {
	public List<BankOrg> queryBankOrg(Map<String,Object> paramaters);
	public List<BMListModel> queryBMList(Map<String,Object> paramaters);
	
	public int insert(Map<String,Object> map);
	public int insert(BMListModel bmListMode);
	public int insert(BusinessRegInfo businessRegInfo);
	public int insert(TaxRegInfo taxRegInfo);
	public List<BusinessRegInfo> queryBusinessRegInfo(String fpId);
	public TaxRegInfo queryTaxRegInfo(String bankCode);
	public List<TaxOptionInfo> queryTaxOption(Map<?, ?> map);
	public List<TaxOptionInfo> queryTaxOptionTe(Map<?, ?> map);
	public int insertTaxOptList(List<TaxOptionRecord> recList);
	public int delAndinsertTaxOptList(List<TaxOptionRecord> recList);
	public void updateBankStatus(Map<String,Object> map);
	public BMListModel queryBmModel(@Param("fpId")String fpId,@Param("bankCode")String bankCode);
	public int updateBMModel(BMListModel bmListMode);
	/**
	 * 根据bankId查询该银行下的金融产品(未配置数据项的产品)
	 * @author Sigua.Huang
	 * @date 2018年6月21日
	 */
	public List<FPOrg> findFPsByBankId(String bankId);
	/**
	 * 对数据项修改的记录
	 * @author Sigua.Huang
	 * @return 
	 * @date 2018年6月24日
	 */
	public int recordDataUpdate(DataUpdateRecord dataUpdateRecord);
	/**
	 * 查询regionId区域id下的所有银行
	 * @author Sigua.Huang
	 * @date 2018年7月2日
	 */
	public List<BankOrg> queryBankList(Map<String, Object> paramaters);
	/**
	 * 查询该银行下的金融产品
	 * @author Sigua.Huang
	 * @date 2018年7月2日
	 */
	public List<FPOrg> findFPList(String bankId);
	public List<FPOrg> findFpList(String bankId);

}
