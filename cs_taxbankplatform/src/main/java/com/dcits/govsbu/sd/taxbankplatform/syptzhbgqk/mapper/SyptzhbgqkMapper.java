package com.dcits.govsbu.sd.taxbankplatform.syptzhbgqk.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 
 * RoleMapper.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 角色
 */
@Repository
public interface SyptzhbgqkMapper {
	 
	public List<Map<String,Object>> queryListByPage(Map<String, Object> parameter);
 
	public List<Map<String,Object >> getHydm(); 
	public List<Map<String,Object >> getDjzclx(); 
}
