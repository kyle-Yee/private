package com.dcits.govsbu.sd.taxbankplatform.dkxx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper.RegisterMapper;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.RegisterEntity;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.RegisterService;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.model.RegisterModel;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

@Service("registerService")
public class RegisterServiceImpl extends AbstractService<RegisterEntity, String> implements RegisterService{
	@Autowired
	private RegisterMapper registerMapper;
	



	public RegisterMapper getRegisterMapper() {
		return registerMapper;
	}




	public void setRegisterMapper(RegisterMapper registerMapper) {
		this.registerMapper = registerMapper;
	}




	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(registerMapper);
	}




	@Override
	public List<RegisterEntity> UserList(Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return registerMapper.UserList(parameter);
	}




	@Override
	public int updatehtzt(Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return registerMapper.updatehtzt(parameter);
	}


	@Override
	public List<RegisterModel> userLogin(String userName) {
		//账户登录
		List<RegisterModel> registerEntity = registerMapper.userLogin(userName);
		return registerEntity;
	}


	@Override
	public String searchYByNsrsbh(Map<String, Object> map) {
		return registerMapper.searchYByNsrsbh(map);
	}




	@Override
	public int insertUser(RegisterModel register) {
		//将信息插入nsryhxx表
		register.setNsryhxxId(IDGenerate.getZJID("QYXZX"));
		register.setUserId(IDGenerate.getZJID("QYUZX"));
		registerMapper.insertUserInfo(register);
		//插入数据到nsryh表
		return registerMapper.insertUser(register);
	}

	
}
