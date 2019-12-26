package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.qybgxx.model.TaxQybgxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.sswfxwdj.model.TaxSswfxwdjEntity;

public class TaxSwqtListEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//企业税务变更信息
	private TaxQybgxxEntity taxQybgxxEntity;
	//企业违法违章信息
	private TaxSswfxwdjEntity taxSswfxwdjEntity;
	public TaxQybgxxEntity getTaxQybgxxEntity() {
		return taxQybgxxEntity;
	}
	public void setTaxQybgxxEntity(TaxQybgxxEntity taxQybgxxEntity) {
		this.taxQybgxxEntity = taxQybgxxEntity;
	}
	public TaxSswfxwdjEntity getTaxSswfxwdjEntity() {
		return taxSswfxwdjEntity;
	}
	public void setTaxSswfxwdjEntity(TaxSswfxwdjEntity taxSswfxwdjEntity) {
		this.taxSswfxwdjEntity = taxSswfxwdjEntity;
	}
}
