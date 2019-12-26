package com.dcits.govsbu.sd.taxbankplatform.swsjcxtj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * RoleMapper.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 角色
 */
@Repository
public interface SwsjcxtjMapper {
	 
	public List<Map<String,Object>> queryListByPage(Map<String, Object> parameter);
	public List<Map<String,Object>> getDataRecord(Map<String,String> sqxh);
	public List<Map<String,Object>> getBData(@Param(value="sql")String sql);
	
 
}
