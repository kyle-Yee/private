package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @versions:1.0 
 * @filename：LoanExportSWSJ.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午11:02:522:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName LoanExportSWSJ
 */
public class LoanExportSWSJ extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String xh;//序号
	
	private String sqxh;//申请序号

	private String skjnssq;//税款缴纳所属期

	private String sdssjnse;//所得税实际纳税额
	
	private String sdsjmtse;//所得税减免退税额
	
	private String sdsynsehj;//所得税应纳税额合计
	
	private String zzssjnse;//增值税实际纳税额
	
	private String zzsjmtse;//增值税减免退税额
	
	private String zzsynsehj;//增值税应纳税额合计
	
	private String hj;//合计
	
	public String getHj() {
		return hj;
	}

	public void setHj(String hj) {
		this.hj = hj;
	}

	public String getSqxh() {
		return sqxh;
	}
	
	public void setSqxh(String sqxh) {
		this.sqxh = sqxh;
	}
	
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getSkjnssq() {
		return skjnssq;
	}
	
	public void setSkjnssq(String skjnssq) {
		this.skjnssq = skjnssq;
	}

	public String getSdssjnse() {
		return sdssjnse;
	}

	public void setSdssjnse(String sdssjnse) {
		this.sdssjnse = sdssjnse;
	}

	public String getSdsjmtse() {
		return sdsjmtse;
	}

	public void setSdsjmtse(String sdsjmtse) {
		this.sdsjmtse = sdsjmtse;
	}

	public String getSdsynsehj() {
		return sdsynsehj;
	}

	public void setSdsynsehj(String sdsynsehj) {
		this.sdsynsehj = sdsynsehj;
	}

	public String getZzssjnse() {
		return zzssjnse;
	}

	public void setZzssjnse(String zzssjnse) {
		this.zzssjnse = zzssjnse;
	}

	public String getZzsjmtse() {
		return zzsjmtse;
	}

	public void setZzsjmtse(String zzsjmtse) {
		this.zzsjmtse = zzsjmtse;
	}

	public String getZzsynsehj() {
		return zzsynsehj;
	}

	public void setZzsynsehj(String zzsynsehj) {
		this.zzsynsehj = zzsynsehj;
	}

	
}
