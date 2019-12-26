package com.dcits.govsbu.sd.taxbankplatform.statistics.model;

import java.io.Serializable;

/**
 * 贷款申请信息接口请求字段
 * @author Sigua.Huang
 * @date 2018年6月27日
 */
public class LoanDetailsInfo implements Serializable{
	
    private static final long serialVersionUID = 1L;

	private String businessid;			//申请单ID
	
    private String entname;				//企业名称
    
    private String entcreditid;			//社会信用代码

    private String tbpname;				//申贷银行
    
    private String fpName;				//产品名称
    
    private String atId;				//授权书编号

    private String atSqsj;				//授权日期

    private String atSqsyxq;			//授权书有效期

    private String atSqqzsj;			//授权查询期起止

    private String loanterm;			//贷款期限
    
    private String applytimestart;		//贷款申请日期
    
    private String loanGrantTime;		//贷款授信日期
    
    private String atSjmc;				//税局名称

    private String loanamount;			//授信金额

    private String isPutLoan;			//是否放款

    private String loantime;			//放款日期

    private String hyMc;				//行业
    
    private String djzclxmc;			//登记注册类型

	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public String getEntname() {
		return entname;
	}

	public void setEntname(String entname) {
		this.entname = entname;
	}

	public String getEntcreditid() {
		return entcreditid;
	}

	public void setEntcreditid(String entcreditid) {
		this.entcreditid = entcreditid;
	}

	public String getTbpname() {
		return tbpname;
	}

	public void setTbpname(String tbpname) {
		this.tbpname = tbpname;
	}

	public String getFpName() {
		return fpName;
	}

	public void setFpName(String fpName) {
		this.fpName = fpName;
	}

	public String getAtId() {
		return atId;
	}

	public void setAtId(String atId) {
		this.atId = atId;
	}

	public String getAtSqsj() {
		return atSqsj;
	}

	public void setAtSqsj(String atSqsj) {
		this.atSqsj = atSqsj;
	}

	public String getAtSqsyxq() {
		return atSqsyxq;
	}

	public void setAtSqsyxq(String atSqsyxq) {
		this.atSqsyxq = atSqsyxq;
	}

	public String getAtSqqzsj() {
		return atSqqzsj;
	}

	public void setAtSqqzsj(String atSqqzsj) {
		this.atSqqzsj = atSqqzsj;
	}

	public String getLoanterm() {
		return loanterm;
	}

	public void setLoanterm(String loanterm) {
		this.loanterm = loanterm;
	}

	public String getApplytimestart() {
		return applytimestart;
	}

	public void setApplytimestart(String applytimestart) {
		this.applytimestart = applytimestart;
	}

	public String getLoanGrantTime() {
		return loanGrantTime;
	}

	public void setLoanGrantTime(String loanGrantTime) {
		this.loanGrantTime = loanGrantTime;
	}

	public String getAtSjmc() {
		return atSjmc;
	}

	public void setAtSjmc(String atSjmc) {
		this.atSjmc = atSjmc;
	}

	public String getLoanamount() {
		return loanamount;
	}

	public void setLoanamount(String loanamount) {
		this.loanamount = loanamount;
	}

	public String getIsPutLoan() {
		return isPutLoan;
	}

	public void setIsPutLoan(String isPutLoan) {
		this.isPutLoan = isPutLoan;
	}

	public String getLoantime() {
		return loantime;
	}

	public void setLoantime(String loantime) {
		this.loantime = loantime;
	}

	public String getHyMc() {
		return hyMc;
	}

	public void setHyMc(String hyMc) {
		this.hyMc = hyMc;
	}

	public String getDjzclxmc() {
		return djzclxmc;
	}

	public void setDjzclxmc(String djzclxmc) {
		this.djzclxmc = djzclxmc;
	}
}