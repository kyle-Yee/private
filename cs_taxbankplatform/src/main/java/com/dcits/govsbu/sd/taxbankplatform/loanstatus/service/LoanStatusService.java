package com.dcits.govsbu.sd.taxbankplatform.loanstatus.service;

import java.util.List;
import java.util.Map;
import com.dcits.govsbu.sd.taxbankplatform.loanstatus.model.LoanStatusEntity;

/**
 * 
 * @author liwangxiong
 *
 */
public interface LoanStatusService {
	public List<LoanStatusEntity> queryListByPage(Map<String, Object> parameter);
	
	public int insert(LoanStatusEntity loanStatusEntity);
	
	public LoanStatusEntity findById(String id);

	public int update(LoanStatusEntity loanStatusEntity);
    
    public int deleteBatchById(List<String> statusids);
}
