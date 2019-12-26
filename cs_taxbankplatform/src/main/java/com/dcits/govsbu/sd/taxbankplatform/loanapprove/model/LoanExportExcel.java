package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @versions:1.0 
 * @filename：LoanExportExcel.java
 * @Company:东方微银
 * @Created: 2017-4-20下午下午4:13:152:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName LoanExportExcel
 */
public class LoanExportExcel extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String laId;			//申请id
	private String larCreditQuota;	//预授信额度
	private String larOpinion;		//准入意见
	private String laApplyTime;		//申请时间
	private String laStatus;		//审批状态id
	private String lasBankStatus;	//审批状态名称
	private String fpName;			//产品名称
	private String nsryhxxQymc;		//企业名称
	private String nsrsbh;			//纳税人识别号
	private String laAmount;		//申请授信额度(万元)
	private String laRepayLoanDeadline;//贷款期限
	private String rwId;			//还款方式
	private String nsrmc;			//涉税信息：纳税人名称
	private String zzjgDm; 			//组织机构代码证号
	private String zcdz;			//注册地址
	private String zcdlxdh;			//注册地址电话
	private String fddbrmc;			//法定代表人姓名
	private String sqxh;			//申请单序号
	private String frsjh;			//法人手机号
	private String nsrglzt;			//纳税人管理状态
	private List<LoanExportSWSJ> swsjList;//税务数据List
	
	
	public String getNsrglzt() {
		return nsrglzt;
	}
	public void setNsrglzt(String nsrglzt) {
		this.nsrglzt = nsrglzt;
	}
	public String getFrsjh() {
		return frsjh;
	}
	public void setFrsjh(String frsjh) {
		this.frsjh = frsjh;
	}
	public String getLaId() {
		return laId;
	}
	public List<LoanExportSWSJ> getSwsjList() {
		return swsjList;
	}
	public void setSwsjList(List<LoanExportSWSJ> swsjList) {
		this.swsjList = swsjList;
	}
	public void setLaId(String laId) {
		this.laId = laId;
	}
	public String getLarCreditQuota() {
		return larCreditQuota;
	}
	public void setLarCreditQuota(String larCreditQuota) {
		this.larCreditQuota = larCreditQuota;
	}
	public String getLarOpinion() {
		return larOpinion;
	}
	public void setLarOpinion(String larOpinion) {
		this.larOpinion = larOpinion;
	}
	public String getLaApplyTime() {
		return laApplyTime;
	}
	public void setLaApplyTime(String laApplyTime) {
		this.laApplyTime = laApplyTime;
	}
	public String getLaStatus() {
		return laStatus;
	}
	public void setLaStatus(String laStatus) {
		this.laStatus = laStatus;
	}
	public String getLasBankStatus() {
		return lasBankStatus;
	}
	public void setLasBankStatus(String lasBankStatus) {
		this.lasBankStatus = lasBankStatus;
	}
	public String getFpName() {
		return fpName;
	}
	public void setFpName(String fpName) {
		this.fpName = fpName;
	}
	public String getNsryhxxQymc() {
		return nsryhxxQymc;
	}
	public void setNsryhxxQymc(String nsryhxxQymc) {
		this.nsryhxxQymc = nsryhxxQymc;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getLaAmount() {
		return laAmount;
	}
	public void setLaAmount(String laAmount) {
		this.laAmount = laAmount;
	}
	public String getLaRepayLoanDeadline() {
		return laRepayLoanDeadline;
	}
	public void setLaRepayLoanDeadline(String laRepayLoanDeadline) {
		this.laRepayLoanDeadline = laRepayLoanDeadline;
	}
	public String getRwId() {
		return rwId;
	}
	public void setRwId(String rwId) {
		this.rwId = rwId;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getZzjgDm() {
		return zzjgDm;
	}
	public void setZzjgDm(String zzjgDm) {
		this.zzjgDm = zzjgDm;
	}
	public String getZcdz() {
		return zcdz;
	}
	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}
	public String getZcdlxdh() {
		return zcdlxdh;
	}
	public void setZcdlxdh(String zcdlxdh) {
		this.zcdlxdh = zcdlxdh;
	}
	public String getFddbrmc() {
		return fddbrmc;
	}
	public void setFddbrmc(String fddbrmc) {
		this.fddbrmc = fddbrmc;
	}
	public String getSqxh() {
		return sqxh;
	}
	public void setSqxh(String sqxh) {
		this.sqxh = sqxh;
	}
}

