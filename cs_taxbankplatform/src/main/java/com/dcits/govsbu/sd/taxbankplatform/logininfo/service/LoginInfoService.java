package com.dcits.govsbu.sd.taxbankplatform.logininfo.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.logininfo.model.LoginInfoEntity;

/**
 * 
 * @versions:1.0 
 * @filename：LoginInfoService.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午10:18:032:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName LoginInfoService
 */
public interface LoginInfoService {

	public int log(LoginInfoEntity loginInfo);
	
	public List<LoginInfoEntity> queryListByPage(Map<String, Object> parameter);
	
	// 删除七天前的记录
	public void clearLogininfo();
}
