package com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * LoanProductDataEntity.java
 * @author 严添麟
 * @date 2016年8月25日
 * @caption 产品数据项
 */
public class LoanProductDataEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private String htid;
	public String getHtid() {
		return htid;
	}
	public void setHtid(String htid) {
		this.htid = htid;
	}
	private String pdiName;											//产品数据项名称

	public String getPdiName() {
		return pdiName;
	}
	public void setPdiName(String pdiName) {
		this.pdiName = pdiName;
	}
}
