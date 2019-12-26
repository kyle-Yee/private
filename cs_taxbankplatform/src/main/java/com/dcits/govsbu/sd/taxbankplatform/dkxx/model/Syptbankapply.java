package com.dcits.govsbu.sd.taxbankplatform.dkxx.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class Syptbankapply extends BaseEntity {

	    /**
	 * 
	 */
	private static final long serialVersionUID = 59966752415020590L;

		private String laId;

	    private String lfId;
	    
	    private String syptfpId;
	    
	    private String bankfpId;
        
        private String templaId;
	    
	    private String tempfinalId;
	    
	    private String tempendId;
	    
	    //add
	    private String syptUserId;
	    private String nsrsbh;

	    
	    public String getSyptUserId() {
			return syptUserId;
		}

		public void setSyptUserId(String syptUserId) {
			this.syptUserId = syptUserId;
		}

		public String getNsrsbh() {
			return nsrsbh;
		}

		public void setNsrsbh(String nsrsbh) {
			this.nsrsbh = nsrsbh;
		}

		

		public String getTemplaId() {
			return templaId;
		}

		public void setTemplaId(String templaId) {
			this.templaId = templaId;
		}

		public String getTempfinalId() {
			return tempfinalId;
		}

		public void setTempfinalId(String tempfinalId) {
			this.tempfinalId = tempfinalId;
		}

		public String getTempendId() {
			return tempendId;
		}

		public void setTempendId(String tempendId) {
			this.tempendId = tempendId;
		}

		public String getSyptfpId() {
			return syptfpId;
		}

		public void setSyptfpId(String syptfpId) {
			this.syptfpId = syptfpId;
		}

		public String getBankfpId() {
			return bankfpId;
		}

		public void setBankfpId(String bankfpId) {
			this.bankfpId = bankfpId;
		}

		private String channelid;

		

	

		public String getLaId() {
			return laId;
		}

		public void setLaId(String laId) {
			this.laId = laId;
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

	 

	 

	
}


	


