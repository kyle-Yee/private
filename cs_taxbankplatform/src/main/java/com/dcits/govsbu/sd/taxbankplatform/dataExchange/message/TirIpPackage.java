package com.dcits.govsbu.sd.taxbankplatform.dataExchange.message;

import java.io.Serializable;

import com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.content.BusinessContent;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.control.ContentControl;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.identity.Identity;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.state.ReturnState;




public class TirIpPackage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Identity identity;													//交易标识
	private ContentControl contentControl;					//内容控制
	private BusinessContent businessContent;				//业务内容
	private ReturnState returnState;									//返回状态
	
	
	public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
	
	public ContentControl getContentControl() {
		return contentControl;
	}
	public void setContentControl(ContentControl contentControl) {
		this.contentControl = contentControl;
	}
	
	public BusinessContent getBusinessContent() {
		return businessContent;
	}
	public void setBusinessContent(BusinessContent businessContent) {
		this.businessContent = businessContent;
	}
	
	public ReturnState getReturnState() {
		return returnState;
	}
	public void setReturnState(ReturnState returnState) {
		this.returnState = returnState;
	}
}
