package com.dcits.govsbu.sd.taxbankplatform.portmager.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.portmager.model.PortmagerEntity;
import com.dcits.govsbu.sd.taxbankplatform.portmager.model.XlztEntity;

/**
 * 
 * @versions:1.0 
 * @filename：PortmagerService.java
 * @Company:dfwyBank
 * @Created: 2017-8-2下午下午6:34:152:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName PortmagerService
 */
public interface PortmagerService {
	
	/***将添加的银行端口管理信息保存到数据库中***/
	int insert(PortmagerEntity portmagerEntity) throws Exception;
	
	/***根据选择的当前列表的行代码查找该行在数据库中具体的信息***/
	public PortmagerEntity findById(String id) throws Exception;
	
	/***查询银行端口接入管理的所以列表数据***/
	public List<PortmagerEntity> queryListByPage(Map<String, Object> parameters) throws Exception;
	
	/***更新银行端口接入管理的所以列表数据***/
	public int update(Map<String,Object> paramaters) throws Exception;
	
	/***获取用户组织名称***/
	public List<OrganizationEntity> findOrgNameList(String regionId) throws Exception;
	
	/***根据orgName查找对应的orgcode***/
	public String findOrgCodeByOrgName(String orgName) throws Exception;
	
	/***线路开通状态的码表信息***/
	public List<XlztEntity> findXlztList() throws Exception;
	
	/***根据银行名称判断是否已经添加过了该银行***/
	public List<PortmagerEntity> findByYhmc(String yhmc) throws Exception;
	
	/***修改银行端口接入管理的数据***/
	public int updateRecord(PortmagerEntity portmagerEntity) throws Exception;
	/***查询银行所有的数据***/
	public List<PortmagerEntity> queryBank() throws Exception;
}
