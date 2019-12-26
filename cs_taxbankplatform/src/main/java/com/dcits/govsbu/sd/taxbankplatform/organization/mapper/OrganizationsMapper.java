package com.dcits.govsbu.sd.taxbankplatform.organization.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.RzxyEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.SqxyEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxauthority.model.TaxAuthorityEntity;


/**
 * 
 * @author liwangxiong
 *
 */
@Repository
public interface OrganizationsMapper extends BaseMapper<OrganizationEntity, String > {


	public List<OrganizationEntity> queryUpOrgIdListAll(Map<String, Object> parameter);
	//查询已经分配的区域机关
	public List<TaxAuthorityEntity> findTaxAuthorityListById(String id);
	public List<RzxyEntity> findrzxy();
	public List<SqxyEntity> findsqxy();

}
