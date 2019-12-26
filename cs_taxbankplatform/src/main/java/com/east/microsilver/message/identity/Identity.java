package com.east.microsilver.message.identity;

import java.io.Serializable;

/**
 * 功能:
 * 交易标识
 * @author Administrator
 */
public class Identity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String serviceId;
	private String channelId;
	private String password;
	
	
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
}
