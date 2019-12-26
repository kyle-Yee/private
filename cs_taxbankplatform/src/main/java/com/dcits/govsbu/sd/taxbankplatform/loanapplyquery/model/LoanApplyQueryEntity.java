package com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model;

import java.util.Date;
import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.model.FinancialProduct;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.NsryhxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.repaymentway.model.RepaymentWayEntity;

/**
 * LoanApplyQueryEntity.java
 * @author 严添麟
 * @date 2016年8月8日
 * @caption 贷款申请查询
 */
public class LoanApplyQueryEntity extends BaseEntity{
	/***/
	private static final long serialVersionUID = 1L;
	
	private String fp_id;																									//产品id
	private String region_id;																							//区域id
	private String rw_id;																									//还款方式
	private int la_amount;																						//申请额度(万)
	private String la_serial_number;                                              						//贷款申请流水号(申请单号)
	private int la_repay_loan_deadline;															//申请还款期限(月)
	private String nsrsbh;																							//纳税人识别号
	private Date la_apply_time;																			//申请时间
	private Date la_first_time;																				//初审时间
	private Date la_end_time;																				//终审时间
	private String la_status;																							//审批状态
	private String la_remark;																					//备注
	private FinancialProduct financialProduct;     											//银行产品信息
	private LoanStatusEntity loanStatusEntity;												//申请状态表
	private RepaymentWayEntity repaymentWayEntity;							//还款方式代码表
	private LoanApplyFinalEntity loanApplyFinalEntity;							//贷款审批结果表
	private List<LoanApproveRecEntity> loanApproveRecEntity;		//贷款申请审批记录表
	private NsryhxxEntity nsryhxxEntity;														//纳税人信息
	private List<LoanApplyAttachEntity> loanApplyAttachList;			//数据项信息

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

	public String getLa_status() {
		return la_status;
	}
	public void setLa_status(String la_status) {
		this.la_status = la_status;
	}

	public String getLa_remark() {
		return la_remark;
	}
	public void setLa_remark(String la_remark) {
		this.la_remark = la_remark;
	}

	public FinancialProduct getFinancialProduct() {
		return financialProduct;
	}
	public void setFinancialProduct(FinancialProduct financialProduct) {
		this.financialProduct = financialProduct;
	}
	
	public LoanStatusEntity getLoanStatusEntity() {
		return loanStatusEntity;
	}
	public void setLoanStatusEntity(LoanStatusEntity loanStatusEntity) {
		this.loanStatusEntity = loanStatusEntity;
	}
	
	public RepaymentWayEntity getRepaymentWayEntity() {
		return repaymentWayEntity;
	}
	public void setRepaymentWayEntity(RepaymentWayEntity repaymentWayEntity) {
		this.repaymentWayEntity = repaymentWayEntity;
	}
	
	public LoanApplyFinalEntity getLoanApplyFinalEntity() {
		return loanApplyFinalEntity;
	}
	public void setLoanApplyFinalEntity(LoanApplyFinalEntity loanApplyFinalEntity) {
		this.loanApplyFinalEntity = loanApplyFinalEntity;
	}
	
	public List<LoanApproveRecEntity> getLoanApproveRecEntity() {
		return loanApproveRecEntity;
	}
	public void setLoanApproveRecEntity(
			List<LoanApproveRecEntity> loanApproveRecEntity) {
		this.loanApproveRecEntity = loanApproveRecEntity;
	}
	
	public NsryhxxEntity getNsryhxxEntity() {
		return nsryhxxEntity;
	}
	public void setNsryhxxEntity(NsryhxxEntity nsryhxxEntity) {
		this.nsryhxxEntity = nsryhxxEntity;
	}
	
	public List<LoanApplyAttachEntity> getLoanApplyAttachList() {
		return loanApplyAttachList;
	}
	public void setLoanApplyAttachList(
			List<LoanApplyAttachEntity> loanApplyAttachList) {
		this.loanApplyAttachList = loanApplyAttachList;
	}
}
