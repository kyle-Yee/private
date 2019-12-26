/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.loanagreement.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.loanagreement.model.LoanAgreementEntity;

/**
 * @author 胡宝龙2016-8-18 下午9:21:11
 *
 */
public interface LoanAgreementService {
	public List<LoanAgreementEntity> queryListByPage(Map<String, Object> parameter);
	public List<LoanAgreementEntity> checkName(Map<String, Object> parameter);
	
	public int insert(LoanAgreementEntity loanAgreementEntity);
	
	public LoanAgreementEntity findById(String id);

	public int update(LoanAgreementEntity loanAgreementEntity);
    
    public int deleteBatchById(List<String> ids);
	List<LoanAgreementEntity> queryListBysqxy(Map<String, Object> parameter);
	List<LoanAgreementEntity> queryListByrzxy(Map<String, Object> parameter);
	// 根据orgid获取涉税保密信息查询委托授权书
	LoanAgreementEntity findByOrgOrLaxyId(String orgid);
}
