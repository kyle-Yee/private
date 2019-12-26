package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import java.math.BigDecimal;
import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 *
 */
public class SdsnsqkEntity extends BaseEntity {

    /**
	 * 所得税月季纳税情况
	 */
	private static final long serialVersionUID = 1L;

	private Integer tpsNf;

    private Integer tpsYf;

    private BigDecimal tpsYssr;

    private BigDecimal tpsLrze;

    private BigDecimal tpsSjlre;

    private BigDecimal tpsYnse;

    private BigDecimal tpsYdkjms;

    private BigDecimal tpsSjse;

    private Date createdDate;

    public Integer getTpsNf() {
        return tpsNf;
    }

    public void setTpsNf(Integer tpsNf) {
        this.tpsNf = tpsNf;
    }

    public Integer getTpsYf() {
        return tpsYf;
    }

    public void setTpsYf(Integer tpsYf) {
        this.tpsYf = tpsYf;
    }

    public BigDecimal getTpsYssr() {
        return tpsYssr;
    }

    public void setTpsYssr(BigDecimal tpsYssr) {
        this.tpsYssr = tpsYssr;
    }

    public BigDecimal getTpsLrze() {
        return tpsLrze;
    }

    public void setTpsLrze(BigDecimal tpsLrze) {
        this.tpsLrze = tpsLrze;
    }

    public BigDecimal getTpsSjlre() {
        return tpsSjlre;
    }

    public void setTpsSjlre(BigDecimal tpsSjlre) {
        this.tpsSjlre = tpsSjlre;
    }

    public BigDecimal getTpsYnse() {
        return tpsYnse;
    }

    public void setTpsYnse(BigDecimal tpsYnse) {
        this.tpsYnse = tpsYnse;
    }

    public BigDecimal getTpsYdkjms() {
        return tpsYdkjms;
    }

    public void setTpsYdkjms(BigDecimal tpsYdkjms) {
        this.tpsYdkjms = tpsYdkjms;
    }

    public BigDecimal getTpsSjse() {
        return tpsSjse;
    }

    public void setTpsSjse(BigDecimal tpsSjse) {
        this.tpsSjse = tpsSjse;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
