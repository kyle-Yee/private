package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import java.math.BigDecimal;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @description 信用卡申请记录表
 * @author 10856
 *
 */
public class LoanCardRecordEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String xr_id;//信用卡申请记录表id
	
	private String xr_cpmc;//产品名称
	
	private String xr_yhmc;//银行名称
	
	private String xr_nsrsbh;//纳税人识别号
	
	private String xr_xydm;//统一社会信用代码
	
	private String xr_lxrsj;//联系人手机号码
	
	private String xr_lxrdz;//联系地址
	
	private String xr_xxdz;//详细地址
	
	private String xr_lsh;//流水号(申请过程的唯一标识)
	
	private String xr_ysxzt_dm;//预授信状态（成功0，失败1）
	
	private String xr_sqjg;//授权结果
	
	private String xr_ysxsj;//预授信时间
	
	private String xr_ysxedmin;//授信额度最小值
	
	private String xr_ysxedmax;//授信额度最大值
	
	private String xr_ysxsm;//授信说明
	
	private String xr_yxjl_tjdm;//营销经理推荐代码
	
	private String xr_yhdm;//银行代码对于光大银行，此代码为ZGGDYH
	
	private String xr_kpzt;//卡片状态（1审批通过，2审批拒绝）
	
	private String xr_zs_sxsj;//终审授信时间
	
	private BigDecimal xr_sxed;//授信额度

	public String getXr_id() {
		return xr_id;
	}

	public void setXr_id(String xr_id) {
		this.xr_id = xr_id;
	}

	public String getXr_cpmc() {
		return xr_cpmc;
	}

	public void setXr_cpmc(String xr_cpmc) {
		this.xr_cpmc = xr_cpmc;
	}

	public String getXr_yhmc() {
		return xr_yhmc;
	}

	public void setXr_yhmc(String xr_yhmc) {
		this.xr_yhmc = xr_yhmc;
	}

	public String getXr_nsrsbh() {
		return xr_nsrsbh;
	}

	public void setXr_nsrsbh(String xr_nsrsbh) {
		this.xr_nsrsbh = xr_nsrsbh;
	}

	public String getXr_xydm() {
		return xr_xydm;
	}

	public void setXr_xydm(String xr_xydm) {
		this.xr_xydm = xr_xydm;
	}

	public String getXr_lxrsj() {
		return xr_lxrsj;
	}

	public void setXr_lxrsj(String xr_lxrsj) {
		this.xr_lxrsj = xr_lxrsj;
	}

	public String getXr_lxrdz() {
		return xr_lxrdz;
	}

	public void setXr_lxrdz(String xr_lxrdz) {
		this.xr_lxrdz = xr_lxrdz;
	}

	public String getXr_xxdz() {
		return xr_xxdz;
	}

	public void setXr_xxdz(String xr_xxdz) {
		this.xr_xxdz = xr_xxdz;
	}

	public String getXr_lsh() {
		return xr_lsh;
	}

	public void setXr_lsh(String xr_lsh) {
		this.xr_lsh = xr_lsh;
	}

	public String getXr_ysxzt_dm() {
		return xr_ysxzt_dm;
	}

	public void setXr_ysxzt_dm(String xr_ysxzt_dm) {
		this.xr_ysxzt_dm = xr_ysxzt_dm;
	}

	public String getXr_sqjg() {
		return xr_sqjg;
	}

	public void setXr_sqjg(String xr_sqjg) {
		this.xr_sqjg = xr_sqjg;
	}

	public String getXr_ysxsj() {
		return xr_ysxsj;
	}

	public void setXr_ysxsj(String xr_ysxsj) {
		this.xr_ysxsj = xr_ysxsj;
	}

	public String getXr_ysxedmin() {
		return xr_ysxedmin;
	}

	public void setXr_ysxedmin(String xr_ysxedmin) {
		this.xr_ysxedmin = xr_ysxedmin;
	}

	public String getXr_ysxedmax() {
		return xr_ysxedmax;
	}

	public void setXr_ysxedmax(String xr_ysxedmax) {
		this.xr_ysxedmax = xr_ysxedmax;
	}

	public String getXr_ysxsm() {
		return xr_ysxsm;
	}

	public void setXr_ysxsm(String xr_ysxsm) {
		this.xr_ysxsm = xr_ysxsm;
	}

	public String getXr_yxjl_tjdm() {
		return xr_yxjl_tjdm;
	}

	public void setXr_yxjl_tjdm(String xr_yxjl_tjdm) {
		this.xr_yxjl_tjdm = xr_yxjl_tjdm;
	}

	public String getXr_yhdm() {
		return xr_yhdm;
	}

	public void setXr_yhdm(String xr_yhdm) {
		this.xr_yhdm = xr_yhdm;
	}

	public String getXr_kpzt() {
		return xr_kpzt;
	}

	public void setXr_kpzt(String xr_kpzt) {
		this.xr_kpzt = xr_kpzt;
	}

	public String getXr_zs_sxsj() {
		return xr_zs_sxsj;
	}

	public void setXr_zs_sxsj(String xr_zs_sxsj) {
		this.xr_zs_sxsj = xr_zs_sxsj;
	}

	public BigDecimal getXr_sxed() {
		return xr_sxed;
	}

	public void setXr_sxed(BigDecimal xr_sxed) {
		this.xr_sxed = xr_sxed;
	}
	
	
	
}
