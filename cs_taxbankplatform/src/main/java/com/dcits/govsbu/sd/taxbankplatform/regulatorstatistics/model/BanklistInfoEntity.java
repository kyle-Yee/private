package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class BanklistInfoEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String bankName;
	private String pdName;
	private String areaName;
	private String dked;
	private String llsp;
	private String dbfs;
	private String bll;
	private String zcyh;
	private String rzyh;
	private String dksqbs;
	private String dksqze;
	private String pjsdje;
	private String cgsxbs;
	private String cgsxed;
	private String pjsxed;
	private String applyCount;				//产品申请总数
	private String rateOfCredit;				//平均贷款利率
	private String approveDay;				//平均贷款期限
	private String avgAmount;			//平均放款额度
	private String enabled;
	public String getApplyCount() {
		return applyCount;
	}
	public void setApplyCount(String applyCount) {
		this.applyCount = applyCount;
	}
	public String getRateOfCredit() {
		return rateOfCredit;
	}
	public void setRateOfCredit(String rateOfCredit) {
		this.rateOfCredit = rateOfCredit;
	}
	public String getApproveDay() {
		return approveDay;
	}
	public void setApproveDay(String approveDay) {
		this.approveDay = approveDay;
	}
	public String getAvgAmount() {
		return avgAmount;
	}
	public void setAvgAmount(String avgAmount) {
		this.avgAmount = avgAmount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public String getDksqbs() {
		return dksqbs;
	}
	public void setDksqbs(String dksqbs) {
		this.dksqbs = dksqbs;
	}
	public String getDksqze() {
		return dksqze;
	}
	public void setDksqze(String dksqze) {
		this.dksqze = dksqze;
	}
	public String getPjsdje() {
		return pjsdje;
	}
	public void setPjsdje(String pjsdje) {
		this.pjsdje = pjsdje;
	}
	public String getCgsxbs() {
		return cgsxbs;
	}
	public void setCgsxbs(String cgsxbs) {
		this.cgsxbs = cgsxbs;
	}
	public String getCgsxed() {
		return cgsxed;
	}
	public void setCgsxed(String cgsxed) {
		this.cgsxed = cgsxed;
	}
	public String getPjsxed() {
		return pjsxed;
	}
	public void setPjsxed(String pjsxed) {
		this.pjsxed = pjsxed;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getZcyh() {
		return zcyh;
	}
	public void setZcyh(String zcyh) {
		this.zcyh = zcyh;
	}
	public String getRzyh() {
		return rzyh;
	}
	public void setRzyh(String rzyh) {
		this.rzyh = rzyh;
	}
	public String getDked() {
		return dked;
	}
	public void setDked(String dked) {
		this.dked = dked;
	}
	public String getLlsp() {
		return llsp;
	}
	public void setLlsp(String llsp) {
		this.llsp = llsp;
	}
	public String getDbfs() {
		return dbfs;
	}
	public void setDbfs(String dbfs) {
		this.dbfs = dbfs;
	}
	public String getBll() {
		return bll;
	}
	public void setBll(String bll) {
		this.bll = bll;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}
