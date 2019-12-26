package com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.model.SystemEntity;
@Repository
public interface SystemMapper extends BaseMapper<SystemEntity, String>{
	//根据系统参数查询开关表示
	public SystemEntity queryFlagByXtcs(String xtcs);
	//数据回滚测试
	@Override
	public int insert(SystemEntity systemEntity);
}
