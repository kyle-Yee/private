package com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.mapper.LoanApproveMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.DownLoadJiaoYanEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.NsryhxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

/**
 * LoanApproveServiceImpl.java
 * @author 严添麟
 * @date 2016年8月15日
 * @caption 贷款申请(初审/终审)
 */
@Service("loanApproveService")
public class LoanApproveServiceImpl extends AbstractService<LoanApproveFinalEntity, String> implements LoanApproveService{

	@Autowired
	private LoanApproveMapper loanApproveMapper;
	
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(loanApproveMapper);
	}

	//贷款申请审批
	@Override
	public int insert(LoanApproveFinalEntity loanApproveRecEntity) {
		int result = 0;
		int la_result = 0;
		int lar_result = 0;
		int laf_result = 0;
		
		String larid = IDGenerate.getZJID("DKJL");
		String lacid= loanApproveRecEntity.getLac_id();
		String lasid = "0";
		String lasStatus = loanApproveRecEntity.getLas_id();
		if(loanApproveRecEntity!=null){
			/***将页面传回来的状态去掉Y 当成贷款状态存入数据库，lacid 是页面传回来的状态  lasid 是贷款申请状态***/
			if(lacid!=null && lacid!=""){
				lasid = lacid.substring(1);
			}
			/***lasStatus 用于撤销和退单记录是在什么贷款状态下撤销或是退单的***/
			if(lasStatus!=null && lasStatus!=""){
				lasStatus = lasStatus.substring(2);
				lasStatus = "CT"+lasStatus;
			}
		}
		loanApproveRecEntity.setLar_id(larid);
		loanApproveRecEntity.setLas_status(loanApproveRecEntity.getLas_id());
		loanApproveRecEntity.setLas_id(lasid);
		/*** 产品申贷成功记录表 添加主键 ***/
		String lafId = IDGenerate.getZJID("DKWC");
		loanApproveRecEntity.setLaf_id(lafId);
		/******start 批量审批，判断审批前是否是1状态（未受理），如果是， 则添加受理记录 *********/
		int lar_shouli_result = 1;
		String formarId1 = loanApproveRecEntity.getLac_id();//原id
		String formarId2 = loanApproveRecEntity.getLas_id();
		String opinion = loanApproveRecEntity.getLar_opinion();
		if(loanApproveRecEntity.getBatchCheckType() == 1){//如果批量审批前是1状态（未受理），则添加受理记录
			loanApproveRecEntity.setLac_id("YDKZT02");//默认通过
			loanApproveRecEntity.setLas_id("DKZT02");
			loanApproveRecEntity.setLar_opinion("同意受理（来自批量审批）");
			lar_shouli_result = loanApproveMapper.insertRec(loanApproveRecEntity);
		}
		loanApproveRecEntity.setLac_id(formarId1);
		loanApproveRecEntity.setLas_id(formarId2);
		loanApproveRecEntity.setLar_opinion(opinion);
		/***** end ***************************************************/
		
		if(!"YDKZT03".equals(loanApproveRecEntity.getLac_id())){
			lar_result = loanApproveMapper.insertRec(loanApproveRecEntity);
			la_result = loanApproveMapper.update(loanApproveRecEntity);
			if(lar_shouli_result == 1 && lar_result == 1 && la_result == 1){
				result = 1;
			}
		}else{
			lar_result = loanApproveMapper.insertRec(loanApproveRecEntity);
			laf_result = loanApproveMapper.insertFinal(loanApproveRecEntity);
			la_result = loanApproveMapper.update(loanApproveRecEntity);
			if(lar_shouli_result == 1 && lar_result == 1 && laf_result == 1 && la_result==1){
				result = 1;
			}
		}
		return result;
	}
	//贷款申请审批
	@Override 
	public DownLoadJiaoYanEntity findDownDateById(Map<String, Object> parameters){
		return loanApproveMapper.findDownDateById(parameters);
	}

	@Override
	public NsryhxxEntity findNsryhxx(String id) {
		return loanApproveMapper.findNsryhxx(id);
	}
	@Override
	//查询授信页面所需要的数据项
	public NsryhxxEntity findSXSJxById (String id){
		return loanApproveMapper.findSXSJxById(id);
	}
	@Override
	//获取湖南接口调用的最新申请序号
	public String findHNSqxh(){
		return loanApproveMapper.findHNSqxh();
	}

	@Override
	public int insertFinal(LoanApproveFinalEntity loanApproveRecEntity) {
		/*** 产品申贷成功记录表 添加主键 ***/
		String lafId = IDGenerate.getZJID("DKWC");
		loanApproveRecEntity.setLaf_id(lafId);
		return loanApproveMapper.insertFinal(loanApproveRecEntity);
	}

	@Override
	public int insertFinalchannelid(LoanApproveFinalEntity loanApproveRecEntity) {
		/*** 产品申贷成功记录表 添加主键 ***/
		String lafId = IDGenerate.getZJID("DKWC");
		loanApproveRecEntity.setLaf_id(lafId);
		return loanApproveMapper.insertFinalchannelid(loanApproveRecEntity);
	}

	@Override
	public String findHNSqxhbynsrsbh(String nsrsbh) {
		return loanApproveMapper.findHNSqxhbynsrsbh(nsrsbh);
	}
}
