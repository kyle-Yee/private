package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class BgdjswbEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fileUrl;
	
	private String regionId;
	
	private char enabled;
	
	private String fileName;
	
	private String fileLx;

	
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public char getEnabled() {
		return enabled;
	}

	public void setEnabled(char enabled) {
		this.enabled = enabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileLx() {
		return fileLx;
	}

	public void setFileLx(String fileLx) {
		this.fileLx = fileLx;
	}
	
	

}
