package com.dcits.govsbu.sd.taxbankplatform.amount.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;


/**
 * 
 * @author 赵宝庆
 * @date 2016年8月8日
 * @caption 金额
 */
public class AmountEntity extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 输入的金额名称
	 */
	private String amountName;
	
	private String code;
	
	private String enabled;
	
	private String orgId;

	public String getAmountName() {
		return amountName;
	}

	public void setAmountName(String amountName) {
		this.amountName = amountName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
