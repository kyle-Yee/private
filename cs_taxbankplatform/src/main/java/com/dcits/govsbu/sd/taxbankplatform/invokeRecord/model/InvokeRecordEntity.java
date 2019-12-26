package com.dcits.govsbu.sd.taxbankplatform.invokeRecord.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
//接口调用记录表
public class InvokeRecordEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String invokeId;//接口调用记录id
	private String interfaceReturnInfo;//接口返回信息
	private Date createTime;//创建时间
	private String userId;//用户名
	private String serviceId;//服务器id
	private int invokeTimes;//调用次数
	private String lpbm;//授权编码
	private String yxqq;//有效期起
	private String yxqz;//有效期止
	public String getInvokeId() {
		return invokeId;
	}
	public void setInvokeId(String invokeId) {
		this.invokeId = invokeId;
	}
	public String getInterfaceReturnInfo() {
		return interfaceReturnInfo;
	}
	public void setInterfaceReturnInfo(String interfaceReturnInfo) {
		this.interfaceReturnInfo = interfaceReturnInfo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public int getInvokeTimes() {
		return invokeTimes;
	}
	public void setInvokeTimes(int invokeTimes) {
		this.invokeTimes = invokeTimes;
	}
	public String getLpbm() {
		return lpbm;
	}
	public void setLpbm(String lpbm) {
		this.lpbm = lpbm;
	}
	public String getYxqq() {
		return yxqq;
	}
	public void setYxqq(String yxqq) {
		this.yxqq = yxqq;
	}
	public String getYxqz() {
		return yxqz;
	}
	public void setYxqz(String yxqz) {
		this.yxqz = yxqz;
	}
	
}
