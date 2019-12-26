package com.east.microsilver.message.control;

import java.io.Serializable;
import java.util.List;

/**
 * 功能:
 * 内容控制
 * @author Administrator
 */
public class ContentControl implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<Control> control;
	
	
	public List<Control> getControl() {
		return control;
	}
	public void setControl(List<Control> control) {
		this.control = control;
	}
}
