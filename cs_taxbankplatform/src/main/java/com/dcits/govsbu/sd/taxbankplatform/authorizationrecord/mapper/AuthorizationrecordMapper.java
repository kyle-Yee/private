package com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model.AuthorizationrecordEntity;
import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;

@Repository
public interface AuthorizationrecordMapper extends BaseMapper<AuthorizationrecordEntity, String > {
	
	 public List<AuthorizationrecordEntity> querySqByPage(Map<String, Object> parameter);
	 public int updateSQxx(Map<String, Object> parameter);
	 public void insert(Map<String , Object>params);
	 /**
	  * 授权编码查询授权信息
	  * */
	 public AuthorizationrecordEntity findByGrantCode(@Param("grantCode")String grantCode);

}
