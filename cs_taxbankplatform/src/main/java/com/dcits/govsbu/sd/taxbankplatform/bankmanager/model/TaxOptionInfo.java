package com.dcits.govsbu.sd.taxbankplatform.bankmanager.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class TaxOptionInfo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pkey;			//序号
	
	private String tableName;		//表名
	
	private String dataHeader;		//表名称
	
	private String fieldName;		//数据项名称
	
	private String sign;			//标识  0-非实质性内容，1-实质性内容，2-其他（时间口径）
	
	private String selected;		//是否被选中
	
	private int fieldCounter;		//总条数

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDataHeader() {
		return dataHeader;
	}

	public void setDataHeader(String dataHeader) {
		this.dataHeader = dataHeader;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public int getFieldCounter() {
		return fieldCounter;
	}

	public void setFieldCounter(int fieldCounter) {
		this.fieldCounter = fieldCounter;
	}

}
