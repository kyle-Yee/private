/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.loanapplyattach.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.loanapplyattach.model.LoanApplyAttach;

/**
 * @author 胡宝龙2016-8-19 上午11:09:01
 *
 */
public interface LoanApplyAttachService {
	public List<LoanApplyAttach> queryListByPage(Map<String, Object> parameter);

	public int insert(LoanApplyAttach loanApplyAttach);
	
	public LoanApplyAttach findById(String id);
}
