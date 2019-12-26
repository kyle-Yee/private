package com.dcits.govsbu.sd.taxbankplatform.qybgxx.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 *
 */
public class TaxQybgxxEntity extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tthbLbpm;

    private String tthbBgxmDm;

    private String tthbBgqnr;

    private String tthbBghnr;

    private Long djxh;

    private String tthbBgdjmxuuid;

    private Date tthbXgrq;

    private Date tthbSjtbsj;

    private String tthbPzjgmc;

    private String tthbPzwj;

    private String tthbLrrDm;

    private Date tthbLrrq;

    private String tthbGluuid;

    private String tthbXgrDm;

    private String creatorid;

    private Date createtime;

    private String updatorid;

    private Date updatetime;

    public String getTthbLbpm() {
        return tthbLbpm;
    }

    public void setTthbLbpm(String tthbLbpm) {
        this.tthbLbpm = tthbLbpm == null ? null : tthbLbpm.trim();
    }

    public String getTthbBgxmDm() {
        return tthbBgxmDm;
    }

    public void setTthbBgxmDm(String tthbBgxmDm) {
        this.tthbBgxmDm = tthbBgxmDm == null ? null : tthbBgxmDm.trim();
    }

    public String getTthbBgqnr() {
        return tthbBgqnr;
    }

    public void setTthbBgqnr(String tthbBgqnr) {
        this.tthbBgqnr = tthbBgqnr == null ? null : tthbBgqnr.trim();
    }

    public String getTthbBghnr() {
        return tthbBghnr;
    }

    public void setTthbBghnr(String tthbBghnr) {
        this.tthbBghnr = tthbBghnr == null ? null : tthbBghnr.trim();
    }

    public Long getDjxh() {
        return djxh;
    }

    public void setDjxh(Long djxh) {
        this.djxh = djxh;
    }

    public String getTthbBgdjmxuuid() {
        return tthbBgdjmxuuid;
    }

    public void setTthbBgdjmxuuid(String tthbBgdjmxuuid) {
        this.tthbBgdjmxuuid = tthbBgdjmxuuid == null ? null : tthbBgdjmxuuid.trim();
    }

    public Date getTthbXgrq() {
        return tthbXgrq;
    }

    public void setTthbXgrq(Date tthbXgrq) {
        this.tthbXgrq = tthbXgrq;
    }

    public Date getTthbSjtbsj() {
        return tthbSjtbsj;
    }

    public void setTthbSjtbsj(Date tthbSjtbsj) {
        this.tthbSjtbsj = tthbSjtbsj;
    }

    public String getTthbPzjgmc() {
        return tthbPzjgmc;
    }

    public void setTthbPzjgmc(String tthbPzjgmc) {
        this.tthbPzjgmc = tthbPzjgmc == null ? null : tthbPzjgmc.trim();
    }

    public String getTthbPzwj() {
        return tthbPzwj;
    }

    public void setTthbPzwj(String tthbPzwj) {
        this.tthbPzwj = tthbPzwj == null ? null : tthbPzwj.trim();
    }

    public String getTthbLrrDm() {
        return tthbLrrDm;
    }

    public void setTthbLrrDm(String tthbLrrDm) {
        this.tthbLrrDm = tthbLrrDm == null ? null : tthbLrrDm.trim();
    }

    public Date getTthbLrrq() {
        return tthbLrrq;
    }

    public void setTthbLrrq(Date tthbLrrq) {
        this.tthbLrrq = tthbLrrq;
    }

    public String getTthbGluuid() {
        return tthbGluuid;
    }

    public void setTthbGluuid(String tthbGluuid) {
        this.tthbGluuid = tthbGluuid == null ? null : tthbGluuid.trim();
    }

    public String getTthbXgrDm() {
        return tthbXgrDm;
    }

    public void setTthbXgrDm(String tthbXgrDm) {
        this.tthbXgrDm = tthbXgrDm == null ? null : tthbXgrDm.trim();
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