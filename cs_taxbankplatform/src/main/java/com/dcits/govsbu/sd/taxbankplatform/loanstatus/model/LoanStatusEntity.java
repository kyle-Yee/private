package com.dcits.govsbu.sd.taxbankplatform.loanstatus.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @author liwangxiong
 * @date 2016年8月4日
 * @caption 贷款状态表
 */
public class LoanStatusEntity extends BaseEntity{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	private String ls_id;
	
	/*
	 *贷款状态名称
	 */
	private String lsname;
	/*
	 *编码code
	 */
	private String lscode;
	/*
	 *有效标志（Y:有效N:无效）
	 */
	private String enabled;
	
	//--------set 、get 方法
	
	
	
	public String getLsname() {
		return lsname;
	}
	public String getLs_id() {
		return ls_id;
	}
	public void setLs_id(String ls_id) {
		this.ls_id = ls_id;
	}
	public void setLsname(String lsname) {
		this.lsname = lsname;
	}
	public String getLscode() {
		return lscode;
	}
	public void setLscode(String lscode) {
		this.lscode = lscode;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
}
