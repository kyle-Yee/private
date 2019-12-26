package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;



public class LoanBankloanTypeEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	/**表名*/
	private static final String tableName = "tb_bankloan_tyle";
	/**表ID名*/
	private static final String idName = "blt_id";
	
	private String bltName;	//审核名称
	
	private String enabled;	//有效标志
	
	private String bltRemark;	//备注





	public String getBltName() {
		return bltName;
	}

	public void setBltName(String bltName) {
		this.bltName = bltName;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getBltRemark() {
		return bltRemark;
	}

	public void setBltRemark(String bltRemark) {
		this.bltRemark = bltRemark;
	}

	public static String getTablename() {
		return tableName;
	}

	public static String getIdname() {
		return idName;
	}

	
	
	
	
}
