package com.dcits.govsbu.sd.taxbankplatform.organization.model;


import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.organizationstype.model.OrganizationsTypeEntity;
import com.dcits.govsbu.sd.taxbankplatform.regionclass.model.RegionclassEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;


/**
 * @author liwangxiong
 * @date 2016年8月4日
 * @caption 编码表
 */
public class OrganizationEntity extends BaseEntity{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	private String orgid;
	/*
	 *所属区域id
	 */
	private String regionid;
	/*
	 *组织名称
	 */
	private String orgname;
	/*
	 *组织类型id
	 */
	private String otid;
	/*
	 *编码code
	 */
	private String orgcode;
	/*
	 *组织logo地址
	 */
	private String orglogourl;
	/*
	 *备注
	 */
	private String remark;
	/*
	 *有效标志（Y:有效N:关闭）
	 */
	private String enabled;
	/*
	 *所属区域
	 */
	private RegionsEntity regionsEntity ;
	/*
	 *上级组织id
	 */
	private String upOrgId;
	/*
	 *区域所属级别
	 */
	private String rcid;
	/**
	 * 区域级别id
	 */
	private String pcid;
	/*
	 * 上级组织名称
	 */
	private String upname;
	/*
	 *所属组织
	 */
	private OrganizationsTypeEntity organizationsTypeEntity ;
	/*
	 * 所属区域名称
	 */
	
	private RegionclassEntity regionclassEntity;
	
	private OrganizationEntity organizationEntity;

	private String rzcid;
	private String sqcid;
	private String accredit;
	public String getAccredit() {
		return accredit;
	}
	public void setAccredit(String accredit) {
		this.accredit = accredit;
	}
	public String getRzcid() {
		return rzcid;
	}
	public void setRzcid(String rzcid) {
		this.rzcid = rzcid;
	}
	public String getSqcid() {
		return sqcid;
	}
	public void setSqcid(String sqcid) {
		this.sqcid = sqcid;
	}
	//--------set 、get 方法
	public String getUpname() {
		return upname;
	}
	public void setUpname(String upname) {
		this.upname = upname;
	}
	public String getUpOrgId() {
		return upOrgId;
	}
	public OrganizationEntity getOrganizationEntity() {
		return organizationEntity;
	}
	public void setOrganizationEntity(OrganizationEntity organizationEntity) {
		this.organizationEntity = organizationEntity;
	}
	public String getPcid() {
		return pcid;
	}
	public void setPcid(String pcid) {
		this.pcid = pcid;
	}
	public void setUpOrgId(String upOrgId) {
		this.upOrgId = upOrgId;
	}
	public String getRcid() {
		return rcid;
	}
	public void setRcid(String rcid) {
		this.rcid = rcid;
	}
	public String getRegionid() {
		return regionid;
	}
	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public RegionsEntity getRegionsEntity() {
		return regionsEntity;
	}
	public void setRegionsEntity(RegionsEntity regionsEntity) {
		this.regionsEntity = regionsEntity;
	}
	public String getOtid() {
		return otid;
	}
	public void setOtid(String otid) {
		this.otid = otid;
	}
	public OrganizationsTypeEntity getOrganizationsTypeEntity() {
		return organizationsTypeEntity;
	}
	public void setOrganizationsTypeEntity(
			OrganizationsTypeEntity organizationsTypeEntity) {
		this.organizationsTypeEntity = organizationsTypeEntity;
	}
	public String getOrglogourl() {
		return orglogourl;
	}
	public void setOrglogourl(String orglogourl) {
		this.orglogourl = orglogourl;
	}
	public RegionclassEntity getRegionclassEntity() {
		return regionclassEntity;
	}
	public void setRegionclassEntity(RegionclassEntity regionclassEntity) {
		this.regionclassEntity = regionclassEntity;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	
}
