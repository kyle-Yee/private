package com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.state;

import java.io.Serializable;

/**
 * 功能:
 * 返回状态
 * @author Administrator
 */
public class ReturnState implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String returnCode;												//状态码
	private String returnMessage;											//状态信息
	
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
}
