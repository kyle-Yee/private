package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import java.util.Date;
import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * LoanApplyFinalEntity.java
 * 
 * @caption 贷款审批结果表
 */
public class LoanApplyFinalEndEntity extends BaseEntity{
	
	/** */
	private static final long serialVersionUID = 1L;
	private String lae_id;			
	//区域id
	private String lafs_id;								
	private String laf_id;								
	private String nsrmc;			
	private String nsrbh;				
	private Date lae_endDate;  
    private String lae_credit_quota;
    private int lae_overdue_count;
    private String bankloan_type;
    
	public String getLafs_id() {
		return lafs_id;
	}
	public void setLafs_id(String lafs_id) {
		this.lafs_id = lafs_id;
	}
	public String getLaf_id() {
		return laf_id;
	}
	public void setLaf_id(String laf_id) {
		this.laf_id = laf_id;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getNsrbh() {
		return nsrbh;
	}
	public void setNsrbh(String nsrbh) {
		this.nsrbh = nsrbh;
	}
	public Date getLae_endDate() {
		return lae_endDate;
	}
	public void setLae_endDate(Date lae_endDate) {
		this.lae_endDate = lae_endDate;
	}
	public String getLae_credit_quota() {
		return lae_credit_quota;
	}
	public void setLae_credit_quota(String lae_credit_quota) {
		this.lae_credit_quota = lae_credit_quota;
	}
	public int getLae_overdue_count() {
		return lae_overdue_count;
	}
	public void setLae_overdue_count(int lae_overdue_count) {
		this.lae_overdue_count = lae_overdue_count;
	}
	public String getBankloan_type() {
		return bankloan_type;
	}
	public void setBankloan_type(String bankloan_type) {
		this.bankloan_type = bankloan_type;
	}
	public String getLae_id() {
		return lae_id;
	}
	public void setLae_id(String lae_id) {
		this.lae_id = lae_id;
	}
}
