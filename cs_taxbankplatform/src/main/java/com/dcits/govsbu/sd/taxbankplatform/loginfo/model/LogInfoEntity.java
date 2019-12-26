package com.dcits.govsbu.sd.taxbankplatform.loginfo.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * 
 * LogInfoEntity.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 审计用的用户操作信息
 */
public class LogInfoEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String l_id;
	
	/*
	 * 管理员id
	 */
	private String userName;
	/*
	 * 管理员id
	 */
	private String userId;
	/*
	 * 账户名称
	 */
	private String accountName;
	/*
	 * 日期
	 */
	private Date createTime;
	/*
	 * 日志内容
	 */
	private String content; 
	/*
	 * 操作(主要是"添加"、"修改"、"删除")
	 */
	private String operation;

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getL_id() {
		return l_id;
	}

	public void setL_id(String l_id) {
		this.l_id = l_id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
