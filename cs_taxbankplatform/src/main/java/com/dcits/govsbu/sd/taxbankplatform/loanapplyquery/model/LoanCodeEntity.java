package com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;


/**
 * LoanCodeEntity.java
 * @author 严添麟
 * @date 2016年8月12日
 * @caption 贷款审批意见表
 */
public class LoanCodeEntity extends BaseEntity{

	/***/
	private static final long serialVersionUID = 1L;
	
	private String lac_name;				//代码名称
	private String lac_code;					//代码code
	private String enabled;					//有效标识
	private String lac_remark;			//备注
	
	public String getLac_name() {
		return lac_name;
	}
	public void setLac_name(String lac_name) {
		this.lac_name = lac_name;
	}
	
	public String getLac_code() {
		return lac_code;
	}
	public void setLac_code(String lac_code) {
		this.lac_code = lac_code;
	}
	
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	public String getLac_remark() {
		return lac_remark;
	}
	public void setLac_remark(String lac_remark) {
		this.lac_remark = lac_remark;
	}
}
