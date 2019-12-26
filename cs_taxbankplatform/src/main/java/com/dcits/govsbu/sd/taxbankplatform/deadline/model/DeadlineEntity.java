package com.dcits.govsbu.sd.taxbankplatform.deadline.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class DeadlineEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ldId;
	
	private String deadlineName;
	
	private String code;
	
	private String enabled;
	
	private	String orgId;

	public String getDeadlineName() {
		return deadlineName;
	}

	public void setDeadlineName(String deadlineName) {
		this.deadlineName = deadlineName;
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

	public String getLdId() {
		return ldId;
	}

	public void setLdId(String ldId) {
		this.ldId = ldId;
	}
	
	
}
