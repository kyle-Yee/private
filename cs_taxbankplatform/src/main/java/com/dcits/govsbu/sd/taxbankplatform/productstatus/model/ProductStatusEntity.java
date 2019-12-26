/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.productstatus.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @author 胡宝龙2016-8-11 上午10:57:33
 * 金融产品状态表
 */
public class ProductStatusEntity  extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String productStatusName; //产品状态名
	private String code; //产品code
	private String enabled;		//有效标志(Y/N)
	
	public String getProductStatusName() {
		return productStatusName;
	}
	public void setProductStatusName(String productStatusName) {
		this.productStatusName = productStatusName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}
