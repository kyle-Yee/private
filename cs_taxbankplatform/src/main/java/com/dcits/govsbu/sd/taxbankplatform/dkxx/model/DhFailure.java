package com.dcits.govsbu.sd.taxbankplatform.dkxx.model;

import java.util.Date;

public class DhFailure {
    private String businessid;

    private String indname;

    private String indcerttype;

    private String indcertid;

    private String entname;

    private String entcreditid;

    private String entregid;

    private String enttaxid;

    private String approvedate;

    private String cjsj;
    
    private String failureId;
    
    private String enabled;
    
    private String channelid;
    
  
	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid == null ? null : businessid.trim();
    }

    public String getIndname() {
        return indname;
    }

    public void setIndname(String indname) {
        this.indname = indname == null ? null : indname.trim();
    }

    public String getIndcerttype() {
        return indcerttype;
    }

    public void setIndcerttype(String indcerttype) {
        this.indcerttype = indcerttype == null ? null : indcerttype.trim();
    }

    public String getIndcertid() {
        return indcertid;
    }

    public void setIndcertid(String indcertid) {
        this.indcertid = indcertid == null ? null : indcertid.trim();
    }

    public String getEntname() {
        return entname;
    }

    public void setEntname(String entname) {
        this.entname = entname == null ? null : entname.trim();
    }

    public String getEntcreditid() {
        return entcreditid;
    }

    public void setEntcreditid(String entcreditid) {
        this.entcreditid = entcreditid == null ? null : entcreditid.trim();
    }

    public String getEntregid() {
        return entregid;
    }

    public void setEntregid(String entregid) {
        this.entregid = entregid == null ? null : entregid.trim();
    }

    public String getEnttaxid() {
        return enttaxid;
    }

    public void setEnttaxid(String enttaxid) {
        this.enttaxid = enttaxid == null ? null : enttaxid.trim();
    }

    public String getApprovedate() {
        return approvedate;
    }

    public void setApprovedate(String approvedate) {
        this.approvedate = approvedate == null ? null : approvedate.trim();
    }
    
   

	public String getFailureId() {
		return failureId;
	}

	public void setFailureId(String failureId) {
		this.failureId = failureId;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

   
}