package com.dcits.govsbu.sd.taxbankplatform.pdf.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class CqPdfWfwzEntity extends BaseEntity  {

	
		/**  描述   (@author: zhongyj) */      
	   //违法违章明细实体 
	private static final long serialVersionUID = 1L;
	private String djrq;
	private String wfss;
	private String wfwzlx;
	private String wfxwclztdm;
	private String larq;
	public String getDjrq() {
		return djrq;
	}
	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}
	public String getWfss() {
		return wfss;
	}
	public void setWfss(String wfss) {
		this.wfss = wfss;
	}
	public String getWfwzlx() {
		return wfwzlx;
	}
	public void setWfwzlx(String wfwzlx) {
		this.wfwzlx = wfwzlx;
	}
	public String getWfxwclztdm() {
		return wfxwclztdm;
	}
	public void setWfxwclztdm(String wfxwclztdm) {
		this.wfxwclztdm = wfxwclztdm;
	}
	public String getLarq() {
		return larq;
	}
	public void setLarq(String larq) {
		this.larq = larq;
	}
	
}
