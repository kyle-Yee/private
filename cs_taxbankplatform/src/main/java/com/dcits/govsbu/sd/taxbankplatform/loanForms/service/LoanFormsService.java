/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.loanForms.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.loanForms.model.LoanForms;
/**
 * @author 胡宝龙2016-8-15 上午10:02:35
 *
 */
public interface LoanFormsService {
	public int insert(LoanForms forms );
	public LoanForms findById(String id);
	public LoanForms findByFpId(String id);
	public int update(LoanForms forms);
	public List<LoanForms> queryListByPage(Map<String, Object> parameter);
}
