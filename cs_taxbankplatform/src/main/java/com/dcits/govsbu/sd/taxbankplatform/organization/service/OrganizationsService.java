package com.dcits.govsbu.sd.taxbankplatform.organization.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.RzxyEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.SqxyEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxauthority.model.TaxAuthorityEntity;

/**
 * 
 * @author liwangxiong
 *
 */
public interface OrganizationsService {
	public List<OrganizationEntity> queryListByPage(Map<String, Object> parameter);
	
	public int insert(OrganizationEntity organizationEntity);
	
	public OrganizationEntity findById(String id);

	public int update(OrganizationEntity organizationEntity);
    
    public int deleteBatchById(List<String> orgids);
    
    public List<OrganizationEntity> queryListAll(Map<String, Object> parameter);
    //查询上级级别id
    public List<OrganizationEntity> queryUpOrgIdListAll(Map<String, Object> parameter);
    //查询已分配的区域机关
    public List<TaxAuthorityEntity> findTaxAuthorityListById(String id);
	public List<RzxyEntity> findrzxy();
	public List<SqxyEntity> findsqxy();
}
