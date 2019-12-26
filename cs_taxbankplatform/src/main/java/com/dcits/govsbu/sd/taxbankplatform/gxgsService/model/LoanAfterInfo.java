package com.dcits.govsbu.sd.taxbankplatform.gxgsService.model;

/**
 * 贷款申请信息接口请求字段
 * @author Sigua.Huang
 * @date 2018年6月27日
 */
public class LoanAfterInfo {
	
	private String dhsxId;
	
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

    private String applyTimeStart;
    
    private String applyTimeEnd;
    
    private String grantFindTimeStart;
    
    private String grantFindTimeEnd;
    
    private String loanGrantTime;

    private String loanApplyAmount;

    private String loanAmount;

    private String drawingBalance;

    private String loanTerm;

    private String loanBalance;

    private String loanTime;

    private String loanOverdue;
    
    private String isExtension;
    
    private String extensionTime;
    
    private String loanAccount;
    
    private String backups;
    
    private String createtime;
    
    private String  grantCode ;

    
	public String getGrantCode() {
		return grantCode;
	}

	public void setGrantCode(String grantCode) {
		this.grantCode = grantCode;
	}

	public String getDhsxId() {
		return dhsxId;
	}

	public void setDhsxId(String dhsxId) {
		this.dhsxId = dhsxId;
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

	public String getApplyTimeStart() {
		return applyTimeStart;
	}

	public void setApplyTimeStart(String applyTimeStart) {
		this.applyTimeStart = applyTimeStart;
	}

	public String getApplyTimeEnd() {
		return applyTimeEnd;
	}

	public void setApplyTimeEnd(String applyTimeEnd) {
		this.applyTimeEnd = applyTimeEnd;
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

	public String getLoanGrantTime() {
		return loanGrantTime;
	}

	public void setLoanGrantTime(String loanGrantTime) {
		this.loanGrantTime = loanGrantTime;
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

	public String getDrawingBalance() {
		return drawingBalance;
	}

	public void setDrawingBalance(String drawingBalance) {
		this.drawingBalance = drawingBalance;
	}

	public String getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}

	public String getLoanBalance() {
		return loanBalance;
	}

	public void setLoanBalance(String loanBalance) {
		this.loanBalance = loanBalance;
	}

	public String getLoanTime() {
		return loanTime;
	}

	public void setLoanTime(String loanTime) {
		this.loanTime = loanTime;
	}

	public String getLoanOverdue() {
		return loanOverdue;
	}

	public void setLoanOverdue(String loanOverdue) {
		this.loanOverdue = loanOverdue;
	}

	public String getIsExtension() {
		return isExtension;
	}

	public void setIsExtension(String isExtension) {
		this.isExtension = isExtension;
	}

	public String getExtensionTime() {
		return extensionTime;
	}

	public void setExtensionTime(String extensionTime) {
		this.extensionTime = extensionTime;
	}

	public String getLoanAccount() {
		return loanAccount;
	}

	public void setLoanAccount(String loanAccount) {
		this.loanAccount = loanAccount;
	}

	public String getBackups() {
		return backups;
	}

	public void setBackups(String backups) {
		this.backups = backups;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "LoanAfterInfo [dhsxId=" + dhsxId + ", businessid=" + businessid + ", tbpName=" + tbpName + ", tbpCode="
				+ tbpCode + ", businessType=" + businessType + ", indName=" + indName + ", indCertType=" + indCertType
				+ ", indCertID=" + indCertID + ", entName=" + entName + ", entCreditID=" + entCreditID + ", entTaxID="
				+ entTaxID + ", applyTimeStart=" + applyTimeStart + ", applyTimeEnd=" + applyTimeEnd
				+ ", grantFindTimeStart=" + grantFindTimeStart + ", grantFindTimeEnd=" + grantFindTimeEnd
				+ ", loanGrantTime=" + loanGrantTime + ", loanApplyAmount=" + loanApplyAmount + ", loanAmount="
				+ loanAmount + ", drawingBalance=" + drawingBalance + ", loanTerm=" + loanTerm + ", loanBalance="
				+ loanBalance + ", loanTime=" + loanTime + ", loanOverdue=" + loanOverdue + ", isExtension="
				+ isExtension + ", extensionTime=" + extensionTime + ", loanAccount=" + loanAccount + ", backups="
				+ backups + ", createtime=" + createtime + "]";
	}
}