package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class ImgEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cityName;
	
	private String cityId;
	
	private String imgName;
	
	private String pitId;
	
	private String imgCode;
	
	private String imgUrl;
	
	private Long order;
	
	private String targetUrl;
	
	private char enabled;

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

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgCode() {
		return imgCode;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
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

	public String getPitId() {
		return pitId;
	}

	public void setPitId(String pitId) {
		this.pitId = pitId;
	}
	
	
}
