package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @versions:1.0 
 * @filename：SxsjxEntity.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午11:01:312:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName SxsjxEntity
 */
public class SxsjxEntity extends BaseEntity {

	
		/**  描述   贷款授信页面所需的数据项 */      
	    
	private static final long serialVersionUID = 1L;
	private String name;//数据项的名称
	private String values;//数据项的值
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
}
