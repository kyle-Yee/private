package com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.service;

import com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.model.SystemEntity;
/**
 * 
 * @author Administrator
 *
 */
public interface SystemService {
	//根据系统参数查询开关表示
	public SystemEntity queryFlagByXtcs(String xtcs);
	//数据回滚测试
	public int insert(SystemEntity systemEntity);
}
