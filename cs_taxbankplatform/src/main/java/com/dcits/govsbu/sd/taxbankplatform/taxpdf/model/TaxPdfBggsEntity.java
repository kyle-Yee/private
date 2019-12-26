package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class TaxPdfBggsEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TaxBggsEntity taxBggsEntity;
	private String ml;//门类
	private String dl;//大类
	private String zl;//中类
	private String xl;//小类
	
	public TaxBggsEntity getTaxBggsEntity() {
		return taxBggsEntity;
	}
	public void setTaxBggsEntity(TaxBggsEntity taxBggsEntity) {
		this.taxBggsEntity = taxBggsEntity;
	}
	public String getMl() {
		return ml;
	}
	public void setMl(String ml) {
		this.ml = ml;
	}
	public String getDl() {
		return dl;
	}
	public void setDl(String dl) {
		this.dl = dl;
	}
	public String getZl() {
		return zl;
	}
	public void setZl(String zl) {
		this.zl = zl;
	}
	public String getXl() {
		return xl;
	}
	public void setXl(String xl) {
		this.xl = xl;
	}
	
}
