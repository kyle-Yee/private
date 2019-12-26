package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class StaticEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cityName;
	
	private String cityId;
	
	private String content;
	
	private String codeName;
	
	private String codeId;
	
	private String code;
	
	private char enabled;
	
	private char codeEnabled;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public char getEnabled() {
		return enabled;
	}

	public void setEnabled(char enabled) {
		this.enabled = enabled;
	}

	public char getCodeEnabled() {
		return codeEnabled;
	}

	public void setCodeEnabled(char codeEnabled) {
		this.codeEnabled = codeEnabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
