package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanCodeEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanStatusEntity;
import com.dcits.govsbu.sd.taxbankplatform.repaymentway.model.RepaymentWayEntity;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;


/**
 * 
 * @versions:1.0 
 * @filename：LoanApproveRecEntity.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午11:11:302:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName LoanApproveRecEntity
 */
public class LoanApproveRecEntity extends BaseEntity{
	
	/***/
	private static final long serialVersionUID = 1L;
	
	private String region_id;																			//区域id
	private String fp_id;																						//产品id
	private String la_id;																						//申请id
	private String las_id;																					//审批状态代码
	private String lafs_id;
	public String getLafs_id() {
		return lafs_id;
	}
	public void setLafs_id(String lafs_id) {
		this.lafs_id = lafs_id;
	}
	private String lac_id;																					//审批意见代码
	private int lar_credit_quota;																//授信额度
	private int lar_loan_deadline;															//贷款期限
	private Date lar_begin;																			//贷款期限起
	private Date lar_end;																				//贷款期限止
	private String lar_rate;																				//贷款利率
	private int lar_isoverlay_area;															//是否在产品覆盖区域
	private int rw_id;																						//还款方式
	private String lar_bank_name;															//借款方开户银行
	private String lar_bank_account;														//借款方银行账号
	private String lar_contract;																	//贷款合同号
	private String lar_opinion;																	//审批意见
	private String lar_remark;																	//备注
	private LoanStatusEntity loanStatusEntity;									//申请状态表
	private RepaymentWayEntity repaymentWayEntity;				//还款方式代码表
	private UserEntity userEntity;															//用户信息表
	private LoanCodeEntity loanCodeEntity;										//审批意见代码
	private String passFlag;//是否审核通过--便于初审/终审页面上的处理：【Y通过  N不通过】
	
	public String getPassFlag() {
		return passFlag;
	}
	public void setPassFlag(String passFlag) {
		this.passFlag = passFlag;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	
	public String getFp_id() {
		return fp_id;
	}
	public void setFp_id(String fp_id) {
		this.fp_id = fp_id;
	}
	
	public String getLa_id() {
		return la_id;
	}
	public void setLa_id(String la_id) {
		this.la_id = la_id;
	}
	
	public String getLas_id() {
		return las_id;
	}
	public void setLas_id(String las_id) {
		this.las_id = las_id;
	}
	
	public String getLac_id() {
		return lac_id;
	}
	public void setLac_id(String lac_id) {
		this.lac_id = lac_id;
	}
	
	public int getLar_credit_quota() {
		return lar_credit_quota;
	}
	public void setLar_credit_quota(int lar_credit_quota) {
		this.lar_credit_quota = lar_credit_quota;
	}
	
	public int getLar_loan_deadline() {
		return lar_loan_deadline;
	}
	public void setLar_loan_deadline(int lar_loan_deadline) {
		this.lar_loan_deadline = lar_loan_deadline;
	}
	
	public Date getLar_begin() {
		return lar_begin;
	}
	public void setLar_begin(Date lar_begin) {
		this.lar_begin = lar_begin;
	}
	
	public Date getLar_end() {
		return lar_end;
	}
	public void setLar_end(Date lar_end) {
		this.lar_end = lar_end;
	}
	
	public String getLar_rate() {
		return lar_rate;
	}
	public void setLar_rate(String lar_rate) {
		this.lar_rate = lar_rate;
	}
	
	public int getLar_isoverlay_area() {
		return lar_isoverlay_area;
	}
	public void setLar_isoverlay_area(int lar_isoverlay_area) {
		this.lar_isoverlay_area = lar_isoverlay_area;
	}
	
	public int getRw_id() {
		return rw_id;
	}
	public void setRw_id(int rw_id) {
		this.rw_id = rw_id;
	}
	
	public String getLar_bank_name() {
		return lar_bank_name;
	}
	public void setLar_bank_name(String lar_bank_name) {
		this.lar_bank_name = lar_bank_name;
	}
	
	public String getLar_bank_account() {
		return lar_bank_account;
	}
	public void setLar_bank_account(String lar_bank_account) {
		this.lar_bank_account = lar_bank_account;
	}
	
	public String getLar_contract() {
		return lar_contract;
	}
	public void setLar_contract(String lar_contract) {
		this.lar_contract = lar_contract;
	}
	
	public String getLar_opinion() {
		return lar_opinion;
	}
	public void setLar_opinion(String lar_opinion) {
		this.lar_opinion = lar_opinion;
	}
	
	public String getLar_remark() {
		return lar_remark;
	}
	public void setLar_remark(String lar_remark) {
		this.lar_remark = lar_remark;
	}
	
	public LoanStatusEntity getLoanStatusEntity() {
		return loanStatusEntity;
	}
	public void setLoanStatusEntity(LoanStatusEntity loanStatusEntity) {
		this.loanStatusEntity = loanStatusEntity;
	}
	
	public RepaymentWayEntity getRepaymentWayEntity() {
		return repaymentWayEntity;
	}
	public void setRepaymentWayEntity(RepaymentWayEntity repaymentWayEntity) {
		this.repaymentWayEntity = repaymentWayEntity;
	}
	
	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	public LoanCodeEntity getLoanCodeEntity() {
		return loanCodeEntity;
	}
	public void setLoanCodeEntity(LoanCodeEntity loanCodeEntity) {
		this.loanCodeEntity = loanCodeEntity;
	}
}