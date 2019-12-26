package com.dcits.govsbu.sd.taxbankplatform.bankmanager.mapper;

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
import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;

public interface BankManagerMapper extends BaseMapper<BMListModel, String > {
	
	public List <BankOrg> queryBankOrg(Map<String,Object> paramaters);
	public List<BMListModel> queryBMList(Map<String,Object> paramaters);
	public int insertBMListModel(BMListModel bmListModel);
	public int insertBusinessRegInfo(BusinessRegInfo businessRegInfo);
	public int insertTaxRegInfo(TaxRegInfo taxRegInfo);
	public List<BusinessRegInfo> queryBusinessRegInfo(String fpId);
	public TaxRegInfo queryTaxRegInfo(String bankCode);
	public List<TaxOptionInfo> queryTaxOption(Map<?, ?> map);
	public List<TaxOptionInfo> queryTaxOptionTe(Map<?, ?> map);
	public int insertTaxOptList(List<TaxOptionRecord> optList);
	public void updateBankStatus(Map<String,Object> map);
	public BMListModel queryBmModel(@Param("fpId")String fpId,@Param("bankCode")String bankCode);
	public int updateBMModel(BMListModel bmListMode);
	public int deleteTaxOptionByBKCode(@Param("fpId")String fpId,@Param("bankCode")String bankCode);
	/**
	 * 根据bankId查询该银行下的金融产品,未配置数据项
	 * @author Sigua.Huang
	 * @date 2018年6月21日
	 */
	public List<FPOrg> findFPsByBankId(String bankId);
	/**
	 * 查询数据项
	 * @author Sigua.Huang
	 * @date 2018年6月24日
	 */
	public List<TaxOptionInfo> findDataByFpId(@Param("fpId")String fpId,@Param("bankCode")String bankCode);
	/**
	 * 记录数据项
	 * @author Sigua.Huang
	 * @date 2018年6月24日
	 */
	public int recordDataUpdate(DataUpdateRecord dataUpdateRecord);
	/**
	 * 查询区域id下的所有银行
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
