package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class LoansdetailsEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String qymc;
	private String sdsj;
	private String sdyh;
	private String sdje;
	private String hpzt;
	private String spsj;
	private String sxje;
	private String sxll;
	private String hymc;
	private String hydm;
	private String applyTotalAmount;//申请贷款总金额
	private String approvedTotalAmount;//授信总金额
	public String getQymc() {
		return qymc;
	}
	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	public String getSdsj() {
		return sdsj;
	}
	public void setSdsj(String sdsj) {
		this.sdsj = sdsj;
	}
	public String getSdyh() {
		return sdyh;
	}
	public void setSdyh(String sdyh) {
		this.sdyh = sdyh;
	}
	public String getSdje() {
		return sdje;
	}
	public void setSdje(String sdje) {
		this.sdje = sdje;
	}
	public String getHpzt() {
		return hpzt;
	}
	public void setHpzt(String hpzt) {
		this.hpzt = hpzt;
	}
	public String getSpsj() {
		return spsj;
	}
	public void setSpsj(String spsj) {
		this.spsj = spsj;
	}
	public String getSxje() {
		return sxje;
	}
	public void setSxje(String sxje) {
		this.sxje = sxje;
	}
	public String getSxll() {
		return sxll;
	}
	public void setSxll(String sxll) {
		this.sxll = sxll;
	}
	public String getHymc() {
		return hymc;
	}
	public void setHymc(String hymc) {
		this.hymc = hymc;
	}
	public String getHydm() {
		return hydm;
	}
	public void setHydm(String hydm) {
		this.hydm = hydm;
	}
	public String getApplyTotalAmount() {
		return applyTotalAmount;
	}
	public void setApplyTotalAmount(String applyTotalAmount) {
		this.applyTotalAmount = applyTotalAmount;
	}
	public String getApprovedTotalAmount() {
		return approvedTotalAmount;
	}
	public void setApprovedTotalAmount(String approvedTotalAmount) {
		this.approvedTotalAmount = approvedTotalAmount;
	}
	
}
