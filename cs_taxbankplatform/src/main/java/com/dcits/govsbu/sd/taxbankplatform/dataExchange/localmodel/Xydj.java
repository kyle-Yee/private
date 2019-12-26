package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localmodel;

import java.io.Serializable;

/**
 * 功能:
 * 纳税人信用等级
 * @author 16420
 */
public class Xydj implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String DJ;
	private String YEAR;
	
	
	public String getDJ() {
		return DJ;
	}
	public void setDJ(String dJ) {
		DJ = dJ;
	}
	
	public String getYEAR() {
		return YEAR;
	}
	public void setYEAR(String yEAR) {
		YEAR = yEAR;
	}
}