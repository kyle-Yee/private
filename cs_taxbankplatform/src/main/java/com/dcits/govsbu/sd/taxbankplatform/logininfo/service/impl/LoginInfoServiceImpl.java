package com.dcits.govsbu.sd.taxbankplatform.logininfo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.logininfo.mapper.LoginInfoMapper;
import com.dcits.govsbu.sd.taxbankplatform.logininfo.model.LoginInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.logininfo.service.LoginInfoService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

/**
 * 
 * LoginInfoServiceImpl.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 登陆信息
 */
@Service("loginInfoService")
public class LoginInfoServiceImpl extends AbstractService<LoginInfoEntity, String> implements LoginInfoService{

	@Autowired
	private LoginInfoMapper loginInfoMapper;
	
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(loginInfoMapper);
	}

	@Override
	public int log(LoginInfoEntity loginInfo) {
		String l_id = IDGenerate.getZJID("XH");
		loginInfo.setL_id(l_id);
		return loginInfoMapper.insert(loginInfo);
	}

	@Override
	public void clearLogininfo() {
		loginInfoMapper.clear7daysBefore();
	}
}
