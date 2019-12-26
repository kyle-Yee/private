package com.dcits.govsbu.sd.taxbankplatform.orgautcomare.service;

import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.orgautcomare.model.OrgAutComareEntity;
/**
 * 
 * @author Administrator
 *
 */
public interface OrgAutComareService {
	int insert(OrgAutComareEntity record);
	//查询表中是否存在已经插入的数据
	public OrgAutComareEntity queryAuthorityByOrgId (Map<String, Object> parameter);
	//删除数据
	public int deleteDataByOrgIdAndTaxId (Map<String, Object> parameter);
}
