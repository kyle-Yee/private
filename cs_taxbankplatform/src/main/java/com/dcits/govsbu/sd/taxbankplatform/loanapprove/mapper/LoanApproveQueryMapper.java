package com.dcits.govsbu.sd.taxbankplatform.loanapprove.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApplyAttachEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApproveRecEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApplyFinalEndEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApplyFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanBankloanTypeEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanExportExcel;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.TotalDataEntity;

/**
 * LoanApproveListMapper.java
 * @author 严添麟
 * @caption 贷款申请(初审/终审)列表/详情查询
 */
@Repository
public interface LoanApproveQueryMapper extends BaseMapper<LoanApproveQueryEntity, String>{
	//查询贷款申请数据项
	public List<LoanApplyAttachEntity> queryListAttach(Map<String, Object> parameter);
	public List<LoanApproveQueryEntity> querylaIdListByPage(Map<String, Object> parameter);
	//获取前台统计数据
	public TotalDataEntity queryTotalData(String orgid);
	public List<LoanApplyFinalEntity> findByEx(Map<String, Object> map);
	public List<LoanBankloanTypeEntity> bankloanTypeList();
	public int insertItem(LoanApplyFinalEndEntity loanApplyFinalEndEntity);
	public int updatela(LoanApproveQueryEntity loanApproveQueryEntity);
	public int updatelastauts3(LoanApproveQueryEntity loanApproveQueryEntity);
	public int updatelastauts6(LoanApproveQueryEntity loanApproveQueryEntity);
	public int updatelabydealline(LoanApproveQueryEntity loanApproveQueryEntity);
	public int insertRec(LoanApproveRecEntity loanApproveRecEntity);
	public int updatelaf(LoanApplyFinalEntity loanApplyFinalEntity);
	@Override
	public int insert(LoanApproveQueryEntity approveQueryEntity);
	public List<LoanApproveQueryEntity> findBystatues();
	
	//通过贷款申请|查询客户贷款申请中的期望还款方式
	public String getHkfsById(String id);
	//通过贷款申请|查询银行产品的期望还款方式
	public String getHkfsYhById(String id);
	//通过贷款申请|查询贷款记录表中银行要求的还款方式（唯一）
	public String getFinalHkfsById(String id);

	public int updatelaf2(LoanApproveFinalEntity loanApproveFinalEntity);
	public int insertApply(LoanApproveQueryEntity approveQueryEntity);
	public int insertRec3(LoanApproveRecEntity loanApproveRecEntity);
	public int insertRec7(LoanApproveRecEntity loanApproveRecEntity);

	//导出贷款审批单的数据项
	public List<LoanExportExcel> exportExcel(Map<String, Object> parameter);
	//所有的还款方式
	public List<Map<String, Object>> getAllRepayWay();
	//根据贷款申请id查询详细信息
    public LoanApproveQueryEntity findlaById(String id);

} 
