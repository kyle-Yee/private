package com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApproveRecEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.mapper.LoanApproveQueryMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApplyFinalEndEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApplyFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanBankloanTypeEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanExportExcel;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.TotalDataEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveQueryService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

/**
 * 贷款申请(列表/详情)查询
 * @versions:1.0 
 * @filename：LoanApproveQueryServiceImpl.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午11:26:562:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName LoanApproveQueryServiceImpl
 */
@Service("loanApproveQueryService")
public class LoanApproveQueryServiceImpl extends AbstractService<LoanApproveQueryEntity, String> implements LoanApproveQueryService{
	
	private static final Logger logger = LoggerFactory.getLogger(LoanApproveQueryServiceImpl.class);
	
	@Autowired
	private LoanApproveQueryMapper loanApproveQueryMapper;
	
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(loanApproveQueryMapper);
	}
	
	public void job1() {  
	       System.out.println("111111");  
	    }  
	/**
	 * 功能:
	 * 获取贷款申请数据项信息
	 */
	@Override
	public LoanApproveQueryEntity queryListAttach(Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		LoanApproveQueryEntity loanApproveQueryEntity = new LoanApproveQueryEntity();
		try {
			loanApproveQueryEntity.setLoanApplyAttachList(loanApproveQueryMapper.queryListAttach(parameter));
		} catch (ServiceException e) {
			// TODO: handle exception
			throw new AjaxException(e);
		}
		return loanApproveQueryEntity;
	}
	/**
	 * 获取管理看板的数据统计 
	 */
	@Override
	public TotalDataEntity queryTotalData(String orgid){
		logger.info("Execute queryTotalData!");
		TotalDataEntity totalDataEntity = new TotalDataEntity();
		try {
			 totalDataEntity = loanApproveQueryMapper.queryTotalData(orgid);
		} catch (Exception e) {
			logger.info("IOExecute queryTotalData!");
		}
		return totalDataEntity;
	}


	@Override
	public List<LoanApplyFinalEntity> findByEx(Map<String, Object> map) {
		return loanApproveQueryMapper.findByEx(map);
	}


	@Override
	public int insertItem(LoanApplyFinalEndEntity loanApplyFinalEndEntity) {
		/*** 信贷终止表 添加主键 ***/
		String laeId = IDGenerate.getZJID("DKZZ");
		loanApplyFinalEndEntity.setLae_id(laeId);
		return loanApproveQueryMapper.insertItem(loanApplyFinalEndEntity);
	}


	@Override
	public int updatela(LoanApproveQueryEntity loanApproveQueryEntity) {
		return loanApproveQueryMapper.updatela(loanApproveQueryEntity);
	}


	@Override
	public int insertRec(LoanApproveRecEntity loanApproveRecEntity) {
		/*** 产品申贷审批记录表 添加主键 ***/
		String larId = IDGenerate.getZJID("DKJL");
		loanApproveRecEntity.setLar_id(larId);
		return loanApproveQueryMapper.insertRec( loanApproveRecEntity);
	}

    @Override
	public int insert(LoanApproveQueryEntity approveQueryEntity){
		/*** 产品申请初始表 添加主键 ***/
		String laId = IDGenerate.getZJID("DK");
		approveQueryEntity.setLa_id(laId);
    	return loanApproveQueryMapper.insert(approveQueryEntity);
    }
	@Override
	public List<LoanBankloanTypeEntity> bankloanTypeList() {
		return loanApproveQueryMapper.bankloanTypeList();
	}


	@Override
	public int updatelaf(LoanApplyFinalEntity loanApplyFinalEntity){
		return loanApproveQueryMapper.updatelaf(loanApplyFinalEntity);
	}

	@Override
	public List<LoanApproveQueryEntity> findBystatues(
			) {
		return loanApproveQueryMapper.findBystatues();
	}

	@Override
	public int updatelabydealline(LoanApproveQueryEntity loanApproveQueryEntity) {
		return loanApproveQueryMapper.updatelabydealline(loanApproveQueryEntity);
	}

	@Override
	public String getHkfsById(String id) {
		return loanApproveQueryMapper.getHkfsById(id);
	}

	@Override
	public String getHkfsYhById(String id) {
		return loanApproveQueryMapper.getHkfsYhById(id);
	}

	@Override
	public String getFinalHkfsById(String id) {
		return loanApproveQueryMapper.getFinalHkfsById(id);
	}

	@Override
	public List<LoanApproveQueryEntity> querylaIdListByPage(
			Map<String, Object> parameter) {
		return loanApproveQueryMapper.querylaIdListByPage(parameter);
	}

	@Override
	public int updatelaf2(LoanApproveFinalEntity loanApproveFinalEntity) {
		return loanApproveQueryMapper.updatelaf2(loanApproveFinalEntity);
	}

	@Override
	public int updatelastauts3(LoanApproveQueryEntity loanApproveQueryEntity) {
		return loanApproveQueryMapper.updatelastauts3(loanApproveQueryEntity);
		
	}

	@Override
	public int updatelastauts6(LoanApproveQueryEntity loanApproveQueryEntity) {
		return loanApproveQueryMapper.updatelastauts6(loanApproveQueryEntity);
	}

	@Override
	public int insertApply(LoanApproveQueryEntity approveQueryEntity) {
		/*** 产品申请初始表 添加主键 ***/
		String laId = IDGenerate.getZJID("DK");
		approveQueryEntity.setLa_id(laId);
		return loanApproveQueryMapper.insertApply(approveQueryEntity);
	};

	@Override
	public int insertRec3(LoanApproveRecEntity loanApproveRecEntity) {
		/*** 产品申贷审批记录表 添加主键 ***/
		String larId = IDGenerate.getZJID("DKJL");
		loanApproveRecEntity.setLar_id(larId);
		return loanApproveQueryMapper.insertRec3( loanApproveRecEntity);
	}

	@Override
	public int insertRec7(LoanApproveRecEntity loanApproveRecEntity) {
		/*** 产品申贷审批记录表 添加主键 ***/
		String larId = IDGenerate.getZJID("DKJL");
		loanApproveRecEntity.setLar_id(larId);
		return loanApproveQueryMapper.insertRec7( loanApproveRecEntity);
	}


	
	//导出贷款审批单的数据项
	@Override
	public List<LoanExportExcel> exportExcel(Map<String, Object> parameter){
		return loanApproveQueryMapper.exportExcel(parameter);
	}

	@Override
	public List<Map<String, Object>> getAllRepayWay() {
		return loanApproveQueryMapper.getAllRepayWay();
	}

	@Override
	public LoanApproveQueryEntity findlaById(String id) {
		// TODO Auto-generated method stub
		return loanApproveQueryMapper.findlaById(id);
	}

}
