package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localmodel;

import java.io.Serializable;

/**
 * 功能:
 * 企业预授信明细反馈信息
 * @author 16420
 */
public class Ysx implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String NSRSBH;
	private String CGBS;
	
	
	public String getNSRSBH() {
		return NSRSBH;
	}
	public void setNSRSBH(String nSRSBH) {
		NSRSBH = nSRSBH;
	}
	
	public String getCGBS() {
		return CGBS;
	}
	public void setCGBS(String cGBS) {
		CGBS = cGBS;
	}
}