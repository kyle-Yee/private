package com.east.microsilver.message.content;

import java.io.Serializable;
import java.util.List;

/**
 * 功能:
 * 描述业务请求响应报文中的业务内容
 * @author Administrator
 */
public class SubPackage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String content;
	private List<?> paramList;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public List<?> getParamList() {
		return paramList;
	}
	public void setParamList(List<?> paramList) {
		this.paramList = paramList;
	}
}
