package com.dcits.govsbu.sd.taxbankplatform.count.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class BusinessdetailsEntity extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String nsrmc;
	private String sdsj;
	private String bankname;
	private String laamount;
	private String lastatus;
	private String lascode;
	private String sxsj;
    private String creditquota;
	private String larrate;
	private String report;
	private String orgname;
	
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getSdsj() {
		return sdsj;
	}
	public void setSdsj(String sdsj) {
		this.sdsj = sdsj;
	}
	public String getSxsj() {
		return sxsj;
	}
	public void setSxsj(String sxsj) {
		this.sxsj = sxsj;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getLaamount() {
		return laamount;
	}
	public void setLaamount(String laamount) {
		this.laamount = laamount;
	}
	public String getLastatus() {
		return lastatus;
	}
	public void setLastatus(String lastatus) {
		this.lastatus = lastatus;
	}
	
	public String getLascode() {
		return lascode;
	}
	public void setLascode(String lascode) {
		this.lascode = lascode;
	}
	public String getCreditquota() {
		return creditquota;
	}
	public void setCreditquota(String creditquota) {
		this.creditquota = creditquota;
	}
	public String getLarrate() {
		return larrate;
	}
	public void setLarrate(String larrate) {
		this.larrate = larrate;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	
}
