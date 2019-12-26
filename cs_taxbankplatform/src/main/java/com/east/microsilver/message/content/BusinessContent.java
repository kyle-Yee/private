package com.east.microsilver.message.content;

import java.io.Serializable;

/**
 * 功能:
 * 业务内容
 * @author Administrator
 */
public class BusinessContent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private SubPackage subPackage;						//业务内容
	
	
	public SubPackage getSubPackage() {
		return subPackage;
	}
	public void setSubPackage(SubPackage subPackage) {
		this.subPackage = subPackage;
	}
}