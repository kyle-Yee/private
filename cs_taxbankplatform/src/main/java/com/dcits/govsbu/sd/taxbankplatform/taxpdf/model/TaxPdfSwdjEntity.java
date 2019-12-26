package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class TaxPdfSwdjEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dj;
	private String year;
	private String nsrzglxdm;
	private String zgswjgdm;
	private String zgswskfjdm;
	private List<TaxSwdjListEntity> taxSwdjList;
	public String getDj() {
		return dj;
	}
	public void setDj(String dj) {
		this.dj = dj;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getNsrzglxdm() {
		return nsrzglxdm;
	}
	public void setNsrzglxdm(String nsrzglxdm) {
		this.nsrzglxdm = nsrzglxdm;
	}
	public String getZgswjgdm() {
		return zgswjgdm;
	}
	public void setZgswjgdm(String zgswjgdm) {
		this.zgswjgdm = zgswjgdm;
	}
	public List<TaxSwdjListEntity> getTaxSwdjList() {
		return taxSwdjList;
	}
	public void setTaxSwdjList(List<TaxSwdjListEntity> taxSwdjList) {
		this.taxSwdjList = taxSwdjList;
	}
	public String getZgswskfjdm() {
		return zgswskfjdm;
	}
	public void setZgswskfjdm(String zgswskfjdm) {
		this.zgswskfjdm = zgswskfjdm;
	}
	
}
