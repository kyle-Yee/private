package com.dcits.govsbu.sd.taxbankplatform.statistics.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * 银行管理设置查询
 * @author Sigua.Huang
 * @date 2018年7月1日
 */
public class BankManagerInfo extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String fpId;
	
	private String fpName;
	
	private String bankid;
	
	private String bankname;
	
	private String datasum;
	
	private String dataItems;
	
	private String createid;
	
	private String starttime;
	
	private String endtime;
	
	private String opentime;
	
	private String status;

	public String getDataItems() {
		return dataItems;
	}

	public void setDataItems(String dataItems) {
		this.dataItems = dataItems;
	}

	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public String getFpId() {
		return fpId;
	}

	public void setFpId(String fpId) {
		this.fpId = fpId;
	}

	public String getFpName() {
		return fpName;
	}

	public void setFpName(String fpName) {
		this.fpName = fpName;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getDatasum() {
		return datasum;
	}

	public void setDatasum(String datasum) {
		this.datasum = datasum;
	}

	public String getCreateid() {
		return createid;
	}

	public void setCreateid(String createid) {
		this.createid = createid;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
