package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class MessageQuartzEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long mq_id;//短信记录id
	private String phone;//电话
	private long org_id;//组织id
	private long region_id;//区域id
	private long u_id;//用户id
	private String content;//短信内容
	private String remark;//备注
	private boolean flag;//是否发送成功
	
	public long getMq_id() {
		return mq_id;
	}
	public void setMq_id(long mq_id) {
		this.mq_id = mq_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public long getOrg_id() {
		return org_id;
	}
	public void setOrg_id(long org_id) {
		this.org_id = org_id;
	}
	public long getRegion_id() {
		return region_id;
	}
	public void setRegion_id(long region_id) {
		this.region_id = region_id;
	}
	public long getU_id() {
		return u_id;
	}
	public void setU_id(long u_id) {
		this.u_id = u_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	
}
