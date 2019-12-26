package com.dcits.wbjh.webservice.nmg.message.contentControl;

public class encrypt {

	private boolean isEncrypt;
	private String encryptType;
	
	public encrypt(){
		this.isEncrypt = false;
		this.encryptType = "";
		
	}
	public boolean isEncrypt() {
		return isEncrypt;
	}
	public void setEncrypt(boolean isEncrypt) {
		this.isEncrypt = isEncrypt;
	}
	public String getEncryptType() {
		return encryptType;
	}
	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}
	
}
