package com.dcits.govsbu.sd.taxbankplatform.nsrxydj.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 * 纳税人信用等级
 */
public class TaxNsrxydjEntity extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long djxh;

    private String ttnLpbm;

    private String ttnDj;

    private String ttnYear;

    private String creatorid;

    private Date createtime;

    private String updatorid;

    private Date updatetime;

    public Long getDjxh() {
        return djxh;
    }

    public void setDjxh(Long djxh) {
        this.djxh = djxh;
    }

    public String getTtnLpbm() {
        return ttnLpbm;
    }

    public void setTtnLpbm(String ttnLpbm) {
        this.ttnLpbm = ttnLpbm == null ? null : ttnLpbm.trim();
    }

    public String getTtnDj() {
        return ttnDj;
    }

    public void setTtnDj(String ttnDj) {
        this.ttnDj = ttnDj == null ? null : ttnDj.trim();
    }

    public String getTtnYear() {
        return ttnYear;
    }

    public void setTtnYear(String ttnYear) {
        this.ttnYear = ttnYear == null ? null : ttnYear.trim();
    }

    @Override
	public Date getCreatetime() {
        return createtime;
    }

    @Override
	public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
	public Date getUpdatetime() {
        return updatetime;
    }

    @Override
	public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

	@Override
	public String getCreatorid() {
		return creatorid;
	}

	@Override
	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	@Override
	public String getUpdatorid() {
		return updatorid;
	}

	@Override
	public void setUpdatorid(String updatorid) {
		this.updatorid = updatorid;
	}
}