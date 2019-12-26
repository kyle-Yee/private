package com.dcits.govsbu.sd.taxbankplatform.downreport.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class DownloadsReportEntity extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String downName;

    private String enterpriseName;

    private String downStatus;

    private Date downTime;

    private Long totaldowns;

    private Date updateTime;
    
    private String laid;

    private int days;
    
    private String orgId;
    
    private String regionId;
    
    private String filename;
    
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

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
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


    public Long getTotaldowns() {
		return totaldowns;
	}

	public void setTotaldowns(Long totaldowns) {
		this.totaldowns = totaldowns;
	}

	public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}