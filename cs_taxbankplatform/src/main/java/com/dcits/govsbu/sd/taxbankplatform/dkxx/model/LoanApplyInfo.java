package com.dcits.govsbu.sd.taxbankplatform.dkxx.model;

/**
 * 贷款申请信息接口请求字段
 * @author Sigua.Huang
 * @date 2018年6月27日
 */
public class LoanApplyInfo {
	
	private String dksqId;
	
    private String businessid;
    
    private String tbpName;
    
    private String tbpCode;
    
    private String businessType;

    private String indName;

    private String indCertType;

    private String indCertID;

    private String entName;

    private String entCreditID;

    private String entTaxID;

    private String applyTime;
    
    private String loanApplyAmount;
    
    private String loanAmount;
    
    private String loanTerm;
    
    private String admittance;

    private String createtime;
    
    private String grantFindTimeStart;
    
    private String grantFindTimeEnd;
    
    private String grantCode;
    
	public String getGrantCode() {
		return grantCode;
	}

	public void setGrantCode(String grantCode) {
		this.grantCode = grantCode;
	}

	public String getGrantFindTimeStart() {
		return grantFindTimeStart;
	}

	public void setGrantFindTimeStart(String grantFindTimeStart) {
		this.grantFindTimeStart = grantFindTimeStart;
	}

	public String getGrantFindTimeEnd() {
		return grantFindTimeEnd;
	}

	public void setGrantFindTimeEnd(String grantFindTimeEnd) {
		this.grantFindTimeEnd = grantFindTimeEnd;
	}

	public String getDksqId() {
		return dksqId;
	}

	public void setDksqId(String dksqId) {
		this.dksqId = dksqId;
	}

	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public String getTbpName() {
		return tbpName;
	}

	public void setTbpName(String tbpName) {
		this.tbpName = tbpName;
	}

	public String getTbpCode() {
		return tbpCode;
	}

	public void setTbpCode(String tbpCode) {
		this.tbpCode = tbpCode;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getIndName() {
		return indName;
	}

	public void setIndName(String indName) {
		this.indName = indName;
	}

	public String getIndCertType() {
		return indCertType;
	}

	public void setIndCertType(String indCertType) {
		this.indCertType = indCertType;
	}

	public String getIndCertID() {
		return indCertID;
	}

	public void setIndCertID(String indCertID) {
		this.indCertID = indCertID;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getEntCreditID() {
		return entCreditID;
	}

	public void setEntCreditID(String entCreditID) {
		this.entCreditID = entCreditID;
	}

	public String getEntTaxID() {
		return entTaxID;
	}

	public void setEntTaxID(String entTaxID) {
		this.entTaxID = entTaxID;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getLoanApplyAmount() {
		return loanApplyAmount;
	}

	public void setLoanApplyAmount(String loanApplyAmount) {
		this.loanApplyAmount = loanApplyAmount;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}

	public String getAdmittance() {
		return admittance;
	}

	public void setAdmittance(String admittance) {
		this.admittance = admittance;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}