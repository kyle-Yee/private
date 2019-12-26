package com.dcits.govsbu.sd.taxbankplatform.links.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class LinksEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String linksName;
	
	private String linksCity;
	
	private String linksUrl;
	
	private String linksPrint;
	
	private char enabled;
	
	private String cityId;
	
	private Long displayOrder; 
	
	public String getLinksName() {
		return linksName;
	}

	public void setLinksName(String linksName) {
		this.linksName = linksName;
	}

	public String getLinksCity() {
		return linksCity;
	}

	public void setLinksCity(String linksCity) {
		this.linksCity = linksCity;
	}

	public String getLinksUrl() {
		return linksUrl;
	}

	public void setLinksUrl(String linksUrl) {
		this.linksUrl = linksUrl;
	}

	public String getLinksPrint() {
		return linksPrint;
	}

	public void setLinksPrint(String linksPrint) {
		this.linksPrint = linksPrint;
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



	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public Long getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Long displayOrder) {
		this.displayOrder = displayOrder;
	}

}
