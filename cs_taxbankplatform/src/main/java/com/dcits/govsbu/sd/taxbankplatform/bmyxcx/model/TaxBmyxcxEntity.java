package com.dcits.govsbu.sd.taxbankplatform.bmyxcx.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 * 编码有效期
 */
public class TaxBmyxcxEntity extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String djxh;

    private String ttbLpbm;

    private Date ttbSssqQ;

    private Date ttbSssqZ;

    public String getDjxh() {
        return djxh;
    }

    public void setDjxh(String djxh) {
        this.djxh = djxh;
    }

    public String getTtbLpbm() {
        return ttbLpbm;
    }

    public void setTtbLpbm(String ttbLpbm) {
        this.ttbLpbm = ttbLpbm == null ? null : ttbLpbm.trim();
    }

    public Date getTtbSssqQ() {
        return ttbSssqQ;
    }

    public void setTtbSssqQ(Date ttbSssqQ) {
        this.ttbSssqQ = ttbSssqQ;
    }

    public Date getTtbSssqZ() {
        return ttbSssqZ;
    }

    public void setTtbSssqZ(Date ttbSssqZ) {
        this.ttbSssqZ = ttbSssqZ;
    }

}