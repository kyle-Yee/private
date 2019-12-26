package com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class Productdataitemvalues extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private Productdataitems productdataitems;

	public Productdataitems getProductdataitems() {
		return productdataitems;
	}

	public void setProductdataitems(Productdataitems productdataitems) {
		this.productdataitems = productdataitems;
	}

	private String pdivName;

	private Integer pdivSeq;

	public String getPdivName() {
		return pdivName;
	}

	public void setPdivName(String pdivName) {
		this.pdivName = pdivName;
	}

	public Integer getPdivSeq() {
		return pdivSeq;
	}

	public void setPdivSeq(Integer pdivSeq) {
		this.pdivSeq = pdivSeq;
	}






	
}