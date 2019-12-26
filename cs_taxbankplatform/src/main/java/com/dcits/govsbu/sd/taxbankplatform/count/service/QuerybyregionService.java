package com.dcits.govsbu.sd.taxbankplatform.count.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.count.model.QuerybyregionEntity;

public interface QuerybyregionService {
	public List<QuerybyregionEntity> queryListByPage(Map<String, Object> parameters);
}
