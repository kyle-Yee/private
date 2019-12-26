/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.loanapplyattach.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @author 胡宝龙2016-8-18 下午9:44:09
 * 贷款申请单附表
 */
public class LoanApplyAttach extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String laa_id;
	private String lf_id; //申请主表id
	private String pdi_id;	//产品数据项Id
	private String pdi_values; //产品数据项值
	private String laa_remark;
	private String orgId; //银行ID
	private String regionId;	//城市ID
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
	public String getPdi_values() {
		return pdi_values;
	}
	public void setPdi_values(String pdi_values) {
		this.pdi_values = pdi_values;
	}
	public String getLaa_remark() {
		return laa_remark;
	}
	public void setLaa_remark(String laa_remark) {
		this.laa_remark = laa_remark;
	}
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getLaa_id() {
		return laa_id;
	}
	public void setLaa_id(String laa_id) {
		this.laa_id = laa_id;
	}
	
}
