package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.sbxx.model.TaxSbxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.sswfxwdj.model.TaxSswfxwdjEntity;

public class TaxSwdjListEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TaxSbxxEntity taxSbxxEntity;
	private TaxSswfxwdjEntity taxSswfxwdjEntity;
	public TaxSbxxEntity getTaxSbxxEntity() {
		return taxSbxxEntity;
	}

	public void setTaxSbxxEntity(TaxSbxxEntity taxSbxxEntity) {
		this.taxSbxxEntity = taxSbxxEntity;
	}

	public TaxSswfxwdjEntity getTaxSswfxwdjEntity() {
		return taxSswfxwdjEntity;
	}

	public void setTaxSswfxwdjEntity(TaxSswfxwdjEntity taxSswfxwdjEntity) {
		this.taxSswfxwdjEntity = taxSswfxwdjEntity;
	}
	
}
