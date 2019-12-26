package com.dcits.govsbu.sd.taxbankplatform.portmager.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.portmager.model.PortmagerEntity;
import com.dcits.govsbu.sd.taxbankplatform.portmager.model.XlztEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;

/**
 * 
 * @versions:1.0
 * @filename：PortmagerMapper.java
 * @Company:dfwyBank
 * @Created: 2017-8-2下午下午6:33:402:57:43
 * @department:深圳IT部门
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName PortmagerMapper
 */
@Repository
public interface PortmagerMapper extends BaseMapper<PortmagerEntity, String> {
	
	/***获取用户组织名称***/
	public List<OrganizationEntity> findOrgNameList(String regionId);
	
	/***根据orgName查找对应的orgcode***/
	public String findOrgCodeByOrgName(String orgName);
	
	/***线路开通状态的码表信息***/
	public List<XlztEntity> findXlztList();
	
	/***根据银行名称判断是否已经添加过了该银行***/
	public List<PortmagerEntity> findByYhmc(String yhmc);
	
	/***更新银行端口接入管理的列表数据***/
	public int update(Map<String,Object> paramaters);
	
	/***修改银行端口接入管理的数据***/
	public int updateRecord(PortmagerEntity portmagerEntity) throws Exception;
	/***查询银行所有的数据***/
	public List<PortmagerEntity> queryBank();
}
