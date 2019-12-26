/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.productfaq.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * @author 胡宝龙2016-8-11 下午3:06:42
 *
 */
public class ProductFaqEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String fpId;//对应产品的ID
    private String pfRemark;
    private String pfContent;
    
	public String getFpId() {
		return fpId;
	}
	public void setFpId(String fpId) {
		this.fpId = fpId;
	}
	public String getPfRemark() {
		return pfRemark;
	}
	public void setPfRemark(String pfRemark) {
		this.pfRemark = pfRemark;
	}
	public String getPfContent() {
		return pfContent;
	}
	public void setPfContent(String pfContent) {
		this.pfContent = pfContent;
	}
    
}
