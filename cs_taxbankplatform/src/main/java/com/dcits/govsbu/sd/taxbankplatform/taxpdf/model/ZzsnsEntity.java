package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import java.math.BigDecimal;
import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 *
 */
public class ZzsnsEntity extends BaseEntity {
    /**
	 * 增值税纳税情况（增值税一般纳税人）
	 */
	private static final long serialVersionUID = 1L;

    private Integer tpzNf;

    private Integer tpzYf;

    private BigDecimal tpzXse;

    private BigDecimal tpzXxse;

    private BigDecimal tpzJxse;

    private BigDecimal tpzYnse;

    private BigDecimal tpzYdkse;

    private BigDecimal tpzSjse;

    private Date createdDate;

    public Integer getTpzNf() {
        return tpzNf;
    }

    public void setTpzNf(Integer tpzNf) {
        this.tpzNf = tpzNf;
    }

    public Integer getTpzYf() {
        return tpzYf;
    }

    public void setTpzYf(Integer tpzYf) {
        this.tpzYf = tpzYf;
    }

    public BigDecimal getTpzXse() {
        return tpzXse;
    }

    public void setTpzXse(BigDecimal tpzXse) {
        this.tpzXse = tpzXse;
    }

    public BigDecimal getTpzXxse() {
        return tpzXxse;
    }

    public void setTpzXxse(BigDecimal tpzXxse) {
        this.tpzXxse = tpzXxse;
    }

    public BigDecimal getTpzJxse() {
        return tpzJxse;
    }

    public void setTpzJxse(BigDecimal tpzJxse) {
        this.tpzJxse = tpzJxse;
    }

    public BigDecimal getTpzYnse() {
        return tpzYnse;
    }

    public void setTpzYnse(BigDecimal tpzYnse) {
        this.tpzYnse = tpzYnse;
    }

    public BigDecimal getTpzYdkse() {
        return tpzYdkse;
    }

    public void setTpzYdkse(BigDecimal tpzYdkse) {
        this.tpzYdkse = tpzYdkse;
    }

    public BigDecimal getTpzSjse() {
        return tpzSjse;
    }

    public void setTpzSjse(BigDecimal tpzSjse) {
        this.tpzSjse = tpzSjse;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
