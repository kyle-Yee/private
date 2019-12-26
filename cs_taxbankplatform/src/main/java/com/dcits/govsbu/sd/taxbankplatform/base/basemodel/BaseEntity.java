package com.dcits.govsbu.sd.taxbankplatform.base.basemodel;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * BaseEntity.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 统一定义id的BaseEntity基类,基类统一定义id的属性名称、数据类型.
 *          子类可重载getId()函数.
 */
public abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	/*
	 * id
	 */
	protected String id;
	
	protected String creatorid;
	
	protected Date createtime;
	
	protected String updatorid;
	
	protected Date updatetime;
	
	protected int indexNo;//用于表格序号显示

	public int getIndexNo() {
		return indexNo;
	}

	public void setIndexNo(int indexNo) {
		this.indexNo = indexNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUpdatorid() {
		return updatorid;
	}

	public void setUpdatorid(String updatorid) {
		this.updatorid = updatorid;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	
}
