package com.dcits.govsbu.sd.taxbankplatform.bankmanager.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class BusinessRegInfo extends BaseEntity{

	/**
	 * 工商登记信息配置表
	 */
	private static final long serialVersionUID = 1L;
	
	private int allCount;
	
	private int perCount;
	
	private String gsbmc;
	
	private String cftOptions;

	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	public int getPerCount() {
		return perCount;
	}

	public void setPerCount(int perCount) {
		this.perCount = perCount;
	}

	public String getGsbmc() {
		return gsbmc;
	}

	public void setGsbmc(String gsbmc) {
		this.gsbmc = gsbmc;
	}

	public String getCftOptions() {
		return cftOptions;
	}

	public void setCftOptions(String cftOptions) {
		this.cftOptions = cftOptions;
	}
	
	
}
