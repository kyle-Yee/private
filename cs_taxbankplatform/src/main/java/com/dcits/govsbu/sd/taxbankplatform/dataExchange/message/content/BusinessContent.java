package com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.content;

import java.io.Serializable;
import java.util.List;

/**
 * 功能:
 * 业务内容
 * @author Administrator
 */
public class BusinessContent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<SubPackage> subPackage;						//业务内容
	
	
	public List<SubPackage> getSubPackage() {
		return subPackage;
	}
	public void setSubPackage(List<SubPackage> subPackage) {
		this.subPackage = subPackage;
	}
}