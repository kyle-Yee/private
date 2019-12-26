package com.dcits.govsbu.sd.taxbankplatform.statistics.model;

import java.io.Serializable;

/**
 * 贷款统计报表实体
 * @author Sigua.Huang
 * @date 2018年6月30日
 */
public class LoanCompreInfo implements Serializable{
	
    private static final long serialVersionUID = 1L;

	private String businessid;			//申请单ID
	
	private String tbpname;				//银行
	
    private String tbpcode;				//银行代码
    
    private String fpId;				//产品id
    
    private String fpName;				//产品名称

    private String applyEntCount;		//申请企业数量

    private String applyLoanCount;		//申请贷款笔数
    
    private String grantQueryCount;		//授权查询笔数
    
    private String auditApproveCount;	//审批通过笔数

    private String auditFailureCount;	//审批不予通过笔数

    private String auditingCount;		//在审批数量

    private String loanAmountSum;		//审批授信金额合计##

    private String loanPutOutCount;		//已放款客户数
    
    private String loanBalanceCount;	//贷款余额户数
    
    private String loanApplyAmountSum;	//申请贷款总额
    
    private String loanApplyAmountAvg;	//平均申贷金额

    private String loanAmountAvg;		//平均授信金额
    
    private String loanBalance;		//贷款余额
    
  

	//为空的置零
  	private String nullToZero(Object obj){
  		String srz = String.valueOf(obj);
  		if(null == srz || "null".equals(srz) || "Null".equals(srz)){
  			return "0";
  		}
  		return srz;
  	}
    
    public String getLoanBalance() {
  		return nullToZero(loanBalance);
  	}

  	public void setLoanBalance(String loanBalance) {
  		this.loanBalance = loanBalance;
  	}
  	
	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public String getTbpname() {
		return tbpname;
	}

	public void setTbpname(String tbpname) {
		this.tbpname = tbpname;
	}

	public String getTbpcode() {
		return tbpcode;
	}

	public void setTbpcode(String tbpcode) {
		this.tbpcode = tbpcode;
	}

	public String getFpId() {
		return fpId;
	}

	public void setFpId(String fpId) {
		this.fpId = fpId;
	}

	public String getFpName() {
		return fpName;
	}

	public void setFpName(String fpName) {
		this.fpName = fpName;
	}

	public String getApplyEntCount() {
		return nullToZero(applyEntCount);
	}

	public void setApplyEntCount(String applyEntCount) {
		this.applyEntCount = applyEntCount;
	}

	public String getApplyLoanCount() {
		return nullToZero(applyLoanCount);
	}

	public void setApplyLoanCount(String applyLoanCount) {
		this.applyLoanCount = applyLoanCount;
	}

	public String getGrantQueryCount() {
		return nullToZero(grantQueryCount);
	}

	public void setGrantQueryCount(String grantQueryCount) {
		this.grantQueryCount = grantQueryCount;
	}

	public String getAuditApproveCount() {
		return nullToZero(auditApproveCount);
	}

	public void setAuditApproveCount(String auditApproveCount) {
		this.auditApproveCount = auditApproveCount;
	}

	public String getAuditFailureCount() {
		return nullToZero(auditFailureCount);
	}

	public void setAuditFailureCount(String auditFailureCount) {
		this.auditFailureCount = auditFailureCount;
	}

	public String getAuditingCount() {
		return nullToZero(auditingCount);
	}

	public void setAuditingCount(String auditingCount) {
		this.auditingCount = auditingCount;
	}

	public String getLoanAmountSum() {
		return nullToZero(loanAmountSum);
	}

	public void setLoanAmountSum(String loanAmountSum) {
		this.loanAmountSum = loanAmountSum;
	}

	public String getLoanPutOutCount() {
		return nullToZero(loanPutOutCount);
	}

	public void setLoanPutOutCount(String loanPutOutCount) {
		this.loanPutOutCount = loanPutOutCount;
	}

	public String getLoanBalanceCount() {
		return nullToZero(loanBalanceCount);
	}

	public void setLoanBalanceCount(String loanBalanceCount) {
		this.loanBalanceCount = loanBalanceCount;
	}

	public String getLoanApplyAmountSum() {
		return nullToZero(loanApplyAmountSum);
	}

	public void setLoanApplyAmountSum(String loanApplyAmountSum) {
		this.loanApplyAmountSum = loanApplyAmountSum;
	}

	public String getLoanApplyAmountAvg() {
		return nullToZero(loanApplyAmountAvg);
	}

	public void setLoanApplyAmountAvg(String loanApplyAmountAvg) {
		this.loanApplyAmountAvg = loanApplyAmountAvg;
	}

	public String getLoanAmountAvg() {
		return nullToZero(loanAmountAvg);
	}

	public void setLoanAmountAvg(String loanAmountAvg) {
		this.loanAmountAvg = loanAmountAvg;
	}

	@Override
	public String toString() {
		return "LoanCompreInfo [businessid=" + businessid + ", tbpname=" + tbpname + ", tbpcode=" + tbpcode + ", fpId="
				+ fpId + ", fpName=" + fpName + ", applyEntCount=" + applyEntCount + ", applyLoanCount="
				+ applyLoanCount + ", grantQueryCount=" + grantQueryCount + ", auditApproveCount=" + auditApproveCount
				+ ", auditFailureCount=" + auditFailureCount + ", auditingCount=" + auditingCount + ", loanAmountSum="
				+ loanAmountSum + ", loanPutOutCount=" + loanPutOutCount + ", loanBalanceCount=" + loanBalanceCount
				+ ", loanApplyAmountSum=" + loanApplyAmountSum + ", loanApplyAmountAvg=" + loanApplyAmountAvg
				+ ", loanAmountAvg=" + loanAmountAvg + "]";
	}
}
