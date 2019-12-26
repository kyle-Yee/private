package com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class InterfaceLogEntity  extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String il_id;
	/**
	 * 编号
	 */
	private String bh;
	/**
	 * 纳税人识别号
	 */
	private String nsrsbh;
	/**
	 * 接口名称
	 */
	private String interfaceName;
	/**
	 * 接口报错信息
	 */
	private String errorLog;
	/**
	 * 接口内容
	 */
	private String content;
	public String getIl_id() {
		return il_id;
	}
	public void setIl_id(String il_id) {
		this.il_id = il_id;
	}
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getErrorLog() {
		return errorLog;
	}
	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
