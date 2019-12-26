/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.messageType.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @author 胡宝龙2016-8-22 上午11:24:16
 *
 */
public class MessageType extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String typeName;
	private String enabled;
	
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
