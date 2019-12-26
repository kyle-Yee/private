package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import java.math.BigDecimal;
import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 *
 */
public class SdsyyesrfxEntity extends BaseEntity{

    /**
	 * 所得税营业收入分析
	 */
	private static final long serialVersionUID = 1L;

	private Integer tpsfYf;

    private BigDecimal tpsfC;

    private BigDecimal tpsfP;

    private BigDecimal tpsfN;

    private BigDecimal tpsfPc;

    private BigDecimal tpsfPn;

    private Date createdDate;


    public Integer getTpsfYf() {
        return tpsfYf;
    }

    public void setTpsfYf(Integer tpsfYf) {
        this.tpsfYf = tpsfYf;
    }

    public BigDecimal getTpsfC() {
        return tpsfC;
    }

    public void setTpsfC(BigDecimal tpsfC) {
        this.tpsfC = tpsfC;
    }

    public BigDecimal getTpsfP() {
        return tpsfP;
    }

    public void setTpsfP(BigDecimal tpsfP) {
        this.tpsfP = tpsfP;
    }

    public BigDecimal getTpsfN() {
        return tpsfN;
    }

    public void setTpsfN(BigDecimal tpsfN) {
        this.tpsfN = tpsfN;
    }

    public BigDecimal getTpsfPc() {
        return tpsfPc;
    }

    public void setTpsfPc(BigDecimal tpsfPc) {
        this.tpsfPc = tpsfPc;
    }

    public BigDecimal getTpsfPn() {
        return tpsfPn;
    }

    public void setTpsfPn(BigDecimal tpsfPn) {
        this.tpsfPn = tpsfPn;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}