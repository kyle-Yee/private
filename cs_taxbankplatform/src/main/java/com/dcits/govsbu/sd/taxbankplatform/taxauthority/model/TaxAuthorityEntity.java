package com.dcits.govsbu.sd.taxbankplatform.taxauthority.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 税务机关代码
 * @author Administrator
 *
 */
public class TaxAuthorityEntity extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String taxId;

	private String taxName;

    private String taxPrcId;

	private String taxIs;

    private String enabled;

    public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	
	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	
	public String getTaxPrcId() {
			return taxPrcId;
		}

	public void setTaxPrcId(String taxPrcId) {
			this.taxPrcId = taxPrcId;
		}
	
	public String getTaxIs() {
		return taxIs;
	}

	public void setTaxIs(String taxIs) {
		this.taxIs = taxIs;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

}