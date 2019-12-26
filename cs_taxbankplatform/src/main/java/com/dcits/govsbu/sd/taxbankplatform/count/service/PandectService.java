package com.dcits.govsbu.sd.taxbankplatform.count.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.count.model.DataCountEntity;
import com.dcits.govsbu.sd.taxbankplatform.count.model.PandectEntity;

public interface PandectService {

	public List<PandectEntity> searchByRegionId();

	public List<DataCountEntity> findDataCount(Map<String, Object> parameters);
	
	public List<PandectEntity> findDataCountByMonth(Map<String, Object> parameters);


}
