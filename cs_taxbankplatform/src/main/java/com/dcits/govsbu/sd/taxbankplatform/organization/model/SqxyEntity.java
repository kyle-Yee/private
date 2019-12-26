package com.dcits.govsbu.sd.taxbankplatform.organization.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class SqxyEntity extends BaseEntity{
    /**
     * 税局授权协议类型
     *author:yaofang
     */
	private static final long serialVersionUID = 1L;
	private String sqcname;//税局授权协议类型名称
	private String sqccode;
	private String enabled;
	private String sqcremark;
	public String getSqcname() {
		return sqcname;
	}
	public void setSqcname(String sqcname) {
		this.sqcname = sqcname;
	}
	public String getSqccode() {
		return sqccode;
	}
	public void setSqccode(String sqccode) {
		this.sqccode = sqccode;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getSqcremark() {
		return sqcremark;
	}
	public void setSqcremark(String sqcremark) {
		this.sqcremark = sqcremark;
	}
	
}
