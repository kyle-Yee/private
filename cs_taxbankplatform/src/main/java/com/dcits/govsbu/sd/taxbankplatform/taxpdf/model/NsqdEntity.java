package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import java.math.BigDecimal;
import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 *
 */
public class NsqdEntity extends BaseEntity {

    /**
	 * 获取纳税清单的字段
	 */
	private static final long serialVersionUID = 1L;

	private Integer tpnNf;

    private BigDecimal tpnZzs;

    private BigDecimal tpnQysds;

    private BigDecimal tpnCswfjs;

    private BigDecimal tpnYhs;

    private BigDecimal tpnCztdsy;

    private BigDecimal tpnJyffj;

    private BigDecimal tpnDfjyfj;

    private BigDecimal tpnSljszx;

    private BigDecimal tpnQtsr;

    private BigDecimal tpnTotal;

    private Date createdId;

	public Integer getTpnNf() {
        return tpnNf;
    }

    public void setTpnNf(Integer tpnNf) {
        this.tpnNf = tpnNf;
    }

    public BigDecimal getTpnZzs() {
        return tpnZzs;
    }

    public void setTpnZzs(BigDecimal tpnZzs) {
        this.tpnZzs = tpnZzs;
    }

    public BigDecimal getTpnQysds() {
        return tpnQysds;
    }

    public void setTpnQysds(BigDecimal tpnQysds) {
        this.tpnQysds = tpnQysds;
    }

    public BigDecimal getTpnCswfjs() {
        return tpnCswfjs;
    }

    public void setTpnCswfjs(BigDecimal tpnCswfjs) {
        this.tpnCswfjs = tpnCswfjs;
    }

    public BigDecimal getTpnYhs() {
        return tpnYhs;
    }

    public void setTpnYhs(BigDecimal tpnYhs) {
        this.tpnYhs = tpnYhs;
    }

    public BigDecimal getTpnCztdsy() {
        return tpnCztdsy;
    }

    public void setTpnCztdsy(BigDecimal tpnCztdsy) {
        this.tpnCztdsy = tpnCztdsy;
    }

    public BigDecimal getTpnJyffj() {
        return tpnJyffj;
    }

    public void setTpnJyffj(BigDecimal tpnJyffj) {
        this.tpnJyffj = tpnJyffj;
    }

    public BigDecimal getTpnDfjyfj() {
        return tpnDfjyfj;
    }

    public void setTpnDfjyfj(BigDecimal tpnDfjyfj) {
        this.tpnDfjyfj = tpnDfjyfj;
    }

    public BigDecimal getTpnSljszx() {
        return tpnSljszx;
    }

    public void setTpnSljszx(BigDecimal tpnSljszx) {
        this.tpnSljszx = tpnSljszx;
    }

    public BigDecimal getTpnQtsr() {
        return tpnQtsr;
    }

    public void setTpnQtsr(BigDecimal tpnQtsr) {
        this.tpnQtsr = tpnQtsr;
    }

    public BigDecimal getTpnTotal() {
        return tpnTotal;
    }

    public void setTpnTotal(BigDecimal tpnTotal) {
        this.tpnTotal = tpnTotal;
    }

    public Date getCreatedId() {
        return createdId;
    }

    public void setCreatedId(Date createdId) {
        this.createdId = createdId;
    }
}
