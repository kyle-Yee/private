package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @description 信用卡产品信贷审批列表实体
 * @author 10856
 *
 */
public class LoanCardQueryEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Date la_apply_time;//申请时间
	
	private String nsryhxx_qymc;//企业名称
	
	private String fp_name;//金融产品名称
	
	private int la_status;//审批状态
	
	private String la_id;//申请id

	public Date getLa_apply_time() {
		return la_apply_time;
	}

	public void setLa_apply_time(Date la_apply_time) {
		this.la_apply_time = la_apply_time;
	}

	public String getNsryhxx_qymc() {
		return nsryhxx_qymc;
	}

	public void setNsryhxx_qymc(String nsryhxx_qymc) {
		this.nsryhxx_qymc = nsryhxx_qymc;
	}

	public String getFp_name() {
		return fp_name;
	}

	public void setFp_name(String fp_name) {
		this.fp_name = fp_name;
	}

	public int getLa_status() {
		return la_status;
	}

	public void setLa_status(int la_status) {
		this.la_status = la_status;
	}

	public String getLa_id() {
		return la_id;
	}

	public void setLa_id(String la_id) {
		this.la_id = la_id;
	}
	
	
}
