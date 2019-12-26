package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.lrbxqykjzd.model.TaxLrbXqykjzdEntity;
import com.dcits.govsbu.sd.taxbankplatform.zcfzbybqykj.model.TaxZcfzbYbqykjEntity;

public class TaxCbxxListEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TaxZcfzbYbqykjEntity taxZcfzbYbqykjEntity;
	private TaxLrbXqykjzdEntity taxLrbXqykjzdEntity;
	public TaxZcfzbYbqykjEntity getTaxZcfzbYbqykjEntity() {
		return taxZcfzbYbqykjEntity;
	}
	public void setTaxZcfzbYbqykjEntity(TaxZcfzbYbqykjEntity taxZcfzbYbqykjEntity) {
		this.taxZcfzbYbqykjEntity = taxZcfzbYbqykjEntity;
	}
	public TaxLrbXqykjzdEntity getTaxLrbXqykjzdEntity() {
		return taxLrbXqykjzdEntity;
	}
	public void setTaxLrbXqykjzdEntity(TaxLrbXqykjzdEntity taxLrbXqykjzdEntity) {
		this.taxLrbXqykjzdEntity = taxLrbXqykjzdEntity;
	}
	
}
