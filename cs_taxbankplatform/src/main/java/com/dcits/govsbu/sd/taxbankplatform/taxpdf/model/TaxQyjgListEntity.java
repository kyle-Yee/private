package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.qyfzjg.model.TaxQyfzjgEntity;
import com.dcits.govsbu.sd.taxbankplatform.qytzf.model.TaxQytzfEntity;

public class TaxQyjgListEntity extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TaxQytzfEntity taxQytzfEntity;
	private TaxQyfzjgEntity taxQyfzjgEntity;
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
}
