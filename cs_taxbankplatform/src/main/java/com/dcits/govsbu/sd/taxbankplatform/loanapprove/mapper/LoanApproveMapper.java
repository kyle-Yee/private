package com.dcits.govsbu.sd.taxbankplatform.loanapprove.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;
import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.DownLoadJiaoYanEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.NsryhxxEntity;

/**
 * @caption 贷款申请(初审/终审)
 * @versions:1.0 
 * @filename：LoanApproveMapper.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午11:20:242:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName LoanApproveMapper
 */
@Repository
public interface LoanApproveMapper extends BaseMapper<LoanApproveFinalEntity, String>{
	//录入审批记录
	public int insertRec(LoanApproveFinalEntity loanApproveRecEntity);
	
	//录入审批结果
	public int insertFinal(LoanApproveFinalEntity loanApproveRecEntity);
	public int insertFinalchannelid(LoanApproveFinalEntity loanApproveRecEntity);
	//下载报告的校验数据
	public DownLoadJiaoYanEntity findDownDateById(Map<String, Object> parameters);
	//查询纳税人用户信息
	public NsryhxxEntity findNsryhxx(String id);
	//查询授信页面所需要的数据项
	public NsryhxxEntity findSXSJxById (String id);
	//获取湖南接口调用的最新申请序号
	public String findHNSqxh();
	//根据纳税人识别号获取湖南接口调用的最新申请序号
	public String findHNSqxhbynsrsbh(String nsrsbh);
}
