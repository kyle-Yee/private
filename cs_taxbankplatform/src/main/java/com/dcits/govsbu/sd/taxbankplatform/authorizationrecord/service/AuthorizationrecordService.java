package com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model.AuthorizationrecordEntity;

public interface AuthorizationrecordService {
	public List<AuthorizationrecordEntity> queryListByPage(Map<String, Object> parameter);
	
	public AuthorizationrecordEntity findById(String id);
	 public List<AuthorizationrecordEntity> querySqByPage(Map<String, Object> parameter);
	 public int updateSQxx(Map<String, Object> parameter);
	 public void insert(String tableName, Map<String, Object> params);
	 /**
	  * 授权编码查询授权信息
	  * */
	 public AuthorizationrecordEntity findByGrantCode(String grantCode) throws Exception;
}
