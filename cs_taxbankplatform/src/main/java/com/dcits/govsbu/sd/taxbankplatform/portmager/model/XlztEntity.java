/**
 * <p>Description: </p>
 * <p>versions:1.0 
 * <p>file name：XlztEntity.java
 * <p>Company:dfwyBank</p>
 * <p>@author: Zhongyj 
 * <p>Created: 2017-8-3下午下午2:40:032:57:43 
 * <p>department:深圳IT部门  
 * <p>Copyright Copyright (c) dfwy. All rights reserved.</p>
 */
package com.dcits.govsbu.sd.taxbankplatform.portmager.model;

import java.io.Serializable;

/**
 * @versions:1.0 
 * @filename：XlztEntity.java
 * @Company:dfwyBank
 * @Created: 2017-8-3下午下午2:40:032:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName XlztEntity
 */
public class XlztEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String xlztdm;
	
	private String xlztmc;
	
	private String yxbs;
	
	private String xybs;

	public String getXlztdm() {
		return xlztdm;
	}

	public void setXlztdm(String xlztdm) {
		this.xlztdm = xlztdm;
	}

	public String getXlztmc() {
		return xlztmc;
	}

	public void setXlztmc(String xlztmc) {
		this.xlztmc = xlztmc;
	}

	public String getYxbs() {
		return yxbs;
	}

	public void setYxbs(String yxbs) {
		this.yxbs = yxbs;
	}

	public String getXybs() {
		return xybs;
	}

	public void setXybs(String xybs) {
		this.xybs = xybs;
	}
	
}
