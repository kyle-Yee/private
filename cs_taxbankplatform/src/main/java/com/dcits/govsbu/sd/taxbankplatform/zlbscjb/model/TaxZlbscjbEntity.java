package com.dcits.govsbu.sd.taxbankplatform.zlbscjb.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 * 纳税企业财报申报主表信息
 */
public class TaxZlbscjbEntity extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long djxh;

    private Date ttzSssqQ;

    private Date ttzSssqZ;

    private String ttzZlbscjuuid;

    private Date ttzSjtbsj;

    private String ttzBszlDm;

    private String ttzZfbz;

    private Date ttzZfrq;

    private String ttzZfrDm;

    private Date ttzLrrq;

    private String ttzXgrDm;

    private Date ttzXgrq;

    private String ttzSjgsdq;

    private String ttzZlbsuuid;

    private String ttzBbh;

    private String ttzLrrDm;

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

    public Date getTtzSssqQ() {
        return ttzSssqQ;
    }

    public void setTtzSssqQ(Date ttzSssqQ) {
        this.ttzSssqQ = ttzSssqQ;
    }

    public Date getTtzSssqZ() {
        return ttzSssqZ;
    }

    public void setTtzSssqZ(Date ttzSssqZ) {
        this.ttzSssqZ = ttzSssqZ;
    }

    public String getTtzZlbscjuuid() {
        return ttzZlbscjuuid;
    }

    public void setTtzZlbscjuuid(String ttzZlbscjuuid) {
        this.ttzZlbscjuuid = ttzZlbscjuuid == null ? null : ttzZlbscjuuid.trim();
    }

    public Date getTtzSjtbsj() {
        return ttzSjtbsj;
    }

    public void setTtzSjtbsj(Date ttzSjtbsj) {
        this.ttzSjtbsj = ttzSjtbsj;
    }

    public String getTtzBszlDm() {
        return ttzBszlDm;
    }

    public void setTtzBszlDm(String ttzBszlDm) {
        this.ttzBszlDm = ttzBszlDm == null ? null : ttzBszlDm.trim();
    }

    public String getTtzZfbz() {
        return ttzZfbz;
    }

    public void setTtzZfbz(String ttzZfbz) {
        this.ttzZfbz = ttzZfbz == null ? null : ttzZfbz.trim();
    }

    public Date getTtzZfrq() {
        return ttzZfrq;
    }

    public void setTtzZfrq(Date ttzZfrq) {
        this.ttzZfrq = ttzZfrq;
    }

    public String getTtzZfrDm() {
        return ttzZfrDm;
    }

    public void setTtzZfrDm(String ttzZfrDm) {
        this.ttzZfrDm = ttzZfrDm == null ? null : ttzZfrDm.trim();
    }

    public Date getTtzLrrq() {
        return ttzLrrq;
    }

    public void setTtzLrrq(Date ttzLrrq) {
        this.ttzLrrq = ttzLrrq;
    }

    public String getTtzXgrDm() {
        return ttzXgrDm;
    }

    public void setTtzXgrDm(String ttzXgrDm) {
        this.ttzXgrDm = ttzXgrDm == null ? null : ttzXgrDm.trim();
    }

    public Date getTtzXgrq() {
        return ttzXgrq;
    }

    public void setTtzXgrq(Date ttzXgrq) {
        this.ttzXgrq = ttzXgrq;
    }

    public String getTtzSjgsdq() {
        return ttzSjgsdq;
    }

    public void setTtzSjgsdq(String ttzSjgsdq) {
        this.ttzSjgsdq = ttzSjgsdq == null ? null : ttzSjgsdq.trim();
    }

    public String getTtzZlbsuuid() {
        return ttzZlbsuuid;
    }

    public void setTtzZlbsuuid(String ttzZlbsuuid) {
        this.ttzZlbsuuid = ttzZlbsuuid == null ? null : ttzZlbsuuid.trim();
    }

    public String getTtzBbh() {
        return ttzBbh;
    }

    public void setTtzBbh(String ttzBbh) {
        this.ttzBbh = ttzBbh == null ? null : ttzBbh.trim();
    }

    public String getTtzLrrDm() {
        return ttzLrrDm;
    }

    public void setTtzLrrDm(String ttzLrrDm) {
        this.ttzLrrDm = ttzLrrDm == null ? null : ttzLrrDm.trim();
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