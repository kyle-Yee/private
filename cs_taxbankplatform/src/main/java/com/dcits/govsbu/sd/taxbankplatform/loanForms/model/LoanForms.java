/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.loanForms.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @author 胡宝龙2016-8-15 上午9:57:02
 *
 */
public class LoanForms  extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String lfId;
	private String orgId;	//所属银行id
	private String regionId;	//区域id
	private String lf_remark;	//备注
	private String la_id;	//授权协议ID
	private String fp_id; //产品id
	
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
	public String getLf_remark() {
		return lf_remark;
	}
	public void setLf_remark(String lf_remark) {
		this.lf_remark = lf_remark;
	}
	public String getLa_id() {
		return la_id;
	}
	public void setLa_id(String la_id) {
		this.la_id = la_id;
	}
	public String getFp_id() {
		return fp_id;
	}
	public void setFp_id(String fp_id) {
		this.fp_id = fp_id;
	}
	public String getLfId() {
		return lfId;
	}
	public void setLfId(String lfId) {
		this.lfId = lfId;
	}
		
}
