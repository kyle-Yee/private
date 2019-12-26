package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @description 信用卡申请详情实体
 * @author 10856
 *
 */
public class LoanCardDetailEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String la_id;//申请id
	
	private NsryhxxEntity nsryhxxEntity;//纳税人信息实体
	
	private LoanCardRecordEntity loanCardRecordEntity;//信用卡申请记录表
	
	private List<LoanApproveRecEntity> recList;//申请记录流水表

	public String getLa_id() {
		return la_id;
	}

	public void setLa_id(String la_id) {
		this.la_id = la_id;
	}

	public NsryhxxEntity getNsryhxxEntity() {
		return nsryhxxEntity;
	}

	public void setNsryhxxEntity(NsryhxxEntity nsryhxxEntity) {
		this.nsryhxxEntity = nsryhxxEntity;
	}

	public LoanCardRecordEntity getLoanCardRecordEntity() {
		return loanCardRecordEntity;
	}

	public void setLoanCardRecordEntity(LoanCardRecordEntity loanCardRecordEntity) {
		this.loanCardRecordEntity = loanCardRecordEntity;
	}

	public List<LoanApproveRecEntity> getRecList() {
		return recList;
	}

	public void setRecList(List<LoanApproveRecEntity> recList) {
		this.recList = recList;
	}
	
	
	
}
