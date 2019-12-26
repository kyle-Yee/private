package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class PdfDataItemEntity extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tpId;

    private String tpfName;

    private Integer tpfTable;

    private Date createdtime;



    public String getTpId() {
		return tpId;
	}

	public void setTpId(String tpId) {
		this.tpId = tpId;
	}

	public String getTpfName() {
        return tpfName;
    }

    public void setTpfName(String tpfName) {
        this.tpfName = tpfName == null ? null : tpfName.trim();
    }

    public Integer getTpfTable() {
        return tpfTable;
    }

    public void setTpfTable(Integer tpfTable) {
        this.tpfTable = tpfTable;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}