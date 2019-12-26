package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class IndustriesdetailsEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String hydm;
	private String hymc;
	private String mlbz;
	private String dlbz;
	private String zlbz;
	private String xlbz;
	private String sjhydm;
	private String dksqbs;
	private String dksqze;
	private String pjsdje;
	private String cgsxbs;
	private String cgsxed;
	private String pjsxed;
	public String getHymc() {
		return hymc;
	}
	public void setHymc(String hymc) {
		this.hymc = hymc;
	}
	
	public String getDksqbs() {
		return dksqbs;
	}
	public void setDksqbs(String dksqbs) {
		this.dksqbs = dksqbs;
	}
	public String getDksqze() {
		return dksqze;
	}
	public void setDksqze(String dksqze) {
		this.dksqze = dksqze;
	}
	public String getPjsdje() {
		return pjsdje;
	}
	public void setPjsdje(String pjsdje) {
		this.pjsdje = pjsdje;
	}
	public String getCgsxbs() {
		return cgsxbs;
	}
	public void setCgsxbs(String cgsxbs) {
		this.cgsxbs = cgsxbs;
	}
	public String getCgsxed() {
		return cgsxed;
	}
	public void setCgsxed(String cgsxed) {
		this.cgsxed = cgsxed;
	}
	public String getPjsxed() {
		return pjsxed;
	}
	public void setPjsxed(String pjsxed) {
		this.pjsxed = pjsxed;
	}
	public String getHydm() {
		return hydm;
	}
	public void setHydm(String hydm) {
		this.hydm = hydm;
	}
	public String getMlbz() {
		return mlbz;
	}
	public void setMlbz(String mlbz) {
		this.mlbz = mlbz;
	}
	public String getDlbz() {
		return dlbz;
	}
	public void setDlbz(String dlbz) {
		this.dlbz = dlbz;
	}
	public String getZlbz() {
		return zlbz;
	}
	public void setZlbz(String zlbz) {
		this.zlbz = zlbz;
	}
	public String getXlbz() {
		return xlbz;
	}
	public void setXlbz(String xlbz) {
		this.xlbz = xlbz;
	}
	public String getSjhydm() {
		return sjhydm;
	}
	public void setSjhydm(String sjhydm) {
		this.sjhydm = sjhydm;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final IndustriesdetailsEntity other = (IndustriesdetailsEntity) obj;
		if (this.getHydm().equals(other.getHydm())) {
			return true;
		}else {
			return false;	
		}
	}
}
