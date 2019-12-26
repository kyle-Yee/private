package com.dcits.govsbu.sd.taxbankplatform.role.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.role.model.RoleEntity;

/**
 * 
 * RoleService.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 角色
 */
public interface RoleService {

	public List<RoleEntity> queryListByPage(Map<String, Object> parameter);

	public RoleEntity findByName(String name);
	
	public RoleEntity findByNameAndOwner(String name, String createUid);
	
	public int insert(RoleEntity roleEntity);
	
	public RoleEntity findById(String id);

	public int update(RoleEntity roleEntity);
    
    public int deleteBatchById(List<String> roleIds);
    
    public boolean addRolePerm(String id, List<String> ids) throws Exception ;
    
    // add by zhangmz 单独增加一条赋权记录
    public boolean addRolePermOne(String id, long resourcesId) throws Exception ;
    
    public RoleEntity findByOrgId(String orgid);
    
    public List<RoleEntity> findRoleQuery(Map<String, Object> parameter);
}