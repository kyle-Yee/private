package com.dcits.govsbu.sd.taxbankplatform.organization.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class RzxyEntity extends BaseEntity{
    /**
     * 认证协议类型
     *author:yaofang
     */
	private static final long serialVersionUID = 1L;
	private String rzcname;//认证协议类型名称
	private String rzccode;
	private String enabled;
	public String getRzcname() {
		return rzcname;
	}
	public void setRzcname(String rzcname) {
		this.rzcname = rzcname;
	}
	public String getRzccode() {
		return rzccode;
	}
	public void setRzccode(String rzccode) {
		this.rzccode = rzccode;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getRzcremark() {
		return rzcremark;
	}
	public void setRzcremark(String rzcremark) {
		this.rzcremark = rzcremark;
	}
	private String rzcremark;
	
}
