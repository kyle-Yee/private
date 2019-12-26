package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localmodel;

import java.io.Serializable;

/**
 * 功能:
 * 企业利润表（企业会计制度）信息
 * @author 16420
 */
public class Qylrb implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String BNLJS;
	private String BYS;
	private String EWBHXH;
	private String HMC;
	private String LRR_DM;
	private String LRRQ;
	private String SJGSDQ;
	private String SJTB_SJ;
	private String UUID;
	private String XGR_DM;
	private String XGRQ;
	private String ZLBSCJUUID;
	
	
	public String getBNLJS() {
		return BNLJS;
	}
	public void setBNLJS(String bNLJS) {
		BNLJS = bNLJS;
	}
	
	public String getBYS() {
		return BYS;
	}
	public void setBYS(String bYS) {
		BYS = bYS;
	}
	
	public String getEWBHXH() {
		return EWBHXH;
	}
	public void setEWBHXH(String eWBHXH) {
		EWBHXH = eWBHXH;
	}
	
	public String getHMC() {
		return HMC;
	}
	public void setHMC(String hMC) {
		HMC = hMC;
	}
	
	public String getLRR_DM() {
		return LRR_DM;
	}
	public void setLRR_DM(String lRR_DM) {
		LRR_DM = lRR_DM;
	}
	
	public String getLRRQ() {
		return LRRQ;
	}
	public void setLRRQ(String lRRQ) {
		LRRQ = lRRQ;
	}
	
	public String getSJGSDQ() {
		return SJGSDQ;
	}
	public void setSJGSDQ(String sJGSDQ) {
		SJGSDQ = sJGSDQ;
	}
	
	public String getSJTB_SJ() {
		return SJTB_SJ;
	}
	public void setSJTB_SJ(String sJTB_SJ) {
		SJTB_SJ = sJTB_SJ;
	}
	
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	
	public String getXGR_DM() {
		return XGR_DM;
	}
	public void setXGR_DM(String xGR_DM) {
		XGR_DM = xGR_DM;
	}
	
	public String getXGRQ() {
		return XGRQ;
	}
	public void setXGRQ(String xGRQ) {
		XGRQ = xGRQ;
	}
	
	public String getZLBSCJUUID() {
		return ZLBSCJUUID;
	}
	public void setZLBSCJUUID(String zLBSCJUUID) {
		ZLBSCJUUID = zLBSCJUUID;
	}
}