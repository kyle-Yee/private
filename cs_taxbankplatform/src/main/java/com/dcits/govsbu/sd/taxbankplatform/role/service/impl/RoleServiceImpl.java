package com.dcits.govsbu.sd.taxbankplatform.role.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.role.mapper.RoleMapper;
import com.dcits.govsbu.sd.taxbankplatform.role.model.RoleEntity;
import com.dcits.govsbu.sd.taxbankplatform.role.service.RoleService;

/**
 * 
 * RoleServiceImpl.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 角色
 */
@Service("roleService")
public class RoleServiceImpl extends AbstractService<RoleEntity, String>
		implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(roleMapper);
	}

	@Override
	public boolean addRolePerm(String id, List<String> ids){
		boolean flag = false;
		try
		{
			int permCount = roleMapper.findRoleResourceById(id);
			boolean delFlag = true;
			if(permCount > 0)
			{
				int delResult = roleMapper.deleteRoleResource(id);
				if(permCount != delResult)
				{
					delFlag = false;
				}
			}
			
			if (delFlag) {
				if(ids.size() > 0)
				{
					Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("roleId", id);
					parameter.put("resourceIds", ids);
					int addResult = roleMapper.addRoleResource(parameter);
					if (addResult == ids.size()) {
						flag = true;
					}
				}else
				{
					flag = true;
				}
			}
			return flag;
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean addRolePermOne(String id, long resourcesId){
		boolean flag = false;
		try
		{	
			List<Long> ids = new ArrayList<>();
			ids.add(resourcesId);
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("roleId", id);
			parameter.put("resourceIds", ids);
			int addResult = roleMapper.addRoleResource(parameter);
			if (addResult == ids.size()) {
				flag = true;
			}
			return flag;
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}
	
	@Override
	public RoleEntity findByNameAndOwner(String name, String createUid) {
		return roleMapper.findByNameAndOwner(name, createUid);
	}
	
	
	@Override
	public RoleEntity findByOrgId(String orgid){
		return roleMapper.findByOrgId(orgid);
	}
	
	@Override
	public List<RoleEntity> findRoleQuery(Map<String, Object> parameter){
		return roleMapper.findRoleQuery(parameter);
	}
}
