package com.dcits.govsbu.sd.taxbankplatform.taxauthority.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.taxauthority.model.TaxAuthorityEntity;

/**
 * 
 * @author Administrator
 *
 */
public interface TaxAuthorityService {
	/**
	 * 获取区域机关List
	 * @param parameter
	 * @return
	 */
	public List<TaxAuthorityEntity> queryListAll(Map<String, Object> parameter);
	
	int insert(TaxAuthorityEntity taxAuthorityEntity);

	public TaxAuthorityEntity findById(String id);

	public List<TaxAuthorityEntity> queryListByPage(Map<String, Object> parameters);

	public int update(TaxAuthorityEntity taxAuthorityEntity);

	public int deleteBatchById(List<String> roleIds);
}
