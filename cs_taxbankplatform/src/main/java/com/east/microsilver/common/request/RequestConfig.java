package com.east.microsilver.common.request;

/**
 * 功能:
 * 拼装报文服务serviceId, password, 请求参数
 * @author Administrator
 */
public class RequestConfig {
	
    private String serviceId;					//服务id
    private String channelId;					//channelId
	private String password;					//业务操作访问密码
	private String content;						//业务参数
	
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
