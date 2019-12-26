package com.dcits.govsbu.sd.taxbankplatform.bankmanager.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * 银行产品数据项设置记录实体
 * @author Sigua.Huang
 * @date 2018年6月24日
 */
public class DataUpdateRecord extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String fpId;
	private String bankid;
	private String bankcode;
	private String dataItems;
	private String createId;
	private String starttime;
	private String endtime;
	private String opentime;

	public String getOpentime() {
		return opentime;
	}
	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public String getFpId() {
		return fpId;
	}
	public void setFpId(String fpId) {
		this.fpId = fpId;
	}
	public String getDataItems() {
		return dataItems;
	}
	public void setDataItems(String dataItems) {
		this.dataItems = dataItems;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	
}
