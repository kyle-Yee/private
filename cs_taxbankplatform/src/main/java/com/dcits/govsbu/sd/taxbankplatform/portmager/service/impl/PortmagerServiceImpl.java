package com.dcits.govsbu.sd.taxbankplatform.portmager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.portmager.mapper.PortmagerMapper;
import com.dcits.govsbu.sd.taxbankplatform.portmager.model.PortmagerEntity;
import com.dcits.govsbu.sd.taxbankplatform.portmager.model.XlztEntity;
import com.dcits.govsbu.sd.taxbankplatform.portmager.service.PortmagerService;
/**
 * 
 * @versions:1.0 
 * @filename：PortmagerServiceImpl.java
 * @Company:dfwyBank
 * @Created: 2017-8-2下午下午6:34:022:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName PortmagerServiceImpl
 */
@Service("portmagerService")
public class PortmagerServiceImpl extends AbstractService<PortmagerEntity, String>  implements PortmagerService{

	@Autowired
	private PortmagerMapper portmagerMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(portmagerMapper);
	}

	@Override
	public List<OrganizationEntity> findOrgNameList(String regionId) throws Exception {
		return portmagerMapper.findOrgNameList(regionId);
	}

	@Override
	public String findOrgCodeByOrgName(String orgName) throws Exception {
		return portmagerMapper.findOrgCodeByOrgName(orgName);
	}

	@Override
	public List<XlztEntity> findXlztList() throws Exception {
		return portmagerMapper.findXlztList();
	}

	@Override
	public List<PortmagerEntity> findByYhmc(String yhmc) throws Exception {
		return portmagerMapper.findByYhmc(yhmc);
	}

	@Override
	public List<PortmagerEntity> queryBank() throws Exception {
		return portmagerMapper.queryBank();
	}
	@Override
	public int update(Map<String, Object> paramaters) throws Exception {
		return portmagerMapper.update(paramaters);
	}

	@Override
	public int updateRecord(PortmagerEntity portmagerEntity) throws Exception {
		return portmagerMapper.updateRecord(portmagerEntity);
	}

}
