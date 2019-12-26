package com.dcits.wbjh.webservice.nmg.message.contentControl;

public class contentControl {
	private code code;
	private encrypt encrypt;
	private zip zip;
	
	
	
	public contentControl(){
		this.code =new code();
		this.encrypt =new encrypt();
		this.zip =new zip();
		
		
	}
	public code getCode() {
		return code;
	}



	public void setCode(code code) {
		this.code = code;
	}



	public encrypt getEncrypt() {
		return encrypt;
	}



	public void setEncrypt(encrypt encrypt) {
		this.encrypt = encrypt;
	}



	public zip getZip() {
		return zip;
	}



	public void setZip(zip zip) {
		this.zip = zip;
	}



	
	
	
	public String toXml(){
		return "";
	}
	
}
