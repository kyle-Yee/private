package com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * LoanApplyAttachEntity.java
 * @author 严添麟
 * @date 2016年8月25日
 * @caption 贷款申请数据项
 */
public class LoanApplyAttachEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String pdiValues;																		//数据项值
	private LoanProductDataEntity loanProductDataEntity;		//数据项名称			

	public String getPdiValues() {
		return pdiValues;
	}
	public void setPdiValues(String pdiValues) {
		this.pdiValues = pdiValues;
	}
	
	public LoanProductDataEntity getLoanProductDataEntity() {
		return loanProductDataEntity;
	}
	public void setLoanProductDataEntity(LoanProductDataEntity loanProductDataEntity) {
		this.loanProductDataEntity = loanProductDataEntity;
	}
}
