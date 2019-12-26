package com.dcits.govsbu.sd.taxbankplatform.fpkjxx.model;

import java.math.BigDecimal;
import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 *	发票开具信息
 */
public class TaxFpKjxxEntity extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	private Long djxh;
	
	private String ttfFpdm;

    private String ttfFphm;

    private String ttfGfNsrsbh;

    private String ttfkXfNsrsbh;

    private BigDecimal ttfkJe;

    private BigDecimal ttfkSe;

    private BigDecimal ttfkSl;

    private Date ttfkFpKprq;

    private String ttfkZfbz;

    private String ttfkBdbz;

    private String ttfkQyMc;

    public Long getDjxh() {
		return djxh;
	}

	public void setDjxh(Long djxh) {
		this.djxh = djxh;
	}

	public String getTtfFpdm() {
        return ttfFpdm;
    }

    public void setTtfFpdm(String ttfFpdm) {
        this.ttfFpdm = ttfFpdm == null ? null : ttfFpdm.trim();
    }

    public String getTtfFphm() {
        return ttfFphm;
    }

    public void setTtfFphm(String ttfFphm) {
        this.ttfFphm = ttfFphm == null ? null : ttfFphm.trim();
    }

    public String getTtfGfNsrsbh() {
        return ttfGfNsrsbh;
    }

    public void setTtfGfNsrsbh(String ttfGfNsrsbh) {
        this.ttfGfNsrsbh = ttfGfNsrsbh == null ? null : ttfGfNsrsbh.trim();
    }

    public String getTtfkXfNsrsbh() {
        return ttfkXfNsrsbh;
    }

    public void setTtfkXfNsrsbh(String ttfkXfNsrsbh) {
        this.ttfkXfNsrsbh = ttfkXfNsrsbh == null ? null : ttfkXfNsrsbh.trim();
    }

    public BigDecimal getTtfkJe() {
        return ttfkJe;
    }

    public void setTtfkJe(BigDecimal ttfkJe) {
        this.ttfkJe = ttfkJe;
    }

    public BigDecimal getTtfkSe() {
        return ttfkSe;
    }

    public void setTtfkSe(BigDecimal ttfkSe) {
        this.ttfkSe = ttfkSe;
    }

    public BigDecimal getTtfkSl() {
        return ttfkSl;
    }

    public void setTtfkSl(BigDecimal ttfkSl) {
        this.ttfkSl = ttfkSl;
    }

    public Date getTtfkFpKprq() {
        return ttfkFpKprq;
    }

    public void setTtfkFpKprq(Date ttfkFpKprq) {
        this.ttfkFpKprq = ttfkFpKprq;
    }

    public String getTtfkZfbz() {
        return ttfkZfbz;
    }

    public void setTtfkZfbz(String ttfkZfbz) {
        this.ttfkZfbz = ttfkZfbz == null ? null : ttfkZfbz.trim();
    }

    public String getTtfkBdbz() {
        return ttfkBdbz;
    }

    public void setTtfkBdbz(String ttfkBdbz) {
        this.ttfkBdbz = ttfkBdbz == null ? null : ttfkBdbz.trim();
    }

    public String getTtfkQyMc() {
        return ttfkQyMc;
    }

    public void setTtfkQyMc(String ttfkQyMc) {
        this.ttfkQyMc = ttfkQyMc == null ? null : ttfkQyMc.trim();
    }
}