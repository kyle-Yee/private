package com.dcits.govsbu.sd.taxbankplatform.loanapprove.model;

import java.io.Serializable;

/**
 * 存放申请状态
 * @versions:1.0 
 * @filename：ApplyStatus.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午11:18:342:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName ApplyStatus
 */
public class ApplyStatus implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String status;				//申请状态

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
