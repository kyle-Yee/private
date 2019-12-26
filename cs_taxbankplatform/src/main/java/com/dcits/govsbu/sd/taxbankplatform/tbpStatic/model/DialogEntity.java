package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class DialogEntity extends BaseEntity {

	/**
	 * 对话表（针对提问Question）
	 */
	private static final long serialVersionUID = 1L;
	
	private String dia_id;//对话id
	
	private String f_id;//反馈id
	
	private String f_content;//对话内容
	
	private int type;//对话类型 1，管理员回复   2，企业端追问
	
	private String createName;//创建者名称
	
	public String getCreateName() {
		return createName;
	}


	public void setCreateName(String createName) {
		this.createName = createName;
	}


	public String getDia_id() {
		return dia_id;
	}


	public void setDia_id(String dia_id) {
		this.dia_id = dia_id;
	}


	public String getF_id() {
		return f_id;
	}


	public void setF_id(String f_id) {
		this.f_id = f_id;
	}


	public String getF_content() {
		return f_content;
	}


	public void setF_content(String f_content) {
		this.f_content = f_content;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
