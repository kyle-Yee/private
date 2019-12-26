package com.east.microsilver.message.control;

import java.io.Serializable;

/**
 * 功能:
 * 内容控制
 * @author Administrator
 */
public class Control implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String type;
	private String impl;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getImpl() {
		return impl;
	}
	public void setImpl(String impl) {
		this.impl = impl;
	}
}
