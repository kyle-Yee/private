package com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model;

import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class HtmlTag extends BaseEntity {
	private static final long serialVersionUID = 1L;
    private String htName;

    private String htType;

    private String enabled;

    public List<Productdataitems> getProductditemsList() {
		return productditemsList;
	}

	public void setProductditemsList(List<Productdataitems> productditemsList) {
		this.productditemsList = productditemsList;
	}

	private List<Productdataitems> productditemsList;
    
    


    public String getHtName() {
        return htName;
    }

    public void setHtName(String htName) {
        this.htName = htName;
    }



    public String getHtType() {
		return htType;
	}

	public void setHtType(String htType) {
		this.htType = htType;
	}

	public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}