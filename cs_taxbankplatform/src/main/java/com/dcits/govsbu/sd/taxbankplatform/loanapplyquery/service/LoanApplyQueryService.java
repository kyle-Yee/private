package com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.service;

import java.util.List;
import java.util.Map;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApplyQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApplyTailafterEntity;

/**
 * LoanApplyQueryService.java
 * @author 严添麟
 * @date 2016年8月9日
 * @caption 贷款申请查询
 */
public interface LoanApplyQueryService {
	//贷款申请查询列表信息
	public List<LoanApplyQueryEntity> queryListByPage(Map<String, Object> parameter);
	//贷款申请查询详细信息
	public LoanApplyQueryEntity findById(String id);
	//贷款申请查询数据项信息
	public LoanApplyQueryEntity queryListAttach(Map<String, Object> parameter);
	
	public LoanApplyTailafterEntity findDataById(String id);
}