package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
//下载报告进行校验的数据项
public class DownLoadJiaoYanEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String amount;
	private String loandeadline;
	private String rwid;
	private String dj;
	private String nsrzglxdm;
	private String fddbrxm;
	private String fddbrsfzjhm;
	private String fddbrsfzjlmdm;
	private String fddbrbzxx;
	private String fddbryddh;
	private String fddbrgddh;
	private String xxse;
	private String jxse;
	private String qymc;
	
	public String getQymc() {
		return qymc;
	}
	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getLoandeadline() {
		return loandeadline;
	}
	public void setLoandeadline(String loandeadline) {
		this.loandeadline = loandeadline;
	}
	public String getRwid() {
		return rwid;
	}
	public void setRwid(String rwid) {
		this.rwid = rwid;
	}
	public String getDj() {
		return dj;
	}
	public void setDj(String dj) {
		this.dj = dj;
	}
	public String getNsrzglxdm() {
		return nsrzglxdm;
	}
	public void setNsrzglxdm(String nsrzglxdm) {
		this.nsrzglxdm = nsrzglxdm;
	}
	public String getFddbrxm() {
		return fddbrxm;
	}
	public void setFddbrxm(String fddbrxm) {
		this.fddbrxm = fddbrxm;
	}
	public String getFddbrsfzjhm() {
		return fddbrsfzjhm;
	}
	public void setFddbrsfzjhm(String fddbrsfzjhm) {
		this.fddbrsfzjhm = fddbrsfzjhm;
	}
	public String getFddbrsfzjlmdm() {
		return fddbrsfzjlmdm;
	}
	public void setFddbrsfzjlmdm(String fddbrsfzjlmdm) {
		this.fddbrsfzjlmdm = fddbrsfzjlmdm;
	}
	public String getFddbrbzxx() {
		return fddbrbzxx;
	}
	public void setFddbrbzxx(String fddbrbzxx) {
		this.fddbrbzxx = fddbrbzxx;
	}
	public String getFddbryddh() {
		return fddbryddh;
	}
	public void setFddbryddh(String fddbryddh) {
		this.fddbryddh = fddbryddh;
	}
	public String getFddbrgddh() {
		return fddbrgddh;
	}
	public void setFddbrgddh(String fddbrgddh) {
		this.fddbrgddh = fddbrgddh;
	}
	public String getXxse() {
		return xxse;
	}
	public void setXxse(String xxse) {
		this.xxse = xxse;
	}
	public String getJxse() {
		return jxse;
	}
	public void setJxse(String jxse) {
		this.jxse = jxse;
	}
}
