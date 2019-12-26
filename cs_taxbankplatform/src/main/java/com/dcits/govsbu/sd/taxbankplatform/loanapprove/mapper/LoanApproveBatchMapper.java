package com.dcits.govsbu.sd.taxbankplatform.loanapprove.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveFinalEntity;

/**
 * 
 * @versions:1.0 
 * @filename：LoanApproveBatchMapper.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午11:21:212:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName LoanApproveBatchMapper
 */
@Repository
public interface LoanApproveBatchMapper extends BaseMapper<LoanApproveFinalEntity, String>{
	//通过纳税人识别号,组织id获取该用户可授信的申请id
	public Map<String,Object> getLaIdByNsrsbh(Map<String,Object> map);
	
	//获取所有的还款方式
	public List<Map<String,Object>> getHkfsList();
	
	//根据银行端orgid获取还款映射表
	public List<Map<String,Object>> getTbpRwId(Map<String,Object> map);
}
