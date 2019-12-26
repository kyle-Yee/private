package com.dcits.govsbu.sd.taxbankplatform.regionclass.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.regionclass.model.RegionclassEntity;

/**
 * 
 * @author 谢翠
 *
 */
public interface RegionclassService {

	int insert(RegionclassEntity regionclassEntity);

	public RegionclassEntity findById(String id);

	public List<RegionclassEntity> queryListByPage(Map<String, Object> parameters);

	public int update(RegionclassEntity regionclassEntity);

	public int deleteBatchById(List<String> roleIds);
	
}
