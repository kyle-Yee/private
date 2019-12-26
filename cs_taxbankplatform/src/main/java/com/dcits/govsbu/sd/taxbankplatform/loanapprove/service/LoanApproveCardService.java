package com.dcits.govsbu.sd.taxbankplatform.loanapprove.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanCardDetailEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanCardQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.TotalDataEntity;



/**
 * @caption 信用卡申请(初审/终审查看)
 * @versions:1.0 
 * @filename：LoanApproveCardService.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午11:23:322:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName LoanApproveCardService
 */
public interface LoanApproveCardService {

	//看板数据
	TotalDataEntity queryTotalData(Map<String, Object> parameters);
	
	//表单数据分页
	List<LoanCardQueryEntity> queryListByPage(Map<String,Object> map);
	
	//受理授信详情
	LoanCardDetailEntity loanCardDetail(Map<String,Object> map);
	
}