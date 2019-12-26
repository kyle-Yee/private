package com.east.microsilver.common;

/**
 * 响应报文业务内容对象
 * @author Administrator
 */
public class ResMsg {
	
	private String code;						//状态代码
	private String msg;						//状态信息
	private String userToken;			//userToken
	private Object data;						//单条数据
	private Object dataList;				//数据集合
	//授权编码有效期
	private String sssqq;					//所属时期起
	private String sssqz;						//所属时期止
	private String success;				//成功标识
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public Object getDataList() {
		return dataList;
	}
	public void setDataList(Object dataList) {
		this.dataList = dataList;
	}
	
	public String getSssqq() {
		return sssqq;
	}
	public void setSssqq(String sssqq) {
		this.sssqq = sssqq;
	}
	
	public String getSssqz() {
		return sssqz;
	}
	public void setSssqz(String sssqz) {
		this.sssqz = sssqz;
	}
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
}
