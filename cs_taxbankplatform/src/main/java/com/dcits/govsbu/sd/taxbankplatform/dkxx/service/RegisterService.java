package com.dcits.govsbu.sd.taxbankplatform.dkxx.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.RegisterEntity;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.model.RegisterModel;



public interface RegisterService {
	
	public List<RegisterEntity> UserList(Map<String, Object> parameter);
	
	public int updatehtzt(Map<String, Object> parameter);
	
	public String searchYByNsrsbh(Map<String, Object> map);

	public List<RegisterModel> userLogin(String userName);

	public int insertUser(RegisterModel register);
	   
}
