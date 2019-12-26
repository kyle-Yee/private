package com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * LoanApplyFinalEntity.java
 * @author 严添麟
 * @date 2016年8月25日
 * @caption 贷款审批结果表
 */
public class LoanApplyFinalEntity extends BaseEntity{
	
	/** */
	private static final long serialVersionUID = 1L;
	
	private String region_id;					//区域id
	private String fp_id;								//产品id
	private String la_id;								//申请id
	private String lac_id;							//审批结果id
	private String laf_opinion;			//审批意见
	private String laf_remark;				//备注
	
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
