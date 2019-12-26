package com.dcits.wbjh.webservice.nmg.message.contentControl;

public class zip {

	private boolean isZip;

	private String zipType;
	public zip(){
		this.isZip = false;
		this.zipType = "";
		
	}
	
	public boolean isZip() {
		return isZip;
	}
	public void setZip(boolean isZip) {
		this.isZip = isZip;
	}
	public String getZipType() {
		return zipType;
	}
	public void setZipType(String zipType) {
		this.zipType = zipType;
	}
}
