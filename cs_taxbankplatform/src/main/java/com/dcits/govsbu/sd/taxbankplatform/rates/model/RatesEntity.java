package com.dcits.govsbu.sd.taxbankplatform.rates.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @versions:1.0 
 * @filename：RatesEntity.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午10:27:362:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName RatesEntity
 */
public class RatesEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ratesName;
	
	private String code;
	
	private String enabled;
	
	private String orgId;
	
	private String lr_id;

	public String getRatesName() {
		return ratesName;
	}

	public void setRatesName(String ratesName) {
		this.ratesName = ratesName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getLr_id() {
		return lr_id;
	}

	public void setLr_id(String lr_id) {
		this.lr_id = lr_id;
	}
	
	

}
