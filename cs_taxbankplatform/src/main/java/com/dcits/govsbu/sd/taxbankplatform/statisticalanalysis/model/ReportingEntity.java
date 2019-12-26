package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class ReportingEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String area;
	private String pccode;
	private String zcyh;
	private String zqyzsbl;
	private String rztgs;
	private String sxbs;
	private String sxze;
	private String swjg;//税务机关名称
	private String taxid;//税务机关id
	public String getSwjg() {
		return swjg;
	}
	public void setSwjg(String swjg) {
		this.swjg = swjg;
	}
	public String getTaxid() {
		return taxid;
	}
	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
 
	public String getPccode() {
		return pccode;
	}
	public void setPccode(String pccode) {
		this.pccode = pccode;
	}
	public String getZcyh() {
		return zcyh;
	}
	public void setZcyh(String zcyh) {
		this.zcyh = zcyh;
	}
	public String getZqyzsbl() {
		return zqyzsbl;
	}
	public void setZqyzsbl(String zqyzsbl) {
		this.zqyzsbl = zqyzsbl;
	}
	public String getRztgs() {
		return rztgs;
	}
	public void setRztgs(String rztgs) {
		this.rztgs = rztgs;
	}
	public String getSxbs() {
		return sxbs;
	}
	public void setSxbs(String sxbs) {
		this.sxbs = sxbs;
	}
	public String getSxze() {
		return sxze;
	}
	public void setSxze(String sxze) {
		this.sxze = sxze;
	}
	
}
