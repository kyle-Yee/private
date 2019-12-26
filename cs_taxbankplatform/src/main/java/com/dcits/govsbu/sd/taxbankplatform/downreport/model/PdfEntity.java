package com.dcits.govsbu.sd.taxbankplatform.downreport.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;


public class PdfEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//是否要清除相关的数据表
	private static boolean open;
	//企业稽查案件信息
	private static boolean ajxx;
	//编码有效期查询
	private static boolean bmyxcx; 
	//企业成功贷款明细反馈信息
	private static boolean cgdkmxfkxx; 
	//纳税企业发票开具信息
	private static boolean fpKjxx;
	//纳税企业发票认证信息
	private static boolean fpRzxx; 
	//企业利润表（企业会计制度）信息
	private static boolean lrbQykjzd;
	//利润表（小企业会计制度）_年报信息
	private static boolean lrbXqykj;
	//企业利润表（小企业会计制度）
	private static boolean lrbXqykjzd;
	//利润表（一般企业会计制度）信息
	private static boolean lrbYbqykj; 
	//纳税人信用等级
	private static boolean nsrxydj;
	//纳税人资格认定信息
	private static boolean nsrzgxxjgb;
	//企业欠税信息
	private static boolean qsclxx;
	//获取企业变更信息
	private static boolean qybgxx;
	//获取企业分支机构
	private static boolean qyfzjg;
	//获取企业基础信息
	private static boolean qyjcxx;
	//获取企业基础信息扩展
	private static boolean qyjcxxKz;
	//获取企业批准机构
	private static boolean qypzjg;
	//获取企业投资方
	private static boolean qytzf; 
	//企业资产负债表(小企业会计制度)
	private static boolean qyzcfzbXqykjzd;
	//获取企业总分机构信息
	private static boolean qyzfjgxx;
	//企业申报信息
	private static boolean sbxx;
	//企业违法违章
	private static boolean sswfxwdj;
	//小规模纳税人申报明细主表
	private static boolean xgmnsrsbmx;
	//一般纳税人申报明细主表
	private static boolean ybnsrsbmxzb;
	//企业征收信息
	private static boolean yjsf;
	//企业预授信明细反馈信息
	private static boolean ysxmxfkxx;
	//企业资产负债表（企业会计制度)信息
	private static boolean zcfzbQykjzd;
	//资产负债表（一般企业会计)信息
	private static boolean zcfzbYbqykj; 
	//纳税企业财报申报主表信息
	private static boolean zlbscjb;
	public static boolean isOpen() {
		return open;
	}
	public static void setOpen(boolean open) {
		PdfEntity.open = open;
	}
	public static boolean isAjxx() {
		return ajxx;
	}
	public static void setAjxx(boolean ajxx) {
		PdfEntity.ajxx = ajxx;
	}
	public static boolean isBmyxcx() {
		return bmyxcx;
	}
	public static void setBmyxcx(boolean bmyxcx) {
		PdfEntity.bmyxcx = bmyxcx;
	}
	public static boolean isCgdkmxfkxx() {
		return cgdkmxfkxx;
	}
	public static void setCgdkmxfkxx(boolean cgdkmxfkxx) {
		PdfEntity.cgdkmxfkxx = cgdkmxfkxx;
	}
	public static boolean isFpKjxx() {
		return fpKjxx;
	}
	public static void setFpKjxx(boolean fpKjxx) {
		PdfEntity.fpKjxx = fpKjxx;
	}
	public static boolean isFpRzxx() {
		return fpRzxx;
	}
	public static void setFpRzxx(boolean fpRzxx) {
		PdfEntity.fpRzxx = fpRzxx;
	}
	public static boolean isLrbQykjzd() {
		return lrbQykjzd;
	}
	public static void setLrbQykjzd(boolean lrbQykjzd) {
		PdfEntity.lrbQykjzd = lrbQykjzd;
	}
	public static boolean isLrbXqykj() {
		return lrbXqykj;
	}
	public static void setLrbXqykj(boolean lrbXqykj) {
		PdfEntity.lrbXqykj = lrbXqykj;
	}
	public static boolean isLrbXqykjzd() {
		return lrbXqykjzd;
	}
	public static void setLrbXqykjzd(boolean lrbXqykjzd) {
		PdfEntity.lrbXqykjzd = lrbXqykjzd;
	}
	public static boolean isLrbYbqykj() {
		return lrbYbqykj;
	}
	public static void setLrbYbqykj(boolean lrbYbqykj) {
		PdfEntity.lrbYbqykj = lrbYbqykj;
	}
	public static boolean isNsrxydj() {
		return nsrxydj;
	}
	public static void setNsrxydj(boolean nsrxydj) {
		PdfEntity.nsrxydj = nsrxydj;
	}
	public static boolean isNsrzgxxjgb() {
		return nsrzgxxjgb;
	}
	public static void setNsrzgxxjgb(boolean nsrzgxxjgb) {
		PdfEntity.nsrzgxxjgb = nsrzgxxjgb;
	}
	public static boolean isQsclxx() {
		return qsclxx;
	}
	public static void setQsclxx(boolean qsclxx) {
		PdfEntity.qsclxx = qsclxx;
	}
	public static boolean isQybgxx() {
		return qybgxx;
	}
	public static void setQybgxx(boolean qybgxx) {
		PdfEntity.qybgxx = qybgxx;
	}
	public static boolean isQyfzjg() {
		return qyfzjg;
	}
	public static void setQyfzjg(boolean qyfzjg) {
		PdfEntity.qyfzjg = qyfzjg;
	}
	public static boolean isQyjcxx() {
		return qyjcxx;
	}
	public static void setQyjcxx(boolean qyjcxx) {
		PdfEntity.qyjcxx = qyjcxx;
	}
	public static boolean isQyjcxxKz() {
		return qyjcxxKz;
	}
	public static void setQyjcxxKz(boolean qyjcxxKz) {
		PdfEntity.qyjcxxKz = qyjcxxKz;
	}
	public static boolean isQypzjg() {
		return qypzjg;
	}
	public static void setQypzjg(boolean qypzjg) {
		PdfEntity.qypzjg = qypzjg;
	}
	public static boolean isQytzf() {
		return qytzf;
	}
	public static void setQytzf(boolean qytzf) {
		PdfEntity.qytzf = qytzf;
	}
	public static boolean isQyzcfzbXqykjzd() {
		return qyzcfzbXqykjzd;
	}
	public static void setQyzcfzbXqykjzd(boolean qyzcfzbXqykjzd) {
		PdfEntity.qyzcfzbXqykjzd = qyzcfzbXqykjzd;
	}
	public static boolean isQyzfjgxx() {
		return qyzfjgxx;
	}
	public static void setQyzfjgxx(boolean qyzfjgxx) {
		PdfEntity.qyzfjgxx = qyzfjgxx;
	}
	public static boolean isSbxx() {
		return sbxx;
	}
	public static void setSbxx(boolean sbxx) {
		PdfEntity.sbxx = sbxx;
	}
	public static boolean isSswfxwdj() {
		return sswfxwdj;
	}
	public static void setSswfxwdj(boolean sswfxwdj) {
		PdfEntity.sswfxwdj = sswfxwdj;
	}
	public static boolean isXgmnsrsbmx() {
		return xgmnsrsbmx;
	}
	public static void setXgmnsrsbmx(boolean xgmnsrsbmx) {
		PdfEntity.xgmnsrsbmx = xgmnsrsbmx;
	}
	public static boolean isYbnsrsbmxzb() {
		return ybnsrsbmxzb;
	}
	public static void setYbnsrsbmxzb(boolean ybnsrsbmxzb) {
		PdfEntity.ybnsrsbmxzb = ybnsrsbmxzb;
	}
	public static boolean isYjsf() {
		return yjsf;
	}
	public static void setYjsf(boolean yjsf) {
		PdfEntity.yjsf = yjsf;
	}
	public static boolean isYsxmxfkxx() {
		return ysxmxfkxx;
	}
	public static void setYsxmxfkxx(boolean ysxmxfkxx) {
		PdfEntity.ysxmxfkxx = ysxmxfkxx;
	}
	public static boolean isZcfzbQykjzd() {
		return zcfzbQykjzd;
	}
	public static void setZcfzbQykjzd(boolean zcfzbQykjzd) {
		PdfEntity.zcfzbQykjzd = zcfzbQykjzd;
	}
	public static boolean isZcfzbYbqykj() {
		return zcfzbYbqykj;
	}
	public static void setZcfzbYbqykj(boolean zcfzbYbqykj) {
		PdfEntity.zcfzbYbqykj = zcfzbYbqykj;
	}
	public static boolean isZlbscjb() {
		return zlbscjb;
	}
	public static void setZlbscjb(boolean zlbscjb) {
		PdfEntity.zlbscjb = zlbscjb;
	}
	
}
