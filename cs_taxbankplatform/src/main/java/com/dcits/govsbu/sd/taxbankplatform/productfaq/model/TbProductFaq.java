package com.dcits.govsbu.sd.taxbankplatform.productfaq.model;

import java.util.Date;

public class TbProductFaq {
	/**
	 *常见问题Id
	 */
    private Integer pfId;

    private String pfRemark;

    private Integer creatorId;

    private Date createtime;

    private Integer updateId;

    private Date updatetime;

    private String pfContent;

    public Integer getPfId() {
        return pfId;
    }

    public void setPfId(Integer pfId) {
        this.pfId = pfId;
    }

    public String getPfRemark() {
        return pfRemark;
    }

    public void setPfRemark(String pfRemark) {
        this.pfRemark = pfRemark;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getPfContent() {
        return pfContent;
    }

    public void setPfContent(String pfContent) {
        this.pfContent = pfContent;
    }
}