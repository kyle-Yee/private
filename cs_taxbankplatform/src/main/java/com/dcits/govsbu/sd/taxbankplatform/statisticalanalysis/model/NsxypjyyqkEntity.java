package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author yaofang
 *  银税实际发放贷款情况
 */
public class NsxypjyyqkEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String swjgtsyhA = "0";//税务机关推送户数
	private String swjgtsyhB = "0";//税务机关推送户数
	private String swjgtsyhC = "0";//税务机关推送户数
	private String swjgtsyhD = "0";//税务机关推送户数
	private String swjgtsyhW = "0";//税务机关推送户数  未评级
	private String swjgtsyhH = "0";//税务机关推送户数  合计
	 
	private String qzsxhsA = "0";//其中：授信户数
	private String qzsxhsB = "0";//其中：授信户数
	private String qzsxhsC = "0";//其中：授信户数
	private String qzsxhsD = "0";//其中：授信户数
	private String qzsxhsW = "0";//其中：授信户数
	private String qzsxhsH = "0";//其中：授信户数
 
	
	private String qztzxhsA = "0";//其中：拓展新户数
	private String qztzxhsB = "0";//其中：拓展新户数
	private String qztzxhsC = "0";//其中：拓展新户数
	private String qztzxhsD = "0";//其中：拓展新户数
	private String qztzxhsW = "0";//其中：拓展新户数
	private String qztzxhsH = "0";//其中：拓展新户数
	 
	
	private String nsxypjjgyyl;//纳税信用评价结果运用率（%）
	private String qyhtzl;//新客户拓展率（%）
	private String jjjyrs = "--";//解决就业人数
	public String getSwjgtsyhA() {
		return swjgtsyhA;
	}
	public void setSwjgtsyhA(String swjgtsyhA) {
		this.swjgtsyhA = swjgtsyhA;
	}
	public String getSwjgtsyhB() {
		return swjgtsyhB;
	}
	public void setSwjgtsyhB(String swjgtsyhB) {
		this.swjgtsyhB = swjgtsyhB;
	}
	public String getSwjgtsyhC() {
		return swjgtsyhC;
	}
	public void setSwjgtsyhC(String swjgtsyhC) {
		this.swjgtsyhC = swjgtsyhC;
	}
	public String getSwjgtsyhD() {
		return swjgtsyhD;
	}
	public void setSwjgtsyhD(String swjgtsyhD) {
		this.swjgtsyhD = swjgtsyhD;
	}
	public String getSwjgtsyhW() {
		return swjgtsyhW;
	}
	public void setSwjgtsyhW(String swjgtsyhW) {
		this.swjgtsyhW = swjgtsyhW;
	}
	public String getSwjgtsyhH() {
		return swjgtsyhH;
	}
	public void setSwjgtsyhH(String swjgtsyhH) {
		this.swjgtsyhH = swjgtsyhH;
	}
	public String getQzsxhsA() {
		return qzsxhsA;
	}
	public void setQzsxhsA(String qzsxhsA) {
		this.qzsxhsA = qzsxhsA;
	}
	public String getQzsxhsB() {
		return qzsxhsB;
	}
	public void setQzsxhsB(String qzsxhsB) {
		this.qzsxhsB = qzsxhsB;
	}
	public String getQzsxhsC() {
		return qzsxhsC;
	}
	public void setQzsxhsC(String qzsxhsC) {
		this.qzsxhsC = qzsxhsC;
	}
	public String getQzsxhsD() {
		return qzsxhsD;
	}
	public void setQzsxhsD(String qzsxhsD) {
		this.qzsxhsD = qzsxhsD;
	}
	public String getQzsxhsW() {
		return qzsxhsW;
	}
	public void setQzsxhsW(String qzsxhsW) {
		this.qzsxhsW = qzsxhsW;
	}
	public String getQzsxhsH() {
		return qzsxhsH;
	}
	public void setQzsxhsH(String qzsxhsH) {
		this.qzsxhsH = qzsxhsH;
	}
	public String getQztzxhsA() {
		return qztzxhsA;
	}
	public void setQztzxhsA(String qztzxhsA) {
		this.qztzxhsA = qztzxhsA;
	}
	public String getQztzxhsB() {
		return qztzxhsB;
	}
	public void setQztzxhsB(String qztzxhsB) {
		this.qztzxhsB = qztzxhsB;
	}
	public String getQztzxhsC() {
		return qztzxhsC;
	}
	public void setQztzxhsC(String qztzxhsC) {
		this.qztzxhsC = qztzxhsC;
	}
	public String getQztzxhsD() {
		return qztzxhsD;
	}
	public void setQztzxhsD(String qztzxhsD) {
		this.qztzxhsD = qztzxhsD;
	}
	public String getQztzxhsW() {
		return qztzxhsW;
	}
	public void setQztzxhsW(String qztzxhsW) {
		this.qztzxhsW = qztzxhsW;
	}
	public String getQztzxhsH() {
		return qztzxhsH;
	}
	public void setQztzxhsH(String qztzxhsH) {
		this.qztzxhsH = qztzxhsH;
	}
	public String getNsxypjjgyyl() {
		return nsxypjjgyyl;
	}
	public void setNsxypjjgyyl(String nsxypjjgyyl) {
		this.nsxypjjgyyl = nsxypjjgyyl;
	}
	public String getQyhtzl() {
		return qyhtzl;
	}
	public void setQyhtzl(String qyhtzl) {
		this.qyhtzl = qyhtzl;
	}
	public String getJjjyrs() {
		return jjjyrs;
	}
	public void setJjjyrs(String jjjyrs) {
		this.jjjyrs = jjjyrs;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	 
}
