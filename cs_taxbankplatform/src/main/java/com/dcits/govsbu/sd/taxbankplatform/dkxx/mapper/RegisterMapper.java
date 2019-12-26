package com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.RegisterEntity;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.model.RegisterModel;
@Repository 
public interface RegisterMapper extends BaseMapper<RegisterEntity, String> {


	public List<RegisterEntity> UserList(Map<String, Object> parameter);
	
	public int updatehtzt(Map<String, Object> parameter);
	
	public String searchYByNsrsbh(Map<String, Object> map);

	public List<RegisterModel> userLogin(String userName);

	public void insertUserInfo(RegisterModel register);

	public int insertUser(RegisterModel register);
	   
}
