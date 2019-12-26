package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import java.math.BigDecimal;
import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 *
 */
public class JtnsqdEntity extends BaseEntity{

    /**
	 * 获取具体税种的综合清单
	 */
	private static final long serialVersionUID = 1L;

	private Integer tpjNf;

    private Integer tpjYf;

    private String tpjZsxmdm;

    private BigDecimal tpjYnsde;

    private BigDecimal tpjYnse;

    private BigDecimal tpjSjse;

    private Date createdDate;

    public Integer getTpjNf() {
        return tpjNf;
    }

    public void setTpjNf(Integer tpjNf) {
        this.tpjNf = tpjNf;
    }

    public Integer getTpjYf() {
        return tpjYf;
    }

    public void setTpjYf(Integer tpjYf) {
        this.tpjYf = tpjYf;
    }

    public String getTpjZsxmdm() {
        return tpjZsxmdm;
    }

    public void setTpjZsxmdm(String tpjZsxmdm) {
        this.tpjZsxmdm = tpjZsxmdm == null ? null : tpjZsxmdm.trim();
    }

    public BigDecimal getTpjYnsde() {
        return tpjYnsde;
    }

    public void setTpjYnsde(BigDecimal tpjYnsde) {
        this.tpjYnsde = tpjYnsde;
    }

    public BigDecimal getTpjYnse() {
        return tpjYnse;
    }

    public void setTpjYnse(BigDecimal tpjYnse) {
        this.tpjYnse = tpjYnse;
    }

    public BigDecimal getTpjSjse() {
        return tpjSjse;
    }

    public void setTpjSjse(BigDecimal tpjSjse) {
        this.tpjSjse = tpjSjse;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
