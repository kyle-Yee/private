package com.dcits.govsbu.sd.taxbankplatform.dkxx.model;

import java.math.BigDecimal;
import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class Dksqxx extends BaseEntity {
      
        /**
	 * 
	 */
	private static final long serialVersionUID = 6171860949869107961L;

		private String  dksqdId;
        
	    public String getDksqdId() {
			return dksqdId;
		}

		public void setDksqdId(String dksqdId) {
			this.dksqdId = dksqdId;
		}

		private String sqxh;

	    private String nsryhxxQymc;

	    private String nsryhxxNsrsbh;

	    private String isnsrsbh;

	    private String sqrxxSffr;

	    private String sqrxxSfrmc;

	    private String sqrxxZjmc;

	    private String sqrxxZjhm;

	    private String sqrxxSjh;

	    private String nsryhxxFrmc;

	    private String nsryhxxZjmc;

	    private String nsryhxxZjhm;

	    private String nsryhxxFrsjh;

	    private String fpName;

	    private String fpId;

	    private BigDecimal laAmount;

	    private Date laApplyTime;

	    private String gsIds;

	    private String lfId;

	    private String channelid;

	    private Date createTime;
        private String enabled;
	    
	    public String getEnabled() {
			return enabled;
		}

		public void setEnabled(String enabled) {
			this.enabled = enabled;
		}

		private Bankfinal bankfinal;
	    
	    private Bankfinalend bankfinalend;
	



	    public Bankfinal getBankfinal() {
			return bankfinal;
		}

		public void setBankfinal(Bankfinal bankfinal) {
			this.bankfinal = bankfinal;
		}

		public Bankfinalend getBankfinalend() {
			return bankfinalend;
		}

		public void setBankfinalend(Bankfinalend bankfinalend) {
			this.bankfinalend = bankfinalend;
		}

		public String getSqxh() {
	        return sqxh;
	    }

	    public void setSqxh(String sqxh) {
	        this.sqxh = sqxh;
	    }

	    public String getNsryhxxQymc() {
	        return nsryhxxQymc;
	    }

	    public void setNsryhxxQymc(String nsryhxxQymc) {
	        this.nsryhxxQymc = nsryhxxQymc;
	    }

	    public String getNsryhxxNsrsbh() {
	        return nsryhxxNsrsbh;
	    }

	    public void setNsryhxxNsrsbh(String nsryhxxNsrsbh) {
	        this.nsryhxxNsrsbh = nsryhxxNsrsbh;
	    }

	    public String getIsnsrsbh() {
	        return isnsrsbh;
	    }

	    public void setIsnsrsbh(String isnsrsbh) {
	        this.isnsrsbh = isnsrsbh;
	    }

	    public String getSqrxxSffr() {
	        return sqrxxSffr;
	    }

	    public void setSqrxxSffr(String sqrxxSffr) {
	        this.sqrxxSffr = sqrxxSffr;
	    }

	    public String getSqrxxSfrmc() {
	        return sqrxxSfrmc;
	    }

	    public void setSqrxxSfrmc(String sqrxxSfrmc) {
	        this.sqrxxSfrmc = sqrxxSfrmc;
	    }

	    public String getSqrxxZjmc() {
	        return sqrxxZjmc;
	    }

	    public void setSqrxxZjmc(String sqrxxZjmc) {
	        this.sqrxxZjmc = sqrxxZjmc;
	    }

	    public String getSqrxxZjhm() {
	        return sqrxxZjhm;
	    }

	    public void setSqrxxZjhm(String sqrxxZjhm) {
	        this.sqrxxZjhm = sqrxxZjhm;
	    }

	    public String getSqrxxSjh() {
	        return sqrxxSjh;
	    }

	    public void setSqrxxSjh(String sqrxxSjh) {
	        this.sqrxxSjh = sqrxxSjh;
	    }

	    public String getNsryhxxFrmc() {
	        return nsryhxxFrmc;
	    }

	    public void setNsryhxxFrmc(String nsryhxxFrmc) {
	        this.nsryhxxFrmc = nsryhxxFrmc;
	    }

	    public String getNsryhxxZjmc() {
	        return nsryhxxZjmc;
	    }

	    public void setNsryhxxZjmc(String nsryhxxZjmc) {
	        this.nsryhxxZjmc = nsryhxxZjmc;
	    }

	    public String getNsryhxxZjhm() {
	        return nsryhxxZjhm;
	    }

	    public void setNsryhxxZjhm(String nsryhxxZjhm) {
	        this.nsryhxxZjhm = nsryhxxZjhm;
	    }

	    public String getNsryhxxFrsjh() {
	        return nsryhxxFrsjh;
	    }

	    public void setNsryhxxFrsjh(String nsryhxxFrsjh) {
	        this.nsryhxxFrsjh = nsryhxxFrsjh;
	    }

	    public String getFpName() {
	        return fpName;
	    }

	    public void setFpName(String fpName) {
	        this.fpName = fpName;
	    }

	    public String getFpId() {
	        return fpId;
	    }

	    public void setFpId(String fpId) {
	        this.fpId = fpId;
	    }

	    public BigDecimal getLaAmount() {
	        return laAmount;
	    }

	    public void setLaAmount(BigDecimal laAmount) {
	        this.laAmount = laAmount;
	    }

	    public Date getLaApplyTime() {
	        return laApplyTime;
	    }

	    public void setLaApplyTime(Date laApplyTime) {
	        this.laApplyTime = laApplyTime;
	    }

	    public String getGsIds() {
	        return gsIds;
	    }

	    public void setGsIds(String gsIds) {
	        this.gsIds = gsIds;
	    }

	    public String getLfId() {
	        return lfId;
	    }

	    public void setLfId(String lfId) {
	        this.lfId = lfId;
	    }

	    public String getChannelid() {
	        return channelid;
	    }

	    public void setChannelid(String channelid) {
	        this.channelid = channelid;
	    }

	    public Date getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(Date createTime) {
	        this.createTime = createTime;
	    }
}
