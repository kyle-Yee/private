package com.dcits.govsbu.sd.taxbankplatform.organization.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.organization.mapper.OrganizationsMapper;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.RzxyEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.SqxyEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.taxauthority.model.TaxAuthorityEntity;


/**
 * 
 * @author liwangxiong
 *
 */
@Service("organizationsService")
public class OrganizationsServiceImpl extends AbstractService<OrganizationEntity, String> implements OrganizationsService{
	@Autowired
	private OrganizationsMapper organizationsMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(organizationsMapper);
	}

	@Override
	public List<OrganizationEntity> queryUpOrgIdListAll(
			Map<String, Object> parameter) {
		return organizationsMapper.queryUpOrgIdListAll(parameter);
	}
	@Override
	public List<TaxAuthorityEntity> findTaxAuthorityListById(String id){
		return organizationsMapper.findTaxAuthorityListById(id);
	}

	@Override
	public List<RzxyEntity> findrzxy() {
		// TODO Auto-generated method stub
		return organizationsMapper.findrzxy();
	}

	@Override
	public List<SqxyEntity> findsqxy() {
		// TODO Auto-generated method stub
		return organizationsMapper.findsqxy();
	}
}
