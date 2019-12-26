package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import java.util.Date;
import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanformsattach.model.LoanFormsAttach;
/**
 * 
 * @author Administrator
 *
 */
public class TaxPdfDksqEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fp_id;																									//产品id
	private String region_id;																							//区域id
	private String rw_id;																									//还款方式
	private String rw_name; //还款方式名称
	private int la_amount;																						//申请额度(万)
	private String la_serial_number;                                              						//贷款申请流水号(申请单号)
	private int la_repay_loan_deadline;															//申请还款期限(月)
	private String nsrsbh;																							//纳税人识别号
	private Date la_apply_time;																			//申请时间
	private Date la_first_time;																				//初审时间
	private Date la_end_time;																				//终审时间
	private int la_status;																							//审批状态
	private String la_remark;
	private String creatorid;
	private Date createtime;
	private String updatorid;
	private Date updatetime;
	private String zcsjh;
	private int cyrs;
	private List<LoanFormsAttach> loanFormsAttachList;
	public String getFp_id() {
		return fp_id;
	}
	public void setFp_id(String fp_id) {
		this.fp_id = fp_id;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public String getRw_id() {
		return rw_id;
	}
	public void setRw_id(String rw_id) {
		this.rw_id = rw_id;
	}
	public String getRw_name() {
		return rw_name;
	}
	public void setRw_name(String rw_name) {
		this.rw_name = rw_name;
	}
	public int getLa_amount() {
		return la_amount;
	}
	public void setLa_amount(int la_amount) {
		this.la_amount = la_amount;
	}
	public String getLa_serial_number() {
		return la_serial_number;
	}
	public void setLa_serial_number(String la_serial_number) {
		this.la_serial_number = la_serial_number;
	}
	public int getLa_repay_loan_deadline() {
		return la_repay_loan_deadline;
	}
	public void setLa_repay_loan_deadline(int la_repay_loan_deadline) {
		this.la_repay_loan_deadline = la_repay_loan_deadline;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public Date getLa_apply_time() {
		return la_apply_time;
	}
	public void setLa_apply_time(Date la_apply_time) {
		this.la_apply_time = la_apply_time;
	}
	public Date getLa_first_time() {
		return la_first_time;
	}
	public void setLa_first_time(Date la_first_time) {
		this.la_first_time = la_first_time;
	}
	public Date getLa_end_time() {
		return la_end_time;
	}
	public void setLa_end_time(Date la_end_time) {
		this.la_end_time = la_end_time;
	}
	public int getLa_status() {
		return la_status;
	}
	public void setLa_status(int la_status) {
		this.la_status = la_status;
	}
	public String getLa_remark() {
		return la_remark;
	}
	public void setLa_remark(String la_remark) {
		this.la_remark = la_remark;
	}
	@Override
	public String getCreatorid() {
		return creatorid;
	}
	@Override
	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}
	@Override
	public Date getCreatetime() {
		return createtime;
	}
	@Override
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@Override
	public String getUpdatorid() {
		return updatorid;
	}
	@Override
	public void setUpdatorid(String updatorid) {
		this.updatorid = updatorid;
	}
	@Override
	public Date getUpdatetime() {
		return updatetime;
	}
	@Override
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getZcsjh() {
		return zcsjh;
	}
	public void setZcsjh(String zcsjh) {
		this.zcsjh = zcsjh;
	}
	public int getCyrs() {
		return cyrs;
	}
	public void setCyrs(int cyrs) {
		this.cyrs = cyrs;
	}
	public List<LoanFormsAttach> getLoanFormsAttachList() {
		return loanFormsAttachList;
	}
	public void setLoanFormsAttachList(List<LoanFormsAttach> loanFormsAttachList) {
		this.loanFormsAttachList = loanFormsAttachList;
	}
	
}
