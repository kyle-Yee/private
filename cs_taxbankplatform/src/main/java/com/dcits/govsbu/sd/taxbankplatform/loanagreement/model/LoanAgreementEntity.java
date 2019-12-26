/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.loanagreement.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

/**
 * @author 胡宝龙2016-8-18 下午9:08:52
 *
 */
public class LoanAgreementEntity extends BaseEntity {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private String laName;	//协议名称
	private String laContent;	//协议内容
	private String orgId; //银行ID
	private String regionId;	//城市ID
	private String enabled;		//有效标志(Y/N)
	private String orgName;	//所属银行
    private String regionName;	//所属城市
    private String laxyid;  //协议类型
	public String getLaxyid() {
		return laxyid;
	}
	public void setLaxyid(String laxyid) {
		this.laxyid = laxyid;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getLaName() {
		return laName;
	}
	public void setLaName(String laName) {
		this.laName = laName;
	}
	public String getLaContent() {
		return laContent;
	}
	public void setLaContent(String laContent) {
		this.laContent = laContent;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}
