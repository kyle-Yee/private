package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class TaxPdfGsdjEntity extends BaseEntity {


	
		/**  描述   (@author: zhongyj) */      
	    
	private static final long serialVersionUID = 1L;
	private TaxGsdjEntity taxGsdjEntity;
	public TaxGsdjEntity getTaxGsdjEntity() {
		return taxGsdjEntity;
	}
	public void setTaxGsdjEntity(TaxGsdjEntity taxGsdjEntity) {
		this.taxGsdjEntity = taxGsdjEntity;
	}
}
