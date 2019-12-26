package com.dcits.govsbu.sd.taxbankplatform.organizationstype.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organizationstype.model.OrganizationsTypeEntity;

/**
 * OrganizationsTypeService.java
 * @author 严添麟
 * @date 2016年8月19日
 * @caption 组织类型代码查询
 */
public interface OrganizationsTypeService {
	
	public List<OrganizationsTypeEntity> queryListAll(Map<String, Object> parameter);
	
	public OrganizationsTypeEntity findById(String id);
	
}