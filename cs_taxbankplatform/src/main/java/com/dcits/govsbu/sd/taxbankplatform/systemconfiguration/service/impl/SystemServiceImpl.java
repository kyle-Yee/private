package com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.mapper.SystemMapper;
import com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.model.SystemEntity;
import com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.service.SystemService;

@Service("SystemService")
public class SystemServiceImpl extends AbstractService<SystemEntity, String> implements SystemService{
	@Autowired
	private SystemMapper systemMapper;
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(systemMapper);
	}
	@Override
	public SystemEntity queryFlagByXtcs(String xtcs){
		return systemMapper.queryFlagByXtcs(xtcs);
	}
	@Override
	//数据回滚测试
	public int insert(SystemEntity systemEntity){
		return systemMapper.insert(systemEntity);
	}
}
