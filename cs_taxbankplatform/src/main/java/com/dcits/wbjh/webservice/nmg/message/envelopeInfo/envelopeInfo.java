package com.dcits.wbjh.webservice.nmg.message.envelopeInfo;

public class envelopeInfo {
	 
	private String sourceID;
	private String destinationID;
	private String destinationAppID;
	private String globalBusinessID;
	private String businessType;
	
	public envelopeInfo(String sourceID,String destinationID,String destinationAppID,String globalBusinessID,String businessType){
		this.sourceID = sourceID;
		this.destinationID = destinationID;
		this.destinationAppID = destinationAppID;
		this.globalBusinessID = globalBusinessID;
		this.businessType = businessType;
		
		
	}
	
	public envelopeInfo(){
		this.sourceID = "";
		this.destinationID = "";
		this.destinationAppID = "";
		this.globalBusinessID = "";
		this.businessType = "";
	}
	
	public String getSourceID() {
		return sourceID;
	}
	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}
	public String getDestinationID() {
		return destinationID;
	}
	public void setDestinationID(String destinationID) {
		this.destinationID = destinationID;
	}
	public String getDestinationAppID() {
		return destinationAppID;
	}
	public void setDestinationAppID(String destinationAppID) {
		this.destinationAppID = destinationAppID;
	}
	public String getGlobalBusinessID() {
		return globalBusinessID;
	}
	public void setGlobalBusinessID(String globalBusinessID) {
		this.globalBusinessID = globalBusinessID;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	public String toXml(){
		return "";
	}
}
