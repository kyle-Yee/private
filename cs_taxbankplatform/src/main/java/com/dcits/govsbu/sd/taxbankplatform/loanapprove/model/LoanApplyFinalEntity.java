package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.model.FinancialProduct;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApproveRecEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;

/**
 * 贷款审批结果表
 * @versions:1.0 
 * @filename：LoanApplyFinalEntity.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午11:15:352:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName LoanApplyFinalEntity
 */
public class LoanApplyFinalEntity extends BaseEntity{
	
	/** */
	private static final long serialVersionUID = 1L;
	
	private String region_id;					//区域id
	private String fp_id;								//产品id
	private String la_id;								//申请id
	private String lac_id;							//审批结果id
	private String lafs_id;
   
	public String getLafs_id() {
		return lafs_id;
	}
	public void setLafs_id(String lafs_id) {
		this.lafs_id = lafs_id;
	}
	private String laf_opinion;			//审批意见
	private String laf_remark;				//备注

	private TaxQyjcxx taxQyjcxx;
	private TaxQyjcxxKz taxQyjcxxKz;
	private NsryhxxEntity nsryhxxEntity;
	private  FinancialProduct financialProduct;
	private  LoanApproveRecEntity  loanApproveRecList;
	private  LoanApproveQueryEntity loanApproveQueryEntity;
	private RegionsEntity regionsEntity;
	private String loan_down_date;
	private String begin;
	private String laname;
	public String getLaname() {
		return laname;
	}
	public void setLaname(String laname) {
		this.laname = laname;
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getLoan_down_date() {
		return loan_down_date;
	}
	public void setLoan_down_date(String loan_down_date) {
		this.loan_down_date = loan_down_date;
	}
	public RegionsEntity getRegionsEntity() {
		return regionsEntity;
	}
	public void setRegionsEntity(RegionsEntity regionsEntity) {
		this.regionsEntity = regionsEntity;
	}
	public TaxQyjcxx getTaxQyjcxx() {
		return taxQyjcxx;
	}
	public void setTaxQyjcxx(TaxQyjcxx taxQyjcxx) {
		this.taxQyjcxx = taxQyjcxx;
	}
	public TaxQyjcxxKz getTaxQyjcxxKz() {
		return taxQyjcxxKz;
	}
	public void setTaxQyjcxxKz(TaxQyjcxxKz taxQyjcxxKz) {
		this.taxQyjcxxKz = taxQyjcxxKz;
	}
	public NsryhxxEntity getNsryhxxEntity() {
		return nsryhxxEntity;
	}
	public void setNsryhxxEntity(NsryhxxEntity nsryhxxEntity) {
		this.nsryhxxEntity = nsryhxxEntity;
	}


	public FinancialProduct getFinancialProduct() {
		return financialProduct;
	}
	public void setFinancialProduct(FinancialProduct financialProduct) {
		this.financialProduct = financialProduct;
	}
	public LoanApproveRecEntity getLoanApproveRecList() {
		return loanApproveRecList;
	}
	public void setLoanApproveRecList(LoanApproveRecEntity loanApproveRecList) {
		this.loanApproveRecList = loanApproveRecList;
	}
	public LoanApproveQueryEntity getLoanApproveQueryEntity() {
		return loanApproveQueryEntity;
	}
	public void setLoanApproveQueryEntity(
			LoanApproveQueryEntity loanApproveQueryEntity) {
		this.loanApproveQueryEntity = loanApproveQueryEntity;
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
	
	public String getLac_id() {
		return lac_id;
	}
	public void setLac_id(String lac_id) {
		this.lac_id = lac_id;
	}
	
	public String getLaf_opinion() {
		return laf_opinion;
	}
	public void setLaf_opinion(String laf_opinion) {
		this.laf_opinion = laf_opinion;
	}
	
	public String getLaf_remark() {
		return laf_remark;
	}
	public void setLaf_remark(String laf_remark) {
		this.laf_remark = laf_remark;
	}
}
