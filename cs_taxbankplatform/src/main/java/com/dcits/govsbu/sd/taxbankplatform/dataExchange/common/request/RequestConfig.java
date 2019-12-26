package com.dcits.govsbu.sd.taxbankplatform.dataExchange.common.request;

/**
 * 功能:
 * 拼装报文服务serviceId, password, 请求参数
 * @author Administrator
 */
public class RequestConfig {
    private Parameter parameter;		//业务内容(参数)
    private String serviceId;					//服务id
	private String password;					//业务操作访问密码
	
	
	public Parameter getParameter() {
		return parameter;
	}
	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
