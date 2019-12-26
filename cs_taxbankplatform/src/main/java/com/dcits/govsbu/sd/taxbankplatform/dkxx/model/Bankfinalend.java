package com.dcits.govsbu.sd.taxbankplatform.dkxx.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class Bankfinalend extends BaseEntity{
	
        /**
	 * 
	 */
	private static final long serialVersionUID = 6041881899412920428L;

		private String endssid;

	    public String getEndssid() {
			return endssid;
		}

		public void setEndssid(String endssid) {
			this.endssid = endssid;
		}

		private String sqxh;

	    private String nsrsbh;

	    private String lfId;

	    private String laeZzbz;

	    private Date laeEbddate;

	    private String laeCreditQuota;

	    private Integer laeOverdueCount;

	    private Integer bankloanType;

	    private Date createTime;

	     private String enabled;
		    
		    public String getEnabled() {
				return enabled;
			}

			public void setEnabled(String enabled) {
				this.enabled = enabled;
			}

	    public String getSqxh() {
	        return sqxh;
	    }

	    public void setSqxh(String sqxh) {
	        this.sqxh = sqxh;
	    }

	    public String getNsrsbh() {
	        return nsrsbh;
	    }

	    public void setNsrsbh(String nsrsbh) {
	        this.nsrsbh = nsrsbh;
	    }

	

	    public String getLfId() {
			return lfId;
		}

		public void setLfId(String lfId) {
			this.lfId = lfId;
		}

		public String getLaeZzbz() {
	        return laeZzbz;
	    }

	    public void setLaeZzbz(String laeZzbz) {
	        this.laeZzbz = laeZzbz;
	    }

	    public Date getLaeEbddate() {
	        return laeEbddate;
	    }

	    public void setLaeEbddate(Date laeEbddate) {
	        this.laeEbddate = laeEbddate;
	    }

	    public String getLaeCreditQuota() {
	        return laeCreditQuota;
	    }

	    public void setLaeCreditQuota(String laeCreditQuota) {
	        this.laeCreditQuota = laeCreditQuota;
	    }

	    public Integer getLaeOverdueCount() {
	        return laeOverdueCount;
	    }

	    public void setLaeOverdueCount(Integer laeOverdueCount) {
	        this.laeOverdueCount = laeOverdueCount;
	    }

	    public Integer getBankloanType() {
	        return bankloanType;
	    }

	    public void setBankloanType(Integer bankloanType) {
	        this.bankloanType = bankloanType;
	    }

	    public Date getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(Date createTime) {
	        this.createTime = createTime;
	    }


}
