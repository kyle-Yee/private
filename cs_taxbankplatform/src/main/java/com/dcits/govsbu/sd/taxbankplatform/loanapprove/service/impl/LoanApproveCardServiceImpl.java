package com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.mapper.LoanApproveCardMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanCardDetailEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanCardQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.TotalDataEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveCardService;

/**
 * 信用卡申请(初审/终审查看)
 * @versions:1.0 
 * @filename：LoanApproveCardServiceImpl.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午11:28:222:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName LoanApproveCardServiceImpl
 */
@Service("loanApproveCardService")
public class LoanApproveCardServiceImpl extends AbstractService<LoanCardQueryEntity, String> implements LoanApproveCardService{

	@Autowired
	private LoanApproveCardMapper loanApproveCardMapper;
	
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(loanApproveCardMapper);
	}

	@Override
	public TotalDataEntity queryTotalData(Map<String, Object> parameters) {
		return loanApproveCardMapper.queryTotalData(parameters);
	}

	@Override
	public LoanCardDetailEntity loanCardDetail(Map<String, Object> map) {
		return loanApproveCardMapper.loanCardDetail(map);
	}

}
