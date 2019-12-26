package com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.mapper.AuthorizationrecordMapper;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model.AuthorizationrecordEntity;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.service.AuthorizationrecordService;
import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
@Service("authorizationrecordService")
public class AuthorizationrecordServiceImpl  extends AbstractService<AuthorizationrecordEntity, String> implements AuthorizationrecordService{
	@Autowired
	private AuthorizationrecordMapper authorizationrecordMapper;
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(authorizationrecordMapper);
	}
	@Override
	public List<AuthorizationrecordEntity> querySqByPage(
			Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return authorizationrecordMapper.querySqByPage(parameter);
	}
	@Override
	public int updateSQxx(Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return authorizationrecordMapper.updateSQxx(parameter);
	}
	
	 /**
	  * 授权编码查询授权信息
	  * */
	 public AuthorizationrecordEntity findByGrantCode(String grantCode) throws Exception{
		 return authorizationrecordMapper.findByGrantCode(grantCode);
	 }
	// 插入数据到指定表
		public void insert(String tableName, Map<String, Object> params) {

			String[] keys = new String[params.size()];
			Set<String> sset = params.keySet();
			int i = 0;
			for (String os : sset) {
				keys[i++] = os;
			}

			String[] keys2 = new String[params.size()];
			Set<String> sset2 = params.keySet();
			i = 0;
			for (String os : sset2) {
				keys2[i++] = os;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tablename", tableName);
			map.put("keys", keys);
			map.put("params", params);
			authorizationrecordMapper.insert(map);
		}
}
