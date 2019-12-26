package com.dcits.govsbu.sd.taxbankplatform.regions.model;


import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;


/**
 * @author liwangxiong
 * @date 2016年8月4日
 * @caption 编码表
 */
public class RegionsEntity extends BaseEntity{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/*
	 *开通区域名称
	 */
	private String regionname;
	/*
	 *开通区域code
	 */
	private String regioncode;
	/*
	 *税务机关名称 
	 */
	private String taxname;
	/*
	 *税务机关id 
	 */
	private String taxid;
	/*
	 *开通区域状态（0:开通	,1：关闭）
	 */
	private String enabled;
	/*
	 * 默认当前站点（0:默认 ,1:关闭）
	 */
	private String currentlocation;
	/*
	 *备注
	 */
	private String remark;
	/**
	 * 关联省市区代码表
	 */
	private ProvinceCitiesEntity provinceCitiesEntity;
	
	
	//--------set 、get 方法
	
	
	public ProvinceCitiesEntity getProvinceCitiesEntity() {
		return provinceCitiesEntity;
	}
	public void setProvinceCitiesEntity(ProvinceCitiesEntity provinceCitiesEntity) {
		this.provinceCitiesEntity = provinceCitiesEntity;
	}
	public String getRegionname() {
		return regionname;
	}
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	public String getCurrentlocation() {
		return currentlocation;
	}
	public void setCurrentlocation(String currentlocation) {
		this.currentlocation = currentlocation;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRegioncode() {
		return regioncode;
	}
	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}
	public String getTaxname() {
		return taxname;
	}
	public void setTaxname(String taxname) {
		this.taxname = taxname;
	}
	public String getTaxid() {
		return taxid;
	}
	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}
	
}
