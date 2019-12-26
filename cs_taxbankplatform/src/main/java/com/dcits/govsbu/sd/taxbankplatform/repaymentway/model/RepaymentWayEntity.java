package com.dcits.govsbu.sd.taxbankplatform.repaymentway.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;


/**
 * @author liwangxiong
 * @date 2016年8月4日
 * @caption 编码表
 */
public class RepaymentWayEntity extends BaseEntity{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/*
	 *还款方式
	 */
	private String rwname;
	/*
	 *编码code
	 */
	private String rwcode;
	/*
	 *有效标志（Y:有效 N:无效）
	 */
	private String enabled;
	
	
	//--------set 、get 方法
	
	
	public String getRwname() {
		return rwname;
	}
	public void setRwname(String rwname) {
		this.rwname = rwname;
	}
	public String getRwcode() {
		return rwcode;
	}
	public void setRwcode(String rwcode) {
		this.rwcode = rwcode;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}
