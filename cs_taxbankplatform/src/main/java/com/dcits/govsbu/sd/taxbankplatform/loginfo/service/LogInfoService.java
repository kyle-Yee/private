package com.dcits.govsbu.sd.taxbankplatform.loginfo.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.loginfo.model.LogInfoEntity;

/**
 * 
 * @versions:1.0 
 * @filename：LogInfoService.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午10:12:402:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName LogInfoService
 */
public interface LogInfoService {

	public int log(LogInfoEntity logInfo);
	
	public List<LogInfoEntity> queryListByPage(Map<String, Object> parameter);
	
	// 删除七天前的记录
	public void clearLoginfo();
}
