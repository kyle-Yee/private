package com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * LoanStatusEntity.java
 * @author 严添麟
 * @date 2016年8月11日
 * @caption 贷款申请状态表
 */
public class LoanStatusEntity extends BaseEntity{
	
	/** */
	private static final long serialVersionUID = 1L;
	
	private String las_name;				//状态名称
	private String las_code;					//状态代码
	private String enabled;					//是否有效标识
	private String las_remark;			//备注
	
	public String getLas_name() {
		return las_name;
	}
	public void setLas_name(String las_name) {
		this.las_name = las_name;
	}
	
	public String getLas_code() {
		return las_code;
	}
	public void setLas_code(String las_code) {
		this.las_code = las_code;
	}
	
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	public String getLas_remark() {
		return las_remark;
	}
	public void setLas_remark(String las_remark) {
		this.las_remark = las_remark;
	}
}
