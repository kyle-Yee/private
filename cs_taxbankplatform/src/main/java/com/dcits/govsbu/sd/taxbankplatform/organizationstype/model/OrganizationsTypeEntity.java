package com.dcits.govsbu.sd.taxbankplatform.organizationstype.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * OrganizationsTypeEntity.java
 * @author 严添麟
 * @date 2016年8月19日
 * @caption 组织类型代码查询
 */
public class OrganizationsTypeEntity extends BaseEntity{
	
	/** */
	private static final long serialVersionUID = 1L;
	
	private String ot_type_code;		//组织类型代码
	private String ot_name;					//组织类型名称
	private String enabled;					//是否有效标识
	private String ot_remark;				//备注
	
	public String getOt_type_code() {
		return ot_type_code;
	}
	public void setOt_type_code(String ot_type_code) {
		this.ot_type_code = ot_type_code;
	}
	
	public String getOt_name() {
		return ot_name;
	}
	public void setOt_name(String ot_name) {
		this.ot_name = ot_name;
	}
	
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	public String getOt_remark() {
		return ot_remark;
	}
	public void setOt_remark(String ot_remark) {
		this.ot_remark = ot_remark;
	}
}
