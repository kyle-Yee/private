package com.dcits.govsbu.sd.taxbankplatform.qyfzjg.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 *
 */
public class TaxQyfzjgEntity extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tthfLbpm;

    private Long djxh;

    private Date tthfYxqq;

    private Date tthfYxqz;

    private String tthfXgrDm;

    private Date tthfXgrq;

    private String tthfSjgsdq;

    private String tthfLrrDm;

    private Date tthfLrrq;

    private Date tthfSjtbsj;

    private String tthfFzjghfdlxdh;

    private String tthfFzjghfddz;

    private String tthfFzjghfdmc;

    private String tthfFzjghfdnsrsbh;

    private Long tthfFzjghfddjxh;

    private String tthfFzjgfduuid;

    private String creatorid;

    private Date createtime;

    private String updatorid;

    private Date updatetime;

    public String getTthfLbpm() {
        return tthfLbpm;
    }

    public void setTthfLbpm(String tthfLbpm) {
        this.tthfLbpm = tthfLbpm == null ? null : tthfLbpm.trim();
    }

    public Long getDjxh() {
        return djxh;
    }

    public void setDjxh(Long djxh) {
        this.djxh = djxh;
    }

    public Date getTthfYxqq() {
        return tthfYxqq;
    }

    public void setTthfYxqq(Date tthfYxqq) {
        this.tthfYxqq = tthfYxqq;
    }

    public Date getTthfYxqz() {
        return tthfYxqz;
    }

    public void setTthfYxqz(Date tthfYxqz) {
        this.tthfYxqz = tthfYxqz;
    }

    public String getTthfXgrDm() {
        return tthfXgrDm;
    }

    public void setTthfXgrDm(String tthfXgrDm) {
        this.tthfXgrDm = tthfXgrDm == null ? null : tthfXgrDm.trim();
    }

    public Date getTthfXgrq() {
        return tthfXgrq;
    }

    public void setTthfXgrq(Date tthfXgrq) {
        this.tthfXgrq = tthfXgrq;
    }

    public String getTthfSjgsdq() {
        return tthfSjgsdq;
    }

    public void setTthfSjgsdq(String tthfSjgsdq) {
        this.tthfSjgsdq = tthfSjgsdq == null ? null : tthfSjgsdq.trim();
    }

    public String getTthfLrrDm() {
        return tthfLrrDm;
    }

    public void setTthfLrrDm(String tthfLrrDm) {
        this.tthfLrrDm = tthfLrrDm == null ? null : tthfLrrDm.trim();
    }

    public Date getTthfLrrq() {
        return tthfLrrq;
    }

    public void setTthfLrrq(Date tthfLrrq) {
        this.tthfLrrq = tthfLrrq;
    }

    public Date getTthfSjtbsj() {
        return tthfSjtbsj;
    }

    public void setTthfSjtbsj(Date tthfSjtbsj) {
        this.tthfSjtbsj = tthfSjtbsj;
    }

    public String getTthfFzjghfdlxdh() {
        return tthfFzjghfdlxdh;
    }

    public void setTthfFzjghfdlxdh(String tthfFzjghfdlxdh) {
        this.tthfFzjghfdlxdh = tthfFzjghfdlxdh == null ? null : tthfFzjghfdlxdh.trim();
    }

    public String getTthfFzjghfddz() {
        return tthfFzjghfddz;
    }

    public void setTthfFzjghfddz(String tthfFzjghfddz) {
        this.tthfFzjghfddz = tthfFzjghfddz == null ? null : tthfFzjghfddz.trim();
    }

    public String getTthfFzjghfdmc() {
        return tthfFzjghfdmc;
    }

    public void setTthfFzjghfdmc(String tthfFzjghfdmc) {
        this.tthfFzjghfdmc = tthfFzjghfdmc == null ? null : tthfFzjghfdmc.trim();
    }

    public String getTthfFzjghfdnsrsbh() {
        return tthfFzjghfdnsrsbh;
    }

    public void setTthfFzjghfdnsrsbh(String tthfFzjghfdnsrsbh) {
        this.tthfFzjghfdnsrsbh = tthfFzjghfdnsrsbh == null ? null : tthfFzjghfdnsrsbh.trim();
    }

    public Long getTthfFzjghfddjxh() {
        return tthfFzjghfddjxh;
    }

    public void setTthfFzjghfddjxh(Long tthfFzjghfddjxh) {
        this.tthfFzjghfddjxh = tthfFzjghfddjxh;
    }

    public String getTthfFzjgfduuid() {
        return tthfFzjgfduuid;
    }

    public void setTthfFzjgfduuid(String tthfFzjgfduuid) {
        this.tthfFzjgfduuid = tthfFzjgfduuid == null ? null : tthfFzjgfduuid.trim();
    }

    @Override
	public Date getCreatetime() {
        return createtime;
    }

    @Override
	public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
	public Date getUpdatetime() {
        return updatetime;
    }

    @Override
	public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

	@Override
	public String getCreatorid() {
		return creatorid;
	}

	@Override
	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	@Override
	public String getUpdatorid() {
		return updatorid;
	}

	@Override
	public void setUpdatorid(String updatorid) {
		this.updatorid = updatorid;
	}
}