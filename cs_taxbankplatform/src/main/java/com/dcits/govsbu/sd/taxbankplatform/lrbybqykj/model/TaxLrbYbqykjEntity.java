package com.dcits.govsbu.sd.taxbankplatform.lrbybqykj.model;

import java.math.BigDecimal;
import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 * 利润表（小企业会计制度）_年报信息
 */
public class TaxLrbYbqykjEntity extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	    private Long djxh;

	    private Date ttlySssqQ;

	    private Date ttlySssqZ;

	    private String ttlyLpbm;

	    private BigDecimal ttlyBqye;

	    private String ttlyEwbhxh;

	    private String ttlyHmc;

	    private String ttlyLrrDm;

	    private Date ttlyLrrq;

	    private String ttlySjgsdq;

	    private Date ttlySjtbSj;

	    private BigDecimal ttlySqye1;

	    private String ttlyUuid;

	    private String ttlyXgrDm;

	    private Date ttlyXgrq;

	    private String ttlyZlbscjuuid;

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

	    public Date getTtlySssqQ() {
	        return ttlySssqQ;
	    }

	    public void setTtlySssqQ(Date ttlySssqQ) {
	        this.ttlySssqQ = ttlySssqQ;
	    }

	    public Date getTtlySssqZ() {
	        return ttlySssqZ;
	    }

	    public void setTtlySssqZ(Date ttlySssqZ) {
	        this.ttlySssqZ = ttlySssqZ;
	    }

	    public String getTtlyLpbm() {
	        return ttlyLpbm;
	    }

	    public void setTtlyLpbm(String ttlyLpbm) {
	        this.ttlyLpbm = ttlyLpbm == null ? null : ttlyLpbm.trim();
	    }

	    public BigDecimal getTtlyBqye() {
	        return ttlyBqye;
	    }

	    public void setTtlyBqye(BigDecimal ttlyBqye) {
	        this.ttlyBqye = ttlyBqye;
	    }

	    public String getTtlyEwbhxh() {
	        return ttlyEwbhxh;
	    }

	    public void setTtlyEwbhxh(String ttlyEwbhxh) {
	        this.ttlyEwbhxh = ttlyEwbhxh == null ? null : ttlyEwbhxh.trim();
	    }

	    public String getTtlyHmc() {
	        return ttlyHmc;
	    }

	    public void setTtlyHmc(String ttlyHmc) {
	        this.ttlyHmc = ttlyHmc == null ? null : ttlyHmc.trim();
	    }

	    public String getTtlyLrrDm() {
	        return ttlyLrrDm;
	    }

	    public void setTtlyLrrDm(String ttlyLrrDm) {
	        this.ttlyLrrDm = ttlyLrrDm == null ? null : ttlyLrrDm.trim();
	    }

	    public Date getTtlyLrrq() {
	        return ttlyLrrq;
	    }

	    public void setTtlyLrrq(Date ttlyLrrq) {
	        this.ttlyLrrq = ttlyLrrq;
	    }

	    public String getTtlySjgsdq() {
	        return ttlySjgsdq;
	    }

	    public void setTtlySjgsdq(String ttlySjgsdq) {
	        this.ttlySjgsdq = ttlySjgsdq == null ? null : ttlySjgsdq.trim();
	    }

	    public Date getTtlySjtbSj() {
	        return ttlySjtbSj;
	    }

	    public void setTtlySjtbSj(Date ttlySjtbSj) {
	        this.ttlySjtbSj = ttlySjtbSj;
	    }

	    public BigDecimal getTtlySqye1() {
	        return ttlySqye1;
	    }

	    public void setTtlySqye1(BigDecimal ttlySqye1) {
	        this.ttlySqye1 = ttlySqye1;
	    }

	    public String getTtlyUuid() {
	        return ttlyUuid;
	    }

	    public void setTtlyUuid(String ttlyUuid) {
	        this.ttlyUuid = ttlyUuid == null ? null : ttlyUuid.trim();
	    }

	    public String getTtlyXgrDm() {
	        return ttlyXgrDm;
	    }

	    public void setTtlyXgrDm(String ttlyXgrDm) {
	        this.ttlyXgrDm = ttlyXgrDm == null ? null : ttlyXgrDm.trim();
	    }

	    public Date getTtlyXgrq() {
	        return ttlyXgrq;
	    }

	    public void setTtlyXgrq(Date ttlyXgrq) {
	        this.ttlyXgrq = ttlyXgrq;
	    }

	    public String getTtlyZlbscjuuid() {
	        return ttlyZlbscjuuid;
	    }

	    public void setTtlyZlbscjuuid(String ttlyZlbscjuuid) {
	        this.ttlyZlbscjuuid = ttlyZlbscjuuid == null ? null : ttlyZlbscjuuid.trim();
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