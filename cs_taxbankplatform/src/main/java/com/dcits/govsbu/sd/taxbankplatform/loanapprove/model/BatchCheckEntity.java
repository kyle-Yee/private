package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import java.io.Serializable;

//批量审批excel的数据
public class BatchCheckEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int rowNum;//在列表中的行号

	private String nsrsbh;//纳税人识别号
	
	private String yhsqxh;//银行申请序号
	
	private String result;//银行批贷结果
	
	private String sxje;//授信金额
	
	private String sxzq;//授信周期
	
	private String sprq;//审批日期
	
	private String dkqxStart;//贷款期限起
	
	private String dkqxEnd;//贷款期限止
	
	private String sxll;//授信利率 
	
	private String hkfs;//还款方式
	
	private String bankHkfs;//银行填写的还款方式（记录用于导出成功失败记录）
	
	public String getBankHkfs() {
		return bankHkfs;
	}

	public void setBankHkfs(String bankHkfs) {
		this.bankHkfs = bankHkfs;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getYhsqxh() {
		return yhsqxh;
	}

	public void setYhsqxh(String yhsqxh) {
		this.yhsqxh = yhsqxh;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSxje() {
		return sxje;
	}

	public void setSxje(String sxje) {
		this.sxje = sxje;
	}

	public String getSxzq() {
		return sxzq;
	}

	public void setSxzq(String sxzq) {
		this.sxzq = sxzq;
	}

	public String getSprq() {
		return sprq;
	}

	public void setSprq(String sprq) {
		this.sprq = sprq;
	}

	public String getDkqxStart() {
		return dkqxStart;
	}

	public void setDkqxStart(String dkqxStart) {
		this.dkqxStart = dkqxStart;
	}

	public String getDkqxEnd() {
		return dkqxEnd;
	}

	public void setDkqxEnd(String dkqxEnd) {
		this.dkqxEnd = dkqxEnd;
	}

	public String getSxll() {
		return sxll;
	}

	public void setSxll(String sxll) {
		this.sxll = sxll;
	}

	public String getHkfs() {
		return hkfs;
	}

	public void setHkfs(String hkfs) {
		this.hkfs = hkfs;
	}
	
	
	
	
	
	
	

}