package com.dcits.govsbu.sd.taxbankplatform.regionclass.model;

import java.io.Serializable;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * 
 * @author 谢翠
 * @date 2016年11月30日
 * @caption 区域级别
 * 
 */
public class RegionclassEntity extends BaseEntity implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	//区域级别名称
	private String rcName;
	//描述
	private String rcDescription;
	
	public String getRcName() {
		return rcName;
	}
	public void setRcName(String rcName) {
		this.rcName = rcName;
	}
	public String getRcDescription() {
		return rcDescription;
	}
	public void setRcDescription(String rcDescription) {
		this.rcDescription = rcDescription;
	}
	
}
