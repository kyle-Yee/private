package com.dcits.govsbu.sd.taxbankplatform.logininfo.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.logininfo.model.LoginInfoEntity;

/**
 * 
 * LoginInfoMapper.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 登陆信息
 */
@Repository
public interface LoginInfoMapper extends BaseMapper<LoginInfoEntity, String> {

	// 删除七天前的记录
	public void clear7daysBefore() ;
}
