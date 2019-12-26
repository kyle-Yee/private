package com.dcits.govsbu.sd.taxbankplatform.lrbxqykj.model;

import java.math.BigDecimal;
import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 * 利润表（一般企业会计制度）信息
 */
public class TaxLrbXqykjEntity extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Long djxh;

    private Date ttlxSssqQ;

    private Date ttlxSssqZ;

    private String ttlxLpbm;

    private BigDecimal ttlxBnljje;

    private String ttlxEwbhxh;

    private String ttlxHmc;

    private String ttlxLrrDm;

    private Date ttlxLrrq;

    private String ttlxSjgsdq;

    private Date ttlxSjtbSj;

    private BigDecimal ttlxSnje;

    private String ttlxUuid;

    private String ttlxXgrDm;

    private Date ttlxXgrq;

    private String ttlxZlbscjuuid;

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

    public Date getTtlxSssqQ() {
        return ttlxSssqQ;
    }

    public void setTtlxSssqQ(Date ttlxSssqQ) {
        this.ttlxSssqQ = ttlxSssqQ;
    }

    public Date getTtlxSssqZ() {
        return ttlxSssqZ;
    }

    public void setTtlxSssqZ(Date ttlxSssqZ) {
        this.ttlxSssqZ = ttlxSssqZ;
    }

    public String getTtlxLpbm() {
        return ttlxLpbm;
    }

    public void setTtlxLpbm(String ttlxLpbm) {
        this.ttlxLpbm = ttlxLpbm == null ? null : ttlxLpbm.trim();
    }

    public BigDecimal getTtlxBnljje() {
        return ttlxBnljje;
    }

    public void setTtlxBnljje(BigDecimal ttlxBnljje) {
        this.ttlxBnljje = ttlxBnljje;
    }

    public String getTtlxEwbhxh() {
        return ttlxEwbhxh;
    }

    public void setTtlxEwbhxh(String ttlxEwbhxh) {
        this.ttlxEwbhxh = ttlxEwbhxh == null ? null : ttlxEwbhxh.trim();
    }

    public String getTtlxHmc() {
        return ttlxHmc;
    }

    public void setTtlxHmc(String ttlxHmc) {
        this.ttlxHmc = ttlxHmc == null ? null : ttlxHmc.trim();
    }

    public String getTtlxLrrDm() {
        return ttlxLrrDm;
    }

    public void setTtlxLrrDm(String ttlxLrrDm) {
        this.ttlxLrrDm = ttlxLrrDm == null ? null : ttlxLrrDm.trim();
    }

    public Date getTtlxLrrq() {
        return ttlxLrrq;
    }

    public void setTtlxLrrq(Date ttlxLrrq) {
        this.ttlxLrrq = ttlxLrrq;
    }

    public String getTtlxSjgsdq() {
        return ttlxSjgsdq;
    }

    public void setTtlxSjgsdq(String ttlxSjgsdq) {
        this.ttlxSjgsdq = ttlxSjgsdq == null ? null : ttlxSjgsdq.trim();
    }

    public Date getTtlxSjtbSj() {
        return ttlxSjtbSj;
    }

    public void setTtlxSjtbSj(Date ttlxSjtbSj) {
        this.ttlxSjtbSj = ttlxSjtbSj;
    }

    public BigDecimal getTtlxSnje() {
        return ttlxSnje;
    }

    public void setTtlxSnje(BigDecimal ttlxSnje) {
        this.ttlxSnje = ttlxSnje;
    }

    public String getTtlxUuid() {
        return ttlxUuid;
    }

    public void setTtlxUuid(String ttlxUuid) {
        this.ttlxUuid = ttlxUuid == null ? null : ttlxUuid.trim();
    }

    public String getTtlxXgrDm() {
        return ttlxXgrDm;
    }

    public void setTtlxXgrDm(String ttlxXgrDm) {
        this.ttlxXgrDm = ttlxXgrDm == null ? null : ttlxXgrDm.trim();
    }

    public Date getTtlxXgrq() {
        return ttlxXgrq;
    }

    public void setTtlxXgrq(Date ttlxXgrq) {
        this.ttlxXgrq = ttlxXgrq;
    }

    public String getTtlxZlbscjuuid() {
        return ttlxZlbscjuuid;
    }

    public void setTtlxZlbscjuuid(String ttlxZlbscjuuid) {
        this.ttlxZlbscjuuid = ttlxZlbscjuuid == null ? null : ttlxZlbscjuuid.trim();
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