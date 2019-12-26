package com.dcits.govsbu.sd.taxbankplatform.fprzxx.model;

import java.math.BigDecimal;
import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 * 发票认证信息
 */
public class TaxFpRzxxEntity extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long djxh;
	
	private String ttfzFpdm;

    private String ttfzFphm;

    private String ttfzGfNsrsbh;

    private String ttfrXfNsrsbh;

    private BigDecimal ttfrJe;

    private BigDecimal ttfrSe;

    private BigDecimal ttfrSl;

    private Date ttfrFpKprq;

    private Date ttfrRzSj;

    private String ttrRzJq;

    private String ttfrQyMc;

    public Long getDjxh() {
		return djxh;
	}

	public void setDjxh(Long djxh) {
		this.djxh = djxh;
	}

	public String getTtfzFpdm() {
        return ttfzFpdm;
    }

    public void setTtfzFpdm(String ttfzFpdm) {
        this.ttfzFpdm = ttfzFpdm == null ? null : ttfzFpdm.trim();
    }

    public String getTtfzFphm() {
        return ttfzFphm;
    }

    public void setTtfzFphm(String ttfzFphm) {
        this.ttfzFphm = ttfzFphm == null ? null : ttfzFphm.trim();
    }

    public String getTtfzGfNsrsbh() {
        return ttfzGfNsrsbh;
    }

    public void setTtfzGfNsrsbh(String ttfzGfNsrsbh) {
        this.ttfzGfNsrsbh = ttfzGfNsrsbh == null ? null : ttfzGfNsrsbh.trim();
    }

    public String getTtfrXfNsrsbh() {
        return ttfrXfNsrsbh;
    }

    public void setTtfrXfNsrsbh(String ttfrXfNsrsbh) {
        this.ttfrXfNsrsbh = ttfrXfNsrsbh == null ? null : ttfrXfNsrsbh.trim();
    }

    public BigDecimal getTtfrJe() {
        return ttfrJe;
    }

    public void setTtfrJe(BigDecimal ttfrJe) {
        this.ttfrJe = ttfrJe;
    }

    public BigDecimal getTtfrSe() {
        return ttfrSe;
    }

    public void setTtfrSe(BigDecimal ttfrSe) {
        this.ttfrSe = ttfrSe;
    }

    public BigDecimal getTtfrSl() {
        return ttfrSl;
    }

    public void setTtfrSl(BigDecimal ttfrSl) {
        this.ttfrSl = ttfrSl;
    }

    public Date getTtfrFpKprq() {
        return ttfrFpKprq;
    }

    public void setTtfrFpKprq(Date ttfrFpKprq) {
        this.ttfrFpKprq = ttfrFpKprq;
    }

    public Date getTtfrRzSj() {
        return ttfrRzSj;
    }

    public void setTtfrRzSj(Date ttfrRzSj) {
        this.ttfrRzSj = ttfrRzSj;
    }

    public String getTtrRzJq() {
        return ttrRzJq;
    }

    public void setTtrRzJq(String ttrRzJq) {
        this.ttrRzJq = ttrRzJq == null ? null : ttrRzJq.trim();
    }

    public String getTtfrQyMc() {
        return ttfrQyMc;
    }

    public void setTtfrQyMc(String ttfrQyMc) {
        this.ttfrQyMc = ttfrQyMc == null ? null : ttfrQyMc.trim();
    }
}