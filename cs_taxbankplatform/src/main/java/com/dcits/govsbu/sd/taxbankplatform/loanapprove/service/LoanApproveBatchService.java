package com.dcits.govsbu.sd.taxbankplatform.loanapprove.service;


import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.BatchCheckEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
/**
 * 
 * @versions:1.0 
 * @filename：LoanApproveBatchService.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午11:23:472:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName LoanApproveBatchService
 */
public interface LoanApproveBatchService {
	//获取申请实体，用于审批
	public LoanApproveFinalEntity getSHEntity(BatchCheckEntity batchCheckEntity,UserEntity sessionUser);
	
	//获取所有的还款方式
	public List<Map<String,Object>> getHkfsList();
	
	//根据银行端orgid获取还款映射表
	public List<Map<String,Object>> getTbpRwId(Map<String,Object> map);
}