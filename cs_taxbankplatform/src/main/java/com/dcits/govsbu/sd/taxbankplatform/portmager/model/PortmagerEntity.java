package com.dcits.govsbu.sd.taxbankplatform.portmager.model;

import java.io.Serializable;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @versions:1.0 
 * @filename：PortmagerEntity.java
 * @Company:dfwyBank
 * @Created: 2017-8-2下午下午6:33:172:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName PortmagerEntity
 */
public class PortmagerEntity extends BaseEntity implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	private String yhmc;
	
	private String xlzt;
	
	private String yhdm;
	
	private String zxyys;
	
	private String zxbh;
	
	private String yhdkh;
	
	private String yhzxlldz;
	
	private String yhfwqipdz;
	
	private String sjdkh;
	
	private String sjzxlldz;
	
	private String sjfwqipdz;
	
	private String bz;
	
	private String cjr;
	// 3.0v modify by Sigua.Huang 2018/06/19 begin
	private String bankaccount;
	
	private String password;
	
	private String callbackurl;
	
	public String getCallbackurl() {
		return callbackurl;
	}
	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}
	
	public String getBankaccount() {
		return bankaccount;
	}
	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	// 3.0v modify by Sigua.Huang 2018/06/19 end

	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	public String getXlzt() {
		return xlzt;
	}
	public void setXlzt(String xlzt) {
		this.xlzt = xlzt;
	}
	public String getYhdm() {
		return yhdm;
	}
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	public String getZxyys() {
		return zxyys;
	}
	public void setZxyys(String zxyys) {
		this.zxyys = zxyys;
	}
	public String getZxbh() {
		return zxbh;
	}
	public void setZxbh(String zxbh) {
		this.zxbh = zxbh;
	}
	public String getYhdkh() {
		return yhdkh;
	}
	public void setYhdkh(String yhdkh) {
		this.yhdkh = yhdkh;
	}
	public String getYhzxlldz() {
		return yhzxlldz;
	}
	public void setYhzxlldz(String yhzxlldz) {
		this.yhzxlldz = yhzxlldz;
	}
	public String getYhfwqipdz() {
		return yhfwqipdz;
	}
	public void setYhfwqipdz(String yhfwqipdz) {
		this.yhfwqipdz = yhfwqipdz;
	}
	public String getSjdkh() {
		return sjdkh;
	}
	public void setSjdkh(String sjdkh) {
		this.sjdkh = sjdkh;
	}
	public String getSjzxlldz() {
		return sjzxlldz;
	}
	public void setSjzxlldz(String sjzxlldz) {
		this.sjzxlldz = sjzxlldz;
	}
	public String getSjfwqipdz() {
		return sjfwqipdz;
	}
	public void setSjfwqipdz(String sjfwqipdz) {
		this.sjfwqipdz = sjfwqipdz;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	
}
