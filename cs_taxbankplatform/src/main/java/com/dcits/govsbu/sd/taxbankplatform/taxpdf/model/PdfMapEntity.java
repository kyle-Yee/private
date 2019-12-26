package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import java.util.Date;
import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class PdfMapEntity extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tpName;

    private Integer tpRow;

    private Integer tpTable;

    private Date createdtime;
    
    private List<PdfDataItemEntity> pdfDataItemList ;

    private String tpfName;
    
    private String tpfTable;
    
    public List<PdfDataItemEntity> getPdfDataItemList() {
		return pdfDataItemList;
	}

	public void setPdfDataItemList(List<PdfDataItemEntity> pdfDataItemList) {
		this.pdfDataItemList = pdfDataItemList;
	}

    public String getTpName() {
        return tpName;
    }

    public void setTpName(String tpName) {
        this.tpName = tpName == null ? null : tpName.trim();
    }

    public Integer getTpRow() {
        return tpRow;
    }

    public void setTpRow(Integer tpRow) {
        this.tpRow = tpRow;
    }

    public Integer getTpTable() {
        return tpTable;
    }

    public void setTpTable(Integer tpTable) {
        this.tpTable = tpTable;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

	public String getTpfName() {
		return tpfName;
	}

	public void setTpfName(String tpfName) {
		this.tpfName = tpfName;
	}

	public String getTpfTable() {
		return tpfTable;
	}

	public void setTpfTable(String tpfTable) {
		this.tpfTable = tpfTable;
	}
    
}