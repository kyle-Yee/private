package com.dcits.govsbu.sd.taxbankplatform.count.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class DataCountEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String dataName;
	private Long dataCount;
	private String unit;
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public Long getDataCount() {
		return dataCount;
	}
	public void setDataCount(Long dataCount) {
		this.dataCount = dataCount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
