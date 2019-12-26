package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class TaxPdfCbxxEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<TaxCbxxListEntity> taxCbxxListEntity;
	public List<TaxCbxxListEntity> getTaxCbxxListEntity() {
		return taxCbxxListEntity;
	}
	public void setTaxCbxxListEntity(List<TaxCbxxListEntity> taxCbxxListEntity) {
		this.taxCbxxListEntity = taxCbxxListEntity;
	}
	
}
