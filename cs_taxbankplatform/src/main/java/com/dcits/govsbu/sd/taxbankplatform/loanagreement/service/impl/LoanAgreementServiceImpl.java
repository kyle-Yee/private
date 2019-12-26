/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.loanagreement.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.loanagreement.mapper.LoanAgreementMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanagreement.model.LoanAgreementEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanagreement.service.LoanAgreementService;

/**
 * @author 胡宝龙2016-8-18 下午9:21:52
 *
 */
@Service("loanAgreementService")
public class LoanAgreementServiceImpl  extends AbstractService<LoanAgreementEntity, String> implements LoanAgreementService{
	@Autowired
	private LoanAgreementMapper loanAgreementMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(loanAgreementMapper);
	}

	@Override
	public List<LoanAgreementEntity> checkName(Map<String, Object> parameter) {
		return loanAgreementMapper.checkName(parameter);
	}

	@Override
	public List<LoanAgreementEntity> queryListBysqxy(
			Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return loanAgreementMapper.queryListBysqxy(parameter);
	}

	@Override
	public List<LoanAgreementEntity> queryListByrzxy(
			Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return loanAgreementMapper.queryListByrzxy(parameter);
	}

	@Override
	public LoanAgreementEntity findByOrgOrLaxyId(String orgid) {
		
		return loanAgreementMapper.findByOrgOrLaxyId(orgid);
	}
}
