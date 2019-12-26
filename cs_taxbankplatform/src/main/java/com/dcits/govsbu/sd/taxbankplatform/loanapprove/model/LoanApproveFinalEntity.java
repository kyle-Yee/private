package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * LoanApproveRecEntity.java
 * @author 严添麟
 * @date 2016年8月8日
 * @caption 贷款申请记录
 */
public class LoanApproveFinalEntity extends BaseEntity{

	/***/
	private static final long serialVersionUID = 1L;
	private String lar_id;
	private String region_id;												//区域id
	private String fp_id;															//产品id
	private String la_id;															//贷款申请id
	private String las_id;														//申请状态id
	private String lac_id;														//审批意见代码id
	private String rw_id;															//还款方式
	private int lar_credit_quota;									//授信额度(万)
	private int lar_loan_deadline;								//贷款期限(月)
	private String lar_begin;											//贷款期限起
	private String lar_end;												//贷款期限止
	private String lar_rate;													//贷款利率
	private int lar_isoverlay_area;								//是否在产品覆盖区域
	private String lar_bank_name;								//借款方开户银行
	private String lar_bank_account;							//借款方银行账号
	private String lar_contract;										//贷款合同号
	private String lar_opinion;										//审批意见
	private String lar_remark;										//备注
	private int tempstatus;												//临时状态用来存放申请记录下一步审批状态
	private String las_status;
	private int batchCheckType;									//批量审批时的状态（1，2）（如果是未受理则在授信记录前加一条受理记录）
	private String lafs_id;
	private String laf_id;
	
	
	public String getLar_id() {
		return lar_id;
	}
	public void setLar_id(String lar_id) {
		this.lar_id = lar_id;
	}
	public String getLafs_id() {
		return lafs_id;
	}
	public void setLafs_id(String lafs_id) {
		this.lafs_id = lafs_id;
	}
	public int getBatchCheckType() {
		return batchCheckType;
	}
	public void setBatchCheckType(int batchCheckType) {
		this.batchCheckType = batchCheckType;
	}
	public String getLas_status() {
		return las_status;
	}
	public void setLas_status(String las_status) {
		this.las_status = las_status;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	
	public String getFp_id() {
		return fp_id;
	}
	public void setFp_id(String fp_id) {
		this.fp_id = fp_id;
	}
	
	public String getLa_id() {
		return la_id;
	}
	public void setLa_id(String la_id) {
		this.la_id = la_id;
	}
	
	public String getLas_id() {
		return las_id;
	}
	public void setLas_id(String las_id) {
		this.las_id = las_id;
	}
	
	public String getLac_id() {
		return lac_id;
	}
	public void setLac_id(String lac_id) {
		this.lac_id = lac_id;
	}
	
	public String getRw_id() {
		return rw_id;
	}
	public void setRw_id(String rw_id) {
		this.rw_id = rw_id;
	}
	
	public int getLar_credit_quota() {
		return lar_credit_quota;
	}
	public void setLar_credit_quota(int lar_credit_quota) {
		this.lar_credit_quota = lar_credit_quota;
	}
	
	public int getLar_loan_deadline() {
		return lar_loan_deadline;
	}
	public void setLar_loan_deadline(int lar_loan_deadline) {
		this.lar_loan_deadline = lar_loan_deadline;
	}
	
	public String getLar_begin() {
		return lar_begin;
	}
	public void setLar_begin(String lar_begin) {
		this.lar_begin = lar_begin;
	}
	
	public String getLar_end() {
		return lar_end;
	}
	public void setLar_end(String lar_end) {
		this.lar_end = lar_end;
	}
	
	public String getLar_rate() {
		return lar_rate;
	}
	public void setLar_rate(String lar_rate) {
		this.lar_rate = lar_rate;
	}
	
	public int getLar_isoverlay_area() {
		return lar_isoverlay_area;
	}
	public void setLar_isoverlay_area(int lar_isoverlay_area) {
		this.lar_isoverlay_area = lar_isoverlay_area;
	}
	
	public String getLar_bank_name() {
		return lar_bank_name;
	}
	public void setLar_bank_name(String lar_bank_name) {
		this.lar_bank_name = lar_bank_name;
	}
	
	public String getLar_bank_account() {
		return lar_bank_account;
	}
	public void setLar_bank_account(String lar_bank_account) {
		this.lar_bank_account = lar_bank_account;
	}
	
	public String getLar_contract() {
		return lar_contract;
	}
	public void setLar_contract(String lar_contract) {
		this.lar_contract = lar_contract;
	}
	
	public String getLar_opinion() {
		return lar_opinion;
	}
	public void setLar_opinion(String lar_opinion) {
		this.lar_opinion = lar_opinion;
	}
	
	public String getLar_remark() {
		return lar_remark;
	}
	public void setLar_remark(String lar_remark) {
		this.lar_remark = lar_remark;
	}
	
	public int getTempstatus() {
		return tempstatus;
	}
	public void setTempstatus(int tempstatus) {
		this.tempstatus = tempstatus;
	}
	public String getLaf_id() {
		return laf_id;
	}
	public void setLaf_id(String laf_id) {
		this.laf_id = laf_id;
	}
}
