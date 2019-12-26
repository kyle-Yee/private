package com.dcits.govsbu.sd.taxbankplatform.auth.service;

import java.util.HashMap;

import com.dcits.govsbu.sd.taxbankplatform.auth.model.AuthResponseEntity;

/**
 *
	* @author dms
	* @date 2019年1月16日
	* @Description: 用户认证Service接口定义
 */

public interface AuthService {
	
	//认证接口
	AuthResponseEntity auth(HashMap<String, Object> params);
	
}
