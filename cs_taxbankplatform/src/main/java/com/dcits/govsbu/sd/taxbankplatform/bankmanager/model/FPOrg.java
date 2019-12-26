package com.dcits.govsbu.sd.taxbankplatform.bankmanager.model;

import java.io.Serializable;

/**
 * 银行产品实体类
 * @author Sigua.Huang
 * @date 2018年6月21日
 */
public class FPOrg implements Serializable{
	private static final long serialVersionUID = 1L;

	private String fpId;
	private String fpName;

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
	@Override
	public String toString() {
		return "FPOrg [fpId=" + fpId + ", fpName=" + fpName + "]";
	}
	
}
