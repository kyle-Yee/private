package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author yaofang
 *  银税实际发放贷款情况
 */
public class ActualissuanceLoansEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String totalamount;//总笔数
	private String microamount;//小微企业合计
	private String microamountProportion;//占小微企业笔数 
	
	private String totalquota;//总金额（含大中型企业）
	private String microquota;//小微企业合计金额
	private String quotaProportion;//占小微企业贷款 
	
	private String totalquotachange;//总金额 余额
	private String microquotachange;//小微企业合计  余额
	private String microProportion;//占小微企业余额
	private String totalamountProportion;//
	
	private String badNum;//不良贷款笔数
	private String badSum;//不良贷款金额
	private String badRate;//不良率
	
	public String getBadNum() {
		return badNum;
	}
	public void setBadNum(String badNum) {
		this.badNum = badNum;
	}
	public String getBadSum() {
		return badSum;
	}
	public void setBadSum(String badSum) {
		this.badSum = badSum;
	}
	public String getBadRate() {
		return badRate;
	}
	public void setBadRate(String badRate) {
		this.badRate = badRate;
	}
	public String getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
	public String getMicroamount() {
		return microamount;
	}
	public void setMicroamount(String microamount) {
		this.microamount = microamount;
	}
	public String getMicroquota() {
		return microquota;
	}
	public void setMicroquota(String microquota) {
		this.microquota = microquota;
	}
	public String getTotalquota() {
		return totalquota;
	}
	public void setTotalquota(String totalquota) {
		this.totalquota = totalquota;
	}
	public String getMicroquotachange() {
		return microquotachange;
	}
	public void setMicroquotachange(String microquotachange) {
		this.microquotachange = microquotachange;
	}
	public String getTotalquotachange() {
		return totalquotachange;
	}
	public void setTotalquotachange(String totalquotachange) {
		this.totalquotachange = totalquotachange;
	}
	public String getTotalamountProportion() {
		return totalamountProportion;
	}
	public void setTotalamountProportion(String totalamountProportion) {
		this.totalamountProportion = totalamountProportion;
	}
	public String getMicroamountProportion() {
		return microamountProportion;
	}
	public void setMicroamountProportion(String microamountProportion) {
		this.microamountProportion = microamountProportion;
	}
	public String getQuotaProportion() {
		return quotaProportion;
	}
	public void setQuotaProportion(String quotaProportion) {
		this.quotaProportion = quotaProportion;
	}
	public String getMicroProportion() {
		return microProportion;
	}
	public void setMicroProportion(String microProportion) {
		this.microProportion = microProportion;
	}
	
}
