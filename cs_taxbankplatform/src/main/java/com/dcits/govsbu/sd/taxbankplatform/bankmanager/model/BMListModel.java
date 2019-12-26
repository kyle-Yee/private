package com.dcits.govsbu.sd.taxbankplatform.bankmanager.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class BMListModel extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8388590568405981979L;

	private String status;
	
	private String bankName;
	
	private String bankCode;
	
	private String bankregionid;
	
	private String contactName;
	
	private String contactphone;
	
	private int frequencylimit;
	
	private String dataCounter;
	
	private String createTime;
	
	private String updateTime;
	//3.0v modify by Sigua.Huang 2018/06/20 begin
	private String contacttelephone;	//联系人固定电话
	
	private String bankdepartment;		//银行负责部门
	
	private String leadername;			//部门负责人
	
	private String leadertelephone;		//部门负责人固定电话
	
	private String leaderphone;			//部门负责人手机号
	
	private String fpId;				//金融产品Id
	
	private String fpName;				//金融产品名称
	
	private String bankId;				//所属银行id
	
	private String applyRequire;		//申请条件
	
	private String starttime;			//数据项设置有效时间起
	
	private String endtime;				//数据项设置有效时间止
	
	private String opentime;			//开通时间
	
	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public String getBankdepartment() {
		return bankdepartment;
	}

	public void setBankdepartment(String bankdepartment) {
		this.bankdepartment = bankdepartment;
	}

	public String getContacttelephone() {
		return contacttelephone;
	}

	public void setContacttelephone(String contacttelephone) {
		this.contacttelephone = contacttelephone;
	}

	public String getLeadername() {
		return leadername;
	}

	public void setLeadername(String leadername) {
		this.leadername = leadername;
	}

	public String getLeadertelephone() {
		return leadertelephone;
	}

	public void setLeadertelephone(String leadertelephone) {
		this.leadertelephone = leadertelephone;
	}

	public String getLeaderphone() {
		return leaderphone;
	}

	public void setLeaderphone(String leaderphone) {
		this.leaderphone = leaderphone;
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

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
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

	public String getApplyRequire() {
		return applyRequire;
	}

	public void setApplyRequire(String applyRequire) {
		this.applyRequire = applyRequire;
	}

	//3.0v modify by Sigua.Huang 2018/06/20 end
	public String getStatus() {
		if(status==null)
			return "0";
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactphone() {
		return contactphone;
	}

	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}

	public int getFrequencylimit() {
		return frequencylimit;
	}

	public void setFrequencylimit(int frequencylimit) {
		this.frequencylimit = frequencylimit;
	}

	public String getDataCounter() {
		return dataCounter;
	}

	public void setDataCounter(String dataCounter) {
		this.dataCounter = dataCounter;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getBankregionid() {
		return bankregionid;
	}

	public void setBankregionid(String bankregionid) {
		this.bankregionid = bankregionid;
	}
	
}
