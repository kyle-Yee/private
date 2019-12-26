/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.loanapplyattach.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyattach.mapper.LoanApplyAttachMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyattach.model.LoanApplyAttach;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyattach.service.LoanApplyAttachService;

/**
 * @author 胡宝龙2016-8-19 上午11:08:44
 *
 */
@Service("loanApplyAttachService")
public class LoanApplyAttachServiceImpl extends AbstractService<LoanApplyAttach, String> implements LoanApplyAttachService{
	@Autowired
	private LoanApplyAttachMapper loanApplyAttachMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(loanApplyAttachMapper);
	}
}
