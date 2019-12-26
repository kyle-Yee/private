package com.dcits.govsbu.sd.taxbankplatform.auth.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dcits.govsbu.sd.taxbankplatform.auth.model.AuthResponseEntity;
import com.dcits.govsbu.sd.taxbankplatform.auth.service.AuthService;
import com.dcits.govsbu.sd.taxbankplatform.util.RequestManager;

/**
 * 
 * @author dms
 * @date 2019年1月16日
 * @Description:认证授权接口 (五要素比对)
 */

@Controller
@Scope("prototype")
public class AuthController {

	@Autowired
	private AuthService authService;

	@ResponseBody
	@RequestMapping("/api/auth")
	public AuthResponseEntity authorizationRecord() {
		// 根据传递的五要素去数据库进行比对
		HashMap<String, Object> params = RequestManager.getParams();
		return authService.auth(params);
	}

}
