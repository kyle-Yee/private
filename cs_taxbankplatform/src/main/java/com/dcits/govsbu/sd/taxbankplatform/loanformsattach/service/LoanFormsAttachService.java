package com.dcits.govsbu.sd.taxbankplatform.loanformsattach.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.loanformsattach.model.LoanFormsAttach;

/**
 * @author 胡宝龙2016-9-9 上午10:14:14
 *
 */
public interface LoanFormsAttachService {
	public List<LoanFormsAttach> queryListByPage(Map<String, Object> parameter);
	public List<LoanFormsAttach> queryListByLfId(Map<String, Object> parameter);
	
	public int insert(LoanFormsAttach LoanFormsAttach);
	public int update(LoanFormsAttach LoanFormsAttach);
	
	public LoanFormsAttach findById(String id);
	public LoanFormsAttach queryExist(Map<String, Object> parameter);
}
