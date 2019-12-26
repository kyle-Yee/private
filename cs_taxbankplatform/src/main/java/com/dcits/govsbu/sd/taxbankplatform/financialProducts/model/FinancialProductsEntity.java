/**
 * <p>Description: </p>
 * <p>versions:1.0 
 * <p>file name：FinancialProductsEntity.java
 * <p>Company:dfwyBank</p>
 * <p>@author: Zhongyj 
 * <p>Created: 2018-7-9下午上午7:48:352:57:43 
 * <p>department:深圳IT部门  
 * <p>Copyright Copyright (c) dfwy. All rights reserved.</p>
 */
package com.dcits.govsbu.sd.taxbankplatform.financialProducts.model;

import java.io.Serializable;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @versions:1.0 
 * @filename：FinancialProductsEntity.java
 * @Company:dfwyBank
 * @Created: 2018-7-9下午上午7:48:352:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName FinancialProductsEntity
 */
public class FinancialProductsEntity extends BaseEntity implements Serializable {
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/*************组织Id**************/
	private String orgId;
	/*************银行名称**************/
	private String yhmc;
	/**************银行代码*************/
	private String yhdm;
	/*************产品名称**************/
	private String cpmc;
	/*************产品代码**************/
	private String cpdm;
	/*************产品代码（老）**************/
	private String cpdmL;
	
	public String getCpdmL() {
		return cpdmL;
	}

	public void setCpdmL(String cpdmL) {
		this.cpdmL = cpdmL;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getYhdm() {
		return yhdm;
	}

	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

	public String getCpmc() {
		return cpmc;
	}

	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
	}

	public String getCpdm() {
		return cpdm;
	}

	public void setCpdm(String cpdm) {
		this.cpdm = cpdm;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}
