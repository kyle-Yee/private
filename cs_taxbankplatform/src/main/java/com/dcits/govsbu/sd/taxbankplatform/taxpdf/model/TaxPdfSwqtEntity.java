package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class TaxPdfSwqtEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String qymc;
	private String nsrsbh;
	private List<TaxSwqtListEntity> taxSwqtListEntity;
	private List<QsxxEntity> qsxxEntity;
	public String getQymc() {
		return qymc;
	}
	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	public List<TaxSwqtListEntity> getTaxSwqtListEntity() {
		return taxSwqtListEntity;
	}
	public void setTaxSwqtListEntity(List<TaxSwqtListEntity> taxSwqtListEntity) {
		this.taxSwqtListEntity = taxSwqtListEntity;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public List<QsxxEntity> getQsxxEntity() {
		return qsxxEntity;
	}
	public void setQsxxEntity(List<QsxxEntity> qsxxEntity) {
		this.qsxxEntity = qsxxEntity;
	}
	
}
