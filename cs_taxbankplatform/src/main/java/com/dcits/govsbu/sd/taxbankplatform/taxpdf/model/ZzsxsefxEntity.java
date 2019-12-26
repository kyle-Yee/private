package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import java.math.BigDecimal;
import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 *
 */
public class ZzsxsefxEntity extends BaseEntity {
    

    /**
	 * 获取增值税销售额分析数据
	 */
	private static final long serialVersionUID = 1L;

	private Integer tpzfYf;

    private BigDecimal tpzfC;

    private BigDecimal tpzfP;

    private BigDecimal tpzfN;

    private BigDecimal tpzfPc;

    private BigDecimal tpzfPn;

    private Date createdDate;


    public Integer getTpzfYf() {
        return tpzfYf;
    }

    public void setTpzfYf(Integer tpzfYf) {
        this.tpzfYf = tpzfYf;
    }

    public BigDecimal getTpzfC() {
        return tpzfC;
    }

    public void setTpzfC(BigDecimal tpzfC) {
        this.tpzfC = tpzfC;
    }

    public BigDecimal getTpzfP() {
        return tpzfP;
    }

    public void setTpzfP(BigDecimal tpzfP) {
        this.tpzfP = tpzfP;
    }

    public BigDecimal getTpzfN() {
        return tpzfN;
    }

    public void setTpzfN(BigDecimal tpzfN) {
        this.tpzfN = tpzfN;
    }

    public BigDecimal getTpzfPc() {
        return tpzfPc;
    }

    public void setTpzfPc(BigDecimal tpzfPc) {
        this.tpzfPc = tpzfPc;
    }

    public BigDecimal getTpzfPn() {
        return tpzfPn;
    }

    public void setTpzfPn(BigDecimal tpzfPn) {
        this.tpzfPn = tpzfPn;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
