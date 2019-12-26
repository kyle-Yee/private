package com.dcits.govsbu.sd.taxbankplatform.downreport.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class DownloadsReportRecEntity extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String downName;

    private String enterpriseName;

    private String downStatus;

    private Date downTime;
    
    private String reportUrl;

    private String laid;
    
    private String orgId;
    
    private String regionId;
    
    private String lasid;
    
    public String getLasid() {
		return lasid;
	}

	public void setLasid(String lasid) {
		this.lasid = lasid;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getLaid() {
		return laid;
	}

	public void setLaid(String laid) {
		this.laid = laid;
	}

	public String getDownName() {
        return downName;
    }

    public void setDownName(String downName) {
        this.downName = downName == null ? null : downName.trim();
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getDownStatus() {
        return downStatus;
    }

    public void setDownStatus(String downStatus) {
        this.downStatus = downStatus == null ? null : downStatus.trim();
    }

    public Date getDownTime() {
        return downTime;
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }

	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}
    
}