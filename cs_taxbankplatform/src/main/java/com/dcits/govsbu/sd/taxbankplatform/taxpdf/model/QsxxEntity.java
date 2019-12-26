package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import java.math.BigDecimal;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 *
 */
public class QsxxEntity extends BaseEntity{
    /**
	 * 企业欠税信息
	 */
	private static final long serialVersionUID = 1L;
    private String zsxmmc;
    private BigDecimal ybse;
	public String getZsxmmc() {
		return zsxmmc;
	}
	public void setZsxmmc(String zsxmmc) {
		this.zsxmmc = zsxmmc;
	}
	public BigDecimal getYbse() {
		return ybse;
	}
	public void setYbse(BigDecimal ybse) {
		this.ybse = ybse;
	}

}
