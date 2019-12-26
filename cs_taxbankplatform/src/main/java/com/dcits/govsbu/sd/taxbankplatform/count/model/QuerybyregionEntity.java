package com.dcits.govsbu.sd.taxbankplatform.count.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class QuerybyregionEntity extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String banklist;
	private String creditnumber;
	private String creditline;
	private String bgxzcs;
	private String dqxzcs;
	private String dhxzcs;
	public String getBanklist() {
		return banklist;
	}
	public void setBanklist(String banklist) {
		this.banklist = banklist;
	}
	public String getCreditnumber() {
		return creditnumber;
	}
	public void setCreditnumber(String creditnumber) {
		this.creditnumber = creditnumber;
	}
	public String getCreditline() {
		return creditline;
	}
	public void setCreditline(String creditline) {
		this.creditline = creditline;
	}
	public String getBgxzcs() {
		return bgxzcs;
	}
	public void setBgxzcs(String bgxzcs) {
		this.bgxzcs = bgxzcs;
	}
	public String getDqxzcs() {
		return dqxzcs;
	}
	public void setDqxzcs(String dqxzcs) {
		this.dqxzcs = dqxzcs;
	}
	public String getDhxzcs() {
		return dhxzcs;
	}
	public void setDhxzcs(String dhxzcs) {
		this.dhxzcs = dhxzcs;
	}

}
