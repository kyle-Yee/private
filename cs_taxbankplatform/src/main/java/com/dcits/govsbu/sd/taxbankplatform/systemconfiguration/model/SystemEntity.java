package com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 *
 */
public class SystemEntity extends BaseEntity{
	
	    
	private static final long serialVersionUID = 1L;
	
	private String xtcs;
	private String xtmc;
	private String enabled;
	private String createdname;
	public String getXtcs() {
		return xtcs;
	}
	public void setXtcs(String xtcs) {
		this.xtcs = xtcs;
	}
	public String getXtmc() {
		return xtmc;
	}
	public void setXtmc(String xtmc) {
		this.xtmc = xtmc;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getCreatedname() {
		return createdname;
	}
	public void setCreatedname(String createdname) {
		this.createdname = createdname;
	}
	
}
