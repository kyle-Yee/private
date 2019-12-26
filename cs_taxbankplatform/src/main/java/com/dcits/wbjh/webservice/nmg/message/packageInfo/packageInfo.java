package com.dcits.wbjh.webservice.nmg.message.packageInfo;


public class packageInfo {

	private String sequence;
	private String content;
	
	public packageInfo(){
	}
	
	private paramList[] paramList;
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public paramList[] getParamList() {
		return paramList;
	}
	public void setParamList(paramList[] paramList) {
		this.paramList = paramList;
	}


	public String toXml(){
		return "";
	}
}
