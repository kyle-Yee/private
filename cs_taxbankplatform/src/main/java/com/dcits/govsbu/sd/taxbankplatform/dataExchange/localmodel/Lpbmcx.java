package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localmodel;

import java.io.Serializable;

/**
 * 功能:
 * 获取编码有效期查询
 * @author 16420
 */
public class Lpbmcx implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String SSSQ_Q;
	private String SSSQ_Z;
	
	public String getSSSQ_Q() {
		return SSSQ_Q;
	}
	public void setSSSQ_Q(String sSSQ_Q) {
		SSSQ_Q = sSSQ_Q;
	}
	
	public String getSSSQ_Z() {
		return SSSQ_Z;
	}
	public void setSSSQ_Z(String sSSQ_Z) {
		SSSQ_Z = sSSQ_Z;
	}
}
