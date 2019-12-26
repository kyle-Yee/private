package com.dcits.govsbu.sd.taxbankplatform.zcfzbqykjzd.model;

import java.math.BigDecimal;
import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 * 企业资产负债表（企业会计制度)信息
 */
public class TaxZcfzbQykjzdEntity extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long djxh;

    private Date ttzqSssqQ;

    private Date ttzqSssqZ;

    private String ttzqLpbm;

    private BigDecimal ttzqBnljs;

    private String ttzqBys;

    private String ttzqEwbhxh;

    private String ttzqHmc;

    private String ttzqLrrDm;

    private Date ttzqLrrq;

    private String ttzqSjgsdq;

    private Date ttzqSjtbSj;

    private String ttzqUuid;

    private String ttzqXgrDm;

    private Date ttzqXgrq;

    private String ttzqZlbscjuuid;

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

    public Date getTtzqSssqQ() {
        return ttzqSssqQ;
    }

    public void setTtzqSssqQ(Date ttzqSssqQ) {
        this.ttzqSssqQ = ttzqSssqQ;
    }

    public Date getTtzqSssqZ() {
        return ttzqSssqZ;
    }

    public void setTtzqSssqZ(Date ttzqSssqZ) {
        this.ttzqSssqZ = ttzqSssqZ;
    }

    public String getTtzqLpbm() {
        return ttzqLpbm;
    }

    public void setTtzqLpbm(String ttzqLpbm) {
        this.ttzqLpbm = ttzqLpbm == null ? null : ttzqLpbm.trim();
    }

    public BigDecimal getTtzqBnljs() {
        return ttzqBnljs;
    }

    public void setTtzqBnljs(BigDecimal ttzqBnljs) {
        this.ttzqBnljs = ttzqBnljs;
    }

    public String getTtzqBys() {
        return ttzqBys;
    }

    public void setTtzqBys(String ttzqBys) {
        this.ttzqBys = ttzqBys == null ? null : ttzqBys.trim();
    }

    public String getTtzqEwbhxh() {
        return ttzqEwbhxh;
    }

    public void setTtzqEwbhxh(String ttzqEwbhxh) {
        this.ttzqEwbhxh = ttzqEwbhxh == null ? null : ttzqEwbhxh.trim();
    }

    public String getTtzqHmc() {
        return ttzqHmc;
    }

    public void setTtzqHmc(String ttzqHmc) {
        this.ttzqHmc = ttzqHmc == null ? null : ttzqHmc.trim();
    }

    public String getTtzqLrrDm() {
        return ttzqLrrDm;
    }

    public void setTtzqLrrDm(String ttzqLrrDm) {
        this.ttzqLrrDm = ttzqLrrDm == null ? null : ttzqLrrDm.trim();
    }

    public Date getTtzqLrrq() {
        return ttzqLrrq;
    }

    public void setTtzqLrrq(Date ttzqLrrq) {
        this.ttzqLrrq = ttzqLrrq;
    }

    public String getTtzqSjgsdq() {
        return ttzqSjgsdq;
    }

    public void setTtzqSjgsdq(String ttzqSjgsdq) {
        this.ttzqSjgsdq = ttzqSjgsdq == null ? null : ttzqSjgsdq.trim();
    }

    public Date getTtzqSjtbSj() {
        return ttzqSjtbSj;
    }

    public void setTtzqSjtbSj(Date ttzqSjtbSj) {
        this.ttzqSjtbSj = ttzqSjtbSj;
    }

    public String getTtzqUuid() {
        return ttzqUuid;
    }

    public void setTtzqUuid(String ttzqUuid) {
        this.ttzqUuid = ttzqUuid == null ? null : ttzqUuid.trim();
    }

    public String getTtzqXgrDm() {
        return ttzqXgrDm;
    }

    public void setTtzqXgrDm(String ttzqXgrDm) {
        this.ttzqXgrDm = ttzqXgrDm == null ? null : ttzqXgrDm.trim();
    }

    public Date getTtzqXgrq() {
        return ttzqXgrq;
    }

    public void setTtzqXgrq(Date ttzqXgrq) {
        this.ttzqXgrq = ttzqXgrq;
    }

    public String getTtzqZlbscjuuid() {
        return ttzqZlbscjuuid;
    }

    public void setTtzqZlbscjuuid(String ttzqZlbscjuuid) {
        this.ttzqZlbscjuuid = ttzqZlbscjuuid == null ? null : ttzqZlbscjuuid.trim();
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