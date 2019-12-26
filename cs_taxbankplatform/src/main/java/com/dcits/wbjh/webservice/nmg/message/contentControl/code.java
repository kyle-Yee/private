package com.dcits.wbjh.webservice.nmg.message.contentControl;

public class code {

	private  boolean isCode;

	private String codeType;
	
	
	public code(){		
		this.isCode =false;
		this.codeType ="";
	}
	public boolean isCode() {
		return isCode;
	}
	public void setCode(boolean isCode) {
		this.isCode = isCode;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
}
