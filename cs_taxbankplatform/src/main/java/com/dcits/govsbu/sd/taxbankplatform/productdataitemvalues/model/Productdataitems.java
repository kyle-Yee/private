package com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model;

import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class Productdataitems extends BaseEntity{
	private static final long serialVersionUID = 1L;
    private String pdiName;

    private String pdiCode;

    private HtmlTag htmlTag;

    private String enabled;

    private String regionid;

    private String orgid;

    private String ot;
    private String orgName;	//所属银行
    public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	private String regionName;	//所属城市
    public String getOt() {
		return ot;
	}

	public void setOt(String ot) {
		this.ot = ot;
	}

	private List<Productdataitemvalues> list;
    public List<Productdataitemvalues> getList() {
		return list;
	}

	public void setList(List<Productdataitemvalues> list) {
		this.list = list;
	}

	private String productditvaluesList;
    public String getProductditvaluesList() {
    	if(productditvaluesList==null){
    		return "";
    	}
		return productditvaluesList;
	}

	public void setProductditvaluesList(String productditvaluesList) {
		this.productditvaluesList = productditvaluesList;
	}
    
    public String getPdiName() {
        return pdiName;
    }

    public void setPdiName(String pdiName) {
        this.pdiName = pdiName;
    }

    public String getPdiCode() {
        return pdiCode;
    }

    public void setPdiCode(String pdiCode) {
        this.pdiCode = pdiCode;
    }



    public HtmlTag getHtmlTag() {
		return htmlTag;
	}

	public void setHtmlTag(HtmlTag htmlTag) {
		this.htmlTag = htmlTag;
	}

	public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

	public String getRegionid() {
		return regionid;
	}

	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}



}