package com.dcits.govsbu.sd.taxbankplatform.lrbqykjzd.model;

import java.math.BigDecimal;
import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 * 企业利润表（企业会计制度）信息
 */
public class TaxLrbQykjzdEntity extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long djxh;

    private Date ttlqSssqQ;

    private Date ttlqSssqZ;

    private String ttlqLpbm;

    private BigDecimal ttlqBnljs;

    private BigDecimal ttlqBys;

    private String ttlqEwbhxh;

    private String ttlqHmc;

    private String ttlqLrrDm;

    private Date ttlqLrrq;

    private String ttlqSjgsdq;

    private Date ttlqSjtbSj;

    private String ttlqUuid;

    private String ttlqXgrDm;

    private Date ttlqXgrq;

    private String ttlqZlbscjuuid;

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

    public Date getTtlqSssqQ() {
        return ttlqSssqQ;
    }

    public void setTtlqSssqQ(Date ttlqSssqQ) {
        this.ttlqSssqQ = ttlqSssqQ;
    }

    public Date getTtlqSssqZ() {
        return ttlqSssqZ;
    }

    public void setTtlqSssqZ(Date ttlqSssqZ) {
        this.ttlqSssqZ = ttlqSssqZ;
    }

    public String getTtlqLpbm() {
        return ttlqLpbm;
    }

    public void setTtlqLpbm(String ttlqLpbm) {
        this.ttlqLpbm = ttlqLpbm == null ? null : ttlqLpbm.trim();
    }

    public BigDecimal getTtlqBnljs() {
        return ttlqBnljs;
    }

    public void setTtlqBnljs(BigDecimal ttlqBnljs) {
        this.ttlqBnljs = ttlqBnljs;
    }

    public BigDecimal getTtlqBys() {
        return ttlqBys;
    }

    public void setTtlqBys(BigDecimal ttlqBys) {
        this.ttlqBys = ttlqBys;
    }

    public String getTtlqEwbhxh() {
        return ttlqEwbhxh;
    }

    public void setTtlqEwbhxh(String ttlqEwbhxh) {
        this.ttlqEwbhxh = ttlqEwbhxh == null ? null : ttlqEwbhxh.trim();
    }

    public String getTtlqHmc() {
        return ttlqHmc;
    }

    public void setTtlqHmc(String ttlqHmc) {
        this.ttlqHmc = ttlqHmc == null ? null : ttlqHmc.trim();
    }

    public String getTtlqLrrDm() {
        return ttlqLrrDm;
    }

    public void setTtlqLrrDm(String ttlqLrrDm) {
        this.ttlqLrrDm = ttlqLrrDm == null ? null : ttlqLrrDm.trim();
    }

    public Date getTtlqLrrq() {
        return ttlqLrrq;
    }

    public void setTtlqLrrq(Date ttlqLrrq) {
        this.ttlqLrrq = ttlqLrrq;
    }

    public String getTtlqSjgsdq() {
        return ttlqSjgsdq;
    }

    public void setTtlqSjgsdq(String ttlqSjgsdq) {
        this.ttlqSjgsdq = ttlqSjgsdq == null ? null : ttlqSjgsdq.trim();
    }

    public Date getTtlqSjtbSj() {
        return ttlqSjtbSj;
    }

    public void setTtlqSjtbSj(Date ttlqSjtbSj) {
        this.ttlqSjtbSj = ttlqSjtbSj;
    }

    public String getTtlqUuid() {
        return ttlqUuid;
    }

    public void setTtlqUuid(String ttlqUuid) {
        this.ttlqUuid = ttlqUuid == null ? null : ttlqUuid.trim();
    }

    public String getTtlqXgrDm() {
        return ttlqXgrDm;
    }

    public void setTtlqXgrDm(String ttlqXgrDm) {
        this.ttlqXgrDm = ttlqXgrDm == null ? null : ttlqXgrDm.trim();
    }

    public Date getTtlqXgrq() {
        return ttlqXgrq;
    }

    public void setTtlqXgrq(Date ttlqXgrq) {
        this.ttlqXgrq = ttlqXgrq;
    }

    public String getTtlqZlbscjuuid() {
        return ttlqZlbscjuuid;
    }

    public void setTtlqZlbscjuuid(String ttlqZlbscjuuid) {
        this.ttlqZlbscjuuid = ttlqZlbscjuuid == null ? null : ttlqZlbscjuuid.trim();
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