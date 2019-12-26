package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.qyfzjg.model.TaxQyfzjgEntity;
import com.dcits.govsbu.sd.taxbankplatform.qytzf.model.TaxQytzfEntity;

public class TaxPdfQyjgEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fddbrxm;
	private String fddbrsfzjlmdm;
	private String sfzjlxmc;
	private String fddbrsfzjhm;
	private String fddbrgddh;
	private String fddbryddh;
	private TaxQytzfEntity taxQytzfEntity;
	private TaxQyfzjgEntity taxQyfzjgEntity;
	
	//private List<TaxQyjgListEntity> taxQyjgListEntity;
	public String getFddbrxm() {
		return fddbrxm;
	}
	public void setFddbrxm(String fddbrxm) {
		this.fddbrxm = fddbrxm;
	}
	public String getFddbrsfzjlmdm() {
		return fddbrsfzjlmdm;
	}
	public void setFddbrsfzjlmdm(String fddbrsfzjlmdm) {
		this.fddbrsfzjlmdm = fddbrsfzjlmdm;
	}
	public String getFddbrsfzjhm() {
		return fddbrsfzjhm;
	}
	public void setFddbrsfzjhm(String fddbrsfzjhm) {
		this.fddbrsfzjhm = fddbrsfzjhm;
	}
	public String getFddbrgddh() {
		return fddbrgddh;
	}
	public void setFddbrgddh(String fddbrgddh) {
		this.fddbrgddh = fddbrgddh;
	}
	public String getFddbryddh() {
		return fddbryddh;
	}
	public void setFddbryddh(String fddbryddh) {
		this.fddbryddh = fddbryddh;
	}
	public TaxQytzfEntity getTaxQytzfEntity() {
		return taxQytzfEntity;
	}
	public void setTaxQytzfEntity(TaxQytzfEntity taxQytzfEntity) {
		this.taxQytzfEntity = taxQytzfEntity;
	}
	public TaxQyfzjgEntity getTaxQyfzjgEntity() {
		return taxQyfzjgEntity;
	}
	public void setTaxQyfzjgEntity(TaxQyfzjgEntity taxQyfzjgEntity) {
		this.taxQyfzjgEntity = taxQyfzjgEntity;
	}
	public String getSfzjlxmc() {
		return sfzjlxmc;
	}
	public void setSfzjlxmc(String sfzjlxmc) {
		this.sfzjlxmc = sfzjlxmc;
	}
	
}
