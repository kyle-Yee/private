package com.dcits.wbjh.webservice.nmg.message.transferInfo;

public class transferInfo {
	private String senderID;
	private String receiverID;
	private String messageID;
	private String sourceMessageID;
	private String isRetry;
	private String sendTime;
	
	public transferInfo(){
		this.senderID ="";
		this.receiverID ="";
		this.messageID ="";
		this.sourceMessageID ="";
		this.isRetry ="";
		this.sendTime ="";
	}
	
	public String getSenderID() {
		return senderID;
	}
	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}
	public String getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}
	public String getMessageID() {
		return messageID;
	}
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}
	public String getSourceMessageID() {
		return sourceMessageID;
	}
	public void setSourceMessageID(String sourceMessageID) {
		this.sourceMessageID = sourceMessageID;
	}
	public String getIsRetry() {
		return isRetry;
	}
	public void setIsRetry(String isRetry) {
		this.isRetry = isRetry;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String toXml(){
		return "";
	}

}
