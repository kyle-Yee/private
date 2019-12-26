/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.loanagreement.mapper;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanagreement.model.LoanAgreementEntity;

/**
 * @author 胡宝龙2016-8-18 下午9:18:13
 *
 */
public interface LoanAgreementMapper  extends BaseMapper<LoanAgreementEntity, String > {
	List<LoanAgreementEntity> checkName(Map<String, Object> parameter);
	List<LoanAgreementEntity> queryListBysqxy(Map<String, Object> parameter);
	List<LoanAgreementEntity> queryListByrzxy(Map<String, Object> parameter);
	LoanAgreementEntity findByOrgOrLaxyId(String orgid);
}


