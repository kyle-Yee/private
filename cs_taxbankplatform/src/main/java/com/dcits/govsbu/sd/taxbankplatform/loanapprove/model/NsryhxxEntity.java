package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @versions:1.0 
 * @filename：NsryhxxEntity.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午11:02:212:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName NsryhxxEntity
 */
public class NsryhxxEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String nsrsbh;						//纳税人识别号
	private String djxh;						//登记序号
	private String lpbm;						//授权编码
	private String yxqq;						//有效期起
	private String yxqz;						//有效期至
	private String frsjh;							//法人手机号
	private String nsrmc;						//纳税人名称
	private String nsrdh;						//纳税人电话
	private String nsrdz;						//纳税人地址
	private String frmc;							//法人名称
	private String xydj;							//信用等级
	private String zjmc;							//证件名称
	private String zjhm;							//证件号码
	private String qymc;                            //企业名称
	
	private String zczb;                          //注册资本     
	private String zcdz;                          //注册地址
	private String hymc;                          //行业类型
	private String djrq;                          //成了时间
	private String jyfw;                          //经营范围
	private String zcsjh;                        //注册人电话
	private String flag;
	private List<SxsjxEntity> sxsjxMapList;       //授信页面所需要的数据项
	
	public String getDjxh() {
		return djxh;
	}
	public void setDjxh(String djxh) {
		this.djxh = djxh;
	}
	public String getLpbm() {
		return lpbm;
	}
	public void setLpbm(String lpbm) {
		this.lpbm = lpbm;
	}
	public String getYxqq() {
		return yxqq;
	}
	public void setYxqq(String yxqq) {
		this.yxqq = yxqq;
	}
	public String getYxqz() {
		return yxqz;
	}
	public void setYxqz(String yxqz) {
		this.yxqz = yxqz;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	
	public String getFrsjh() {
		return frsjh;
	}
	public void setFrsjh(String frsjh) {
		this.frsjh = frsjh;
	}
	
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	
	public String getNsrdh() {
		return nsrdh;
	}
	public void setNsrdh(String nsrdh) {
		this.nsrdh = nsrdh;
	}
	public String getNsrdz() {
		return nsrdz;
	}
	
	public void setNsrdz(String nsrdz) {
		this.nsrdz = nsrdz;
	}
	/*public String getNsrcz() {
		return nsrcz;
	}
	public void setNsrcz(String nsrcz) {
		this.nsrcz = nsrcz;
	}*/
	
	public String getFrmc() {
		return frmc;
	}
	public void setFrmc(String frmc) {
		this.frmc = frmc;
	}
	
	public String getXydj() {
		return xydj;
	}
	public void setXydj(String xydj) {
		this.xydj = xydj;
	}
	
	public String getZjmc() {
		return zjmc;
	}
	public void setZjmc(String zjmc) {
		this.zjmc = zjmc;
	}
	
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getQymc() {
		return qymc;
	}
	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	public String getZczb() {
		return zczb;
	}
	public void setZczb(String zczb) {
		this.zczb = zczb;
	}
	public String getZcdz() {
		return zcdz;
	}
	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}

	public String getHymc() {
		return hymc;
	}
	public void setHymc(String hymc) {
		this.hymc = hymc;
	}
	public String getJyfw() {
		return jyfw;
	}
	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
	}
	public String getZcsjh() {
		return zcsjh;
	}
	public void setZcsjh(String zcsjh) {
		this.zcsjh = zcsjh;
	}
	public String getDjrq() {
		return djrq;
	}
	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public List<SxsjxEntity> getSxsjxMapList() {
		return sxsjxMapList;
	}
	public void setSxsjxMapList(List<SxsjxEntity> sxsjxMapList) {
		this.sxsjxMapList = sxsjxMapList;
	}
	
}
