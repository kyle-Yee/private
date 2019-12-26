package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TaxBggsEntity implements Serializable{
	
		/**  描述   (@author: zhongyj) */      
	    
	private static final long serialVersionUID = 1L;
	private String qymc;
	private String fddbrxm;
	private String zczb;
	private String zzjgkxdm;
	private String dj;
	private String hydm;
	private int djzclxDm;
	private Date djrq;
	private String nsrsbh;
	private BigDecimal znsed;//近两年总纳税额度
	private String djzclxmc;
	public String getDjzclxmc() {
		return djzclxmc;
	}
	public void setDjzclxmc(String djzclxmc) {
		this.djzclxmc = djzclxmc;
	}
	public String getQymc() {
		return qymc;
	}
	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	public String getFddbrxm() {
		return fddbrxm;
	}
	public void setFddbrxm(String fddbrxm) {
		this.fddbrxm = fddbrxm;
	}
	public String getZczb() {
		return zczb;
	}
	public void setZczb(String zczb) {
		this.zczb = zczb;
	}
	public String getZzjgkxdm() {
		return zzjgkxdm;
	}
	public void setZzjgkxdm(String zzjgkxdm) {
		this.zzjgkxdm = zzjgkxdm;
	}
	public String getDj() {
		return dj;
	}
	public void setDj(String dj) {
		this.dj = dj;
	}
	public String getHydm() {
		return hydm;
	}
	public void setHydm(String hydm) {
		this.hydm = hydm;
	}
	public int getDjzclxDm() {
		return djzclxDm;
	}
	public void setDjzclxDm(int djzclxDm) {
		this.djzclxDm = djzclxDm;
	}
	public Date getDjrq() {
		return djrq;
	}
	public void setDjrq(Date djrq) {
		this.djrq = djrq;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public BigDecimal getZnsed() {
		return znsed;
	}
	public void setZnsed(BigDecimal znsed) {
		this.znsed = znsed;
	}
	
}
