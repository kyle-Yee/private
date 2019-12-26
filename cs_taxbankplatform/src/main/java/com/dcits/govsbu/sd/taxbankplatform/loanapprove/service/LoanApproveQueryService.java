package com.dcits.govsbu.sd.taxbankplatform.loanapprove.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApproveRecEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApplyFinalEndEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApplyFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanBankloanTypeEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanExportExcel;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.TotalDataEntity;

/**
 * @caption 贷款申请(列表/详情)查询
 * @versions:1.0 
 * @filename：LoanApproveQueryService.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午11:22:232:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName LoanApproveQueryService
 */
public interface LoanApproveQueryService {
	//查询(初审/终审)列表
	public List<LoanApproveQueryEntity> queryListByPage(Map<String, Object> parameter);

	public List<LoanApproveQueryEntity> querylaIdListByPage(Map<String, Object> parameter);
	//查询管理看板的数据统计
	public TotalDataEntity queryTotalData(String orgid);
	
	//根据贷款申请id查询详细信息
	public LoanApproveQueryEntity findById(String id);
	
	//贷款申请查询数据项信息
	public LoanApproveQueryEntity queryListAttach(Map<String, Object> parameter);
	public List<LoanApplyFinalEntity> findByEx(Map<String, Object> map);
	public List<LoanBankloanTypeEntity> bankloanTypeList();
	public int insertItem(LoanApplyFinalEndEntity loanApplyFinalEndEntity);
	public int updatela(LoanApproveQueryEntity loanApproveQueryEntity);
	public int updatelastauts3(LoanApproveQueryEntity loanApproveQueryEntity);
	public int updatelastauts6(LoanApproveQueryEntity loanApproveQueryEntity);
	public int insertRec(LoanApproveRecEntity loanApproveRecEntity);
	public int updatelaf(LoanApplyFinalEntity loanApplyFinalEntity);
	public int insert(LoanApproveQueryEntity approveQueryEntity);
	public List<LoanApproveQueryEntity> findBystatues();
	public int updatelabydealline(LoanApproveQueryEntity loanApproveQueryEntity);
	//获取还款方式通过申请id，个人期望
	public String getHkfsById(String id);
	//获取还款方式通过申请id,银行设定
	public String getHkfsYhById(String id);
	//通过贷款申请|查询贷款记录表中银行要求的还款方式（唯一）
	public String getFinalHkfsById(String id);

	public int updatelaf2(LoanApproveFinalEntity loanApproveFinalEntity);
	public int insertApply(LoanApproveQueryEntity approveQueryEntity);
	public int insertRec3(LoanApproveRecEntity loanApproveRecEntity);
	public int insertRec7(LoanApproveRecEntity loanApproveRecEntity);
	

	//导出贷款审批单的数据项
	public List<LoanExportExcel> exportExcel(Map<String, Object> parameter);
	//获取所有的还款方式
	public List<Map<String,Object>> getAllRepayWay();
	//根据贷款申请id查询详细信息
     public LoanApproveQueryEntity findlaById(String id);

}