package com.dcits.govsbu.sd.taxbankplatform.organization.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class TaxEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orgid;
	private String orgname;
	private String tax;
	private String tax1;
	private String tax2;
	private String tax3;
	private String tax4;
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getTax1() {
		return tax1;
	}
	public void setTax1(String tax1) {
		this.tax1 = tax1;
	}
	public String getTax2() {
		return tax2;
	}
	public void setTax2(String tax2) {
		this.tax2 = tax2;
	}
	public String getTax3() {
		return tax3;
	}
	public void setTax3(String tax3) {
		this.tax3 = tax3;
	}
	public String getTax4() {
		return tax4;
	}
	public void setTax4(String tax4) {
		this.tax4 = tax4;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	
}
