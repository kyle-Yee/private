package com.dcits.govsbu.sd.taxbankplatform.loanformsattach.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @author 胡宝龙
 * 申请单预览附表
 */
public class LoanFormsAttach extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String lf_id; //申请主表id
	private String pdi_id;	//产品数据项Id
	private String pdi_code;	//产品数据项code
	private String pdi_name;	//产品数据项名
	private String pdi_values; //产品数据项值
	private String ht_type; //产品数据项值
	private String enabled;		//有效标志(Y/N)
	
	
	public String getLf_id() {
		return lf_id;
	}
	public void setLf_id(String lf_id) {
		this.lf_id = lf_id;
	}
	public String getPdi_id() {
		return pdi_id;
	}
	public void setPdi_id(String pdi_id) {
		this.pdi_id = pdi_id;
	}
	public String getPdi_code() {
		return pdi_code;
	}
	public void setPdi_code(String pdi_code) {
		this.pdi_code = pdi_code;
	}
	public String getPdi_name() {
		return pdi_name;
	}
	public void setPdi_name(String pdi_name) {
		this.pdi_name = pdi_name;
	}
	public String getPdi_values() {
		return pdi_values;
	}
	public void setPdi_values(String pdi_values) {
		this.pdi_values = pdi_values;
	}
	public String getHt_type() {
		return ht_type;
	}
	public void setHt_type(String ht_type) {
		this.ht_type = ht_type;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}
