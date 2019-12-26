package com.dcits.govsbu.sd.taxbankplatform.nsrzgxxjgb.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 *
 */
public class TaxNsrzgxxjgbEntity extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long djxh;

    private Date ttnSjtbsj;

    private Date ttnXgrq;

    private String ttnZfbz;

    private String ttnZfrDm;

    private Date ttnZfrq;

    private String ttnRdpzuuid;

    private String ttnLcslid;

    private String ttnNsrzglxDm;

    private Date ttnYxqq;

    private Date ttnYxqz;

    private Date ttnSjzzrq;

    private String ttnQxbz;

    private String ttnSjgsdq;

    private String ttnLrrDm;

    private Date ttnLrrq;

    private String ttnXgrDm;

    private String ttnWszg;

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

    public Date getTtnSjtbsj() {
        return ttnSjtbsj;
    }

    public void setTtnSjtbsj(Date ttnSjtbsj) {
        this.ttnSjtbsj = ttnSjtbsj;
    }

    public Date getTtnXgrq() {
        return ttnXgrq;
    }

    public void setTtnXgrq(Date ttnXgrq) {
        this.ttnXgrq = ttnXgrq;
    }

    public String getTtnZfbz() {
        return ttnZfbz;
    }

    public void setTtnZfbz(String ttnZfbz) {
        this.ttnZfbz = ttnZfbz == null ? null : ttnZfbz.trim();
    }

    public String getTtnZfrDm() {
        return ttnZfrDm;
    }

    public void setTtnZfrDm(String ttnZfrDm) {
        this.ttnZfrDm = ttnZfrDm == null ? null : ttnZfrDm.trim();
    }

    public Date getTtnZfrq() {
        return ttnZfrq;
    }

    public void setTtnZfrq(Date ttnZfrq) {
        this.ttnZfrq = ttnZfrq;
    }

    public String getTtnRdpzuuid() {
        return ttnRdpzuuid;
    }

    public void setTtnRdpzuuid(String ttnRdpzuuid) {
        this.ttnRdpzuuid = ttnRdpzuuid == null ? null : ttnRdpzuuid.trim();
    }

    public String getTtnLcslid() {
        return ttnLcslid;
    }

    public void setTtnLcslid(String ttnLcslid) {
        this.ttnLcslid = ttnLcslid == null ? null : ttnLcslid.trim();
    }

    public String getTtnNsrzglxDm() {
        return ttnNsrzglxDm;
    }

    public void setTtnNsrzglxDm(String ttnNsrzglxDm) {
        this.ttnNsrzglxDm = ttnNsrzglxDm == null ? null : ttnNsrzglxDm.trim();
    }

    public Date getTtnYxqq() {
        return ttnYxqq;
    }

    public void setTtnYxqq(Date ttnYxqq) {
        this.ttnYxqq = ttnYxqq;
    }

    public Date getTtnYxqz() {
        return ttnYxqz;
    }

    public void setTtnYxqz(Date ttnYxqz) {
        this.ttnYxqz = ttnYxqz;
    }

    public Date getTtnSjzzrq() {
        return ttnSjzzrq;
    }

    public void setTtnSjzzrq(Date ttnSjzzrq) {
        this.ttnSjzzrq = ttnSjzzrq;
    }

    public String getTtnQxbz() {
        return ttnQxbz;
    }

    public void setTtnQxbz(String ttnQxbz) {
        this.ttnQxbz = ttnQxbz == null ? null : ttnQxbz.trim();
    }

    public String getTtnSjgsdq() {
        return ttnSjgsdq;
    }

    public void setTtnSjgsdq(String ttnSjgsdq) {
        this.ttnSjgsdq = ttnSjgsdq == null ? null : ttnSjgsdq.trim();
    }

    public String getTtnLrrDm() {
        return ttnLrrDm;
    }

    public void setTtnLrrDm(String ttnLrrDm) {
        this.ttnLrrDm = ttnLrrDm == null ? null : ttnLrrDm.trim();
    }

    public Date getTtnLrrq() {
        return ttnLrrq;
    }

    public void setTtnLrrq(Date ttnLrrq) {
        this.ttnLrrq = ttnLrrq;
    }

    public String getTtnXgrDm() {
        return ttnXgrDm;
    }

    public void setTtnXgrDm(String ttnXgrDm) {
        this.ttnXgrDm = ttnXgrDm == null ? null : ttnXgrDm.trim();
    }

    public String getTtnWszg() {
        return ttnWszg;
    }

    public void setTtnWszg(String ttnWszg) {
        this.ttnWszg = ttnWszg == null ? null : ttnWszg.trim();
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