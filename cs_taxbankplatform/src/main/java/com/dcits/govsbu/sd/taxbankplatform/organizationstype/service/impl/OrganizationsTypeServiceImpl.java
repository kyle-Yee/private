package com.dcits.govsbu.sd.taxbankplatform.organizationstype.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.organizationstype.mapper.OrganizationsTypeMapper;
import com.dcits.govsbu.sd.taxbankplatform.organizationstype.model.OrganizationsTypeEntity;
import com.dcits.govsbu.sd.taxbankplatform.organizationstype.service.OrganizationsTypeService;


/**
 * OrganizationsTypeServiceImpl.java
 * @author 严添麟
 * @date 2016年8月19日
 * @caption 组织类型代码查询
 */
@Service("organizationsTypeService")
public class OrganizationsTypeServiceImpl extends AbstractService<OrganizationsTypeEntity, String> implements OrganizationsTypeService{

	@Autowired
	private OrganizationsTypeMapper organizationsTypeMapper;
	
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(organizationsTypeMapper);
	}
}
