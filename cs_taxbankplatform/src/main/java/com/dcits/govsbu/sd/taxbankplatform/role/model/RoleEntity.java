package com.dcits.govsbu.sd.taxbankplatform.role.model;

import java.util.Date;
import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;

/**
 * 
 * RoleEntity.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 角色
 */
public class RoleEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 区域id
	 */
	private String regionid;
	/*
	 * 组织id
	 */
	private String orgid;
	/*
	 * 角色名
	 */
	private String name;
	
	private String key;
	/*
	 * 状态
	 */
	private String status;

	private String description;

	private String createUid;

	private Date createTime;

	private Date updateTime;
	
	private RegionsEntity regionsEntity;
	
	private OrganizationEntity organizationEntity;
	
	private List<UserEntity> userList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCreateUid() {
		return createUid;
	}

	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<UserEntity> getUserList() {
		return userList;
	}

	public void setUserList(List<UserEntity> userList) {
		this.userList = userList;
	}
	
	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public RegionsEntity getRegionsEntity() {
		return regionsEntity;
	}

	public void setRegionsEntity(RegionsEntity regionsEntity) {
		this.regionsEntity = regionsEntity;
	}

	public OrganizationEntity getOrganizationEntity() {
		return organizationEntity;
	}

	public void setOrganizationEntity(OrganizationEntity organizationEntity) {
		this.organizationEntity = organizationEntity;
	}
	
	public String getRegionid() {
		return regionid;
	}

	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", name=" + name + ", key=" + key 
				+ ", status=" + status + ", description=" + description 
				+ ", createUid=" + createUid + ", createTime=" + createTime 
				+ ", updateTime=" + updateTime + ", userList=" + userList 
				+ "]";
	}

}