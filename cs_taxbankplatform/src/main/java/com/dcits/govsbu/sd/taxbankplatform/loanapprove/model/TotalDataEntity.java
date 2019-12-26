package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 获取管理看板的统计数据
 * @versions:1.0 
 * @filename：TotalDataEntity.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午11:01:532:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName TotalDataEntity
 */
public class TotalDataEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	//待受理
	private String dsl;
	//未受理
	private String wsl;
	//待授信
	private String dsx;
	//授信终止
	private String sxzz;
	//自动终止
	private String zdzz;
	//已授信
	private String ysx;
	//未获得授信
	private String whdsx;
	//全部
	private String total;
	//受理不通过
	private String slbtg;
	//授信完成
	private String sxwc;
	//带批准撤销
	private String dpzcx;
	//已批准撤销
	private String ypzcx;
	//已退单
	private String ytd;
	
	public String getSlbtg() {
		return slbtg;
	}
	public void setSlbtg(String slbtg) {
		this.slbtg = slbtg;
	}
	public String getDsl() {
		return dsl;
	}
	public void setDsl(String dsl) {
		this.dsl = dsl;
	}
	public String getWsl() {
		return wsl;
	}
	public void setWsl(String wsl) {
		this.wsl = wsl;
	}
	public String getDsx() {
		return dsx;
	}
	public void setDsx(String dsx) {
		this.dsx = dsx;
	}
	public String getSxzz() {
		return sxzz;
	}
	public void setSxzz(String sxzz) {
		this.sxzz = sxzz;
	}
	public String getZdzz() {
		return zdzz;
	}
	public void setZdzz(String zdzz) {
		this.zdzz = zdzz;
	}
	public String getYsx() {
		return ysx;
	}
	public void setYsx(String ysx) {
		this.ysx = ysx;
	}
	public String getWhdsx() {
		return whdsx;
	}
	public void setWhdsx(String whdsx) {
		this.whdsx = whdsx;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getSxwc() {
		return sxwc;
	}
	public void setSxwc(String sxwc) {
		this.sxwc = sxwc;
	}
	public String getDpzcx() {
		return dpzcx;
	}
	public void setDpzcx(String dpzcx) {
		this.dpzcx = dpzcx;
	}
	public String getYpzcx() {
		return ypzcx;
	}
	public void setYpzcx(String ypzcx) {
		this.ypzcx = ypzcx;
	}
	public String getYtd() {
		return ytd;
	}
	public void setYtd(String ytd) {
		this.ytd = ytd;
	}
	
}
