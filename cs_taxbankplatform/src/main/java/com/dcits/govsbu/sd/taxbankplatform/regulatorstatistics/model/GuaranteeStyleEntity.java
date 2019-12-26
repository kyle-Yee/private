package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @author liwangxiong
 * @date 2016年8月4日
 * @caption 编码表
 */
public class GuaranteeStyleEntity extends BaseEntity{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/*
	 *担保方式名称
	 */
	private String gsname;
	/*
	 *编码code
	 */
	private String gscode;
	/*
	 *有效标志
	 */
	private String enabled;
	
	//--------set 、get 方法
	
	public String getGsname() {
		return gsname;
	}
	public void setGsname(String gsname) {
		this.gsname = gsname;
	}
	public String getGscode() {
		return gscode;
	}
	public void setGscode(String gscode) {
		this.gscode = gscode;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}
