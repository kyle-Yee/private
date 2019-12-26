package com.east.microsilver.common.request;

/**
 * 功能:
 * 请求参数
 * @author Administrator
 */
public class Parameter {
	
	private String nsrdzdah;				//登记序号
	private String ssqq;							//所属时期起
	private String ssqz;							//所属时期止
	private String lpbm;							//授权编码
	
	public String getNsrdzdah() {
		return nsrdzdah;
	}
	public void setNsrdzdah(String nsrdzdah) {
		this.nsrdzdah = nsrdzdah;
	}
	
	public String getSsqq() {
		return ssqq;
	}
	public void setSsqq(String ssqq) {
		this.ssqq = ssqq;
	}
	
	public String getSsqz() {
		return ssqz;
	}
	public void setSsqz(String ssqz) {
		this.ssqz = ssqz;
	}
	
	public String getLpbm() {
		return lpbm;
	}
	public void setLpbm(String lpbm) {
		this.lpbm = lpbm;
	}
}