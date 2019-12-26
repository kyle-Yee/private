package com.dcits.govsbu.sd.taxbankplatform.loanapprove.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanCardDetailEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanCardQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.TotalDataEntity;

/**
 * @caption 信用卡申请(初审/终审查看)
 * @versions:1.0 
 * @filename：LoanApproveCardMapper.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午11:20:492:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName LoanApproveCardMapper
 */
@Repository
public interface LoanApproveCardMapper extends BaseMapper<LoanCardQueryEntity, String>{
	
	//看板数据
	public TotalDataEntity queryTotalData(Map<String, Object> parameters);
	
	//授信受理详情
	public LoanCardDetailEntity loanCardDetail(Map<String,Object> map);
	
}
