package com.dcits.govsbu.sd.taxbankplatform.regionalcascade.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class CascadeEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String uId;
	private String uName;
	private String name;
	private String pcId;
	private String pcpId;
	private String fname;
	private String fpcId ;
	private String fpcpId;
	private String ffname;
	private String ffpcId;
    private String ffpcpId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public String getPcpId() {
		return pcpId;
	}
	public void setPcpId(String pcpId) {
		this.pcpId = pcpId;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFpcId() {
		return fpcId;
	}
	public void setFpcId(String fpcId) {
		this.fpcId = fpcId;
	}
	public String getFpcpId() {
		return fpcpId;
	}
	public void setFpcpId(String fpcpId) {
		this.fpcpId = fpcpId;
	}
	public String getFfname() {
		return ffname;
	}
	public void setFfname(String ffname) {
		this.ffname = ffname;
	}
	public String getFfpcId() {
		return ffpcId;
	}
	public void setFfpcId(String ffpcId) {
		this.ffpcId = ffpcId;
	}
	public String getFfpcpId() {
		return ffpcpId;
	}
	public void setFfpcpId(String ffpcpId) {
		this.ffpcpId = ffpcpId;
	}
	
}
