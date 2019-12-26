package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author 
 *  银税互动资料更新
 *  （未使用 2017年5月12日16:07:20）
 */
public class DataRefreshEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String dr_id;//资料更新id
	private String region_id;//区域id
	private String org_id;//组织id
	private String data_month;//月份
	private String bad_num;//不良贷款笔数
	private String bad_sum;//不良贷款金额
	private String bad_rate;//不良率
	private String yearly_plan_sum;//年计划授信总金额 
	private String yearly_plan_micro_sum;//年计划授信小微企业总金额

	private boolean hasUpdate;//本月是否由更新
	
	public boolean isHasUpdate() {
		return hasUpdate;
	}
	public void setHasUpdate(boolean hasUpdate) {
		this.hasUpdate = hasUpdate;
	}
	public String getDr_id() {
		return dr_id;
	}
	public void setDr_id(String dr_id) {
		this.dr_id = dr_id;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getData_month() {
		return data_month;
	}
	public void setData_month(String data_month) {
		this.data_month = data_month;
	}
	public String getBad_num() {
		return bad_num;
	}
	public void setBad_num(String bad_num) {
		this.bad_num = bad_num;
	}
	public String getBad_sum() {
		return bad_sum;
	}
	public void setBad_sum(String bad_sum) {
		this.bad_sum = bad_sum;
	}
	public String getBad_rate() {
		return bad_rate;
	}
	public void setBad_rate(String bad_rate) {
		this.bad_rate = bad_rate;
	}
	public String getYearly_plan_sum() {
		return yearly_plan_sum;
	}
	public void setYearly_plan_sum(String yearly_plan_sum) {
		this.yearly_plan_sum = yearly_plan_sum;
	}
	public String getYearly_plan_micro_sum() {
		return yearly_plan_micro_sum;
	}
	public void setYearly_plan_micro_sum(String yearly_plan_micro_sum) {
		this.yearly_plan_micro_sum = yearly_plan_micro_sum;
	}
	
	
	
	
}
