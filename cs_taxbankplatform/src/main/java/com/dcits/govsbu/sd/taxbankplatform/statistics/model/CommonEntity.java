package com.dcits.govsbu.sd.taxbankplatform.statistics.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * 行业简类
 * @author Sigua.Huang
 * @date 2018年6月29日
 */
public class CommonEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String dm;
	private String mc;
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	
}
