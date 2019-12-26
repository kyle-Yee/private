package com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class LoanApplyTailafterEntity extends BaseEntity{

	
		/**  描述   (@author: zhongyj) */      
	    
	private static final long serialVersionUID = 1L;
	
	private int total_days;
	private String org_name;
	public int getTotal_days() {
		return total_days;
	}
	public void setTotal_days(int total_days) {
		this.total_days = total_days;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

}
