/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.loanagreement.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @author yaofang *
 */
public class AgreementkvEntity extends BaseEntity {
	/**
	 * 键值对
	 */
	private static final long serialVersionUID = 1L;
	private String xykey;	//协议键
	private String xyvalue;	//协议值
	public String getXykey() {
		return xykey;
	}
	@Override
	public String toString() {
		return "AgreementkvEntity [xykey=" + xykey + ", xyvalue=" + xyvalue
				+ "]";
	}
	public void setXykey(String xykey) {
		this.xykey = xykey;
	}
	public String getXyvalue() {
		return xyvalue;
	}
	public void setXyvalue(String xyvalue) {
		this.xyvalue = xyvalue;
	}
    public AgreementkvEntity(String k, String v)  
    {  
    	xykey = k;  
    	xyvalue = v;  
    } 
 
}
