package com.dcits.govsbu.sd.taxbankplatform.user.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.role.model.RoleEntity;

/**
 * 
 * UserEntity.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 用户
 */
public class UserEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String u_id;
	/*
	 * 用户真实姓名
	 */
	private String userName;
	/*
	 * 这里账户名称统一使用邮箱
	 */
	private String accountName;
	
	private String password;
	/**
	 * 组织id
	 */
	private String orgid;
	
	private Integer deleteStatus;
	
	private Integer locked;
	
	private String description;
	/*
	 * 加密盐
	 */
	private String credentialsSalt;
	
	private String creatorId;
	
	private String creatorName;
	
	private Date createTime;
	
	private Date updateTime;
	/*
	 * 所属角色
	 */
	private RoleEntity role;
	
	private String regionid;
	
	private String ssswjDm;
	
	private RegionsEntity regionsEntity;
	
	private OrganizationEntity organizationEntity;
	/*
	 * 个人资料信息
	 */
	private UserInfoEntity userInfo;

	
	
	public String getSsswjDm() {
		return ssswjDm;
	}

	public void setSsswjDm(String ssswjDm) {
		this.ssswjDm = ssswjDm;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCredentialsSalt() {
		return credentialsSalt;
	}

	public void setCredentialsSalt(String credentialsSalt) {
		this.credentialsSalt = credentialsSalt;
	}
	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
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

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}
	
	public UserInfoEntity getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoEntity userInfo) {
		this.userInfo = userInfo;
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

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	@Override
	public String toString() {
		return "UserEntity [id="+ id +", userName=" + userName + ", accountName="
				+ accountName + ", password=" + password + ", deleteStatus="
				+ deleteStatus + ", locked=" + locked + ", description="
				+ description + ", credentialsSalt=" + credentialsSalt
				+ ", creatorId=" + creatorId + ", creatorName=" + creatorName 
				+ ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", role=" + role + "]";
	}
}
