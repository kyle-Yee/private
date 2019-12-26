package com.dcits.govsbu.sd.taxbankplatform.dkxx.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class Bankfinal extends BaseEntity {

        /**
	 * 
	 */
	private static final long serialVersionUID = 5780585918030066795L;

		private String finalid;
	    public String getFinalid() {
			return finalid;
		}

		public void setFinalid(String finalid) {
			this.finalid = finalid;
		}

		private String sqxh;

	    private String nsrsbh;

	    private String lfId;

	    private String larPdjg;

	    private String larCreditQuota;

	    private String larLoanDeadline;

	    private String larRate;

	    private String  rwId;

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

	   
	    public String getLarPdjg() {
	        return larPdjg;
	    }

	    public void setLarPdjg(String larPdjg) {
	        this.larPdjg = larPdjg;
	    }

	  


	    public String getLfId() {
			return lfId;
		}

		public void setLfId(String lfId) {
			this.lfId = lfId;
		}

		public String getLarCreditQuota() {
			return larCreditQuota;
		}

		public void setLarCreditQuota(String larCreditQuota) {
			this.larCreditQuota = larCreditQuota;
		}

		public String getLarLoanDeadline() {
			return larLoanDeadline;
		}

		public void setLarLoanDeadline(String larLoanDeadline) {
			this.larLoanDeadline = larLoanDeadline;
		}

		public String getLarRate() {
			return larRate;
		}

		public void setLarRate(String larRate) {
			this.larRate = larRate;
		}

	

	    public String getRwId() {
			return rwId;
		}

		public void setRwId(String rwId) {
			this.rwId = rwId;
		}

		public Date getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(Date createTime) {
	        this.createTime = createTime;
	    }

	
}


	


