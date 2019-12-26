package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.StaticEntity;

public interface StaticService {

	public List<StaticEntity> queryListByPage(Map<String, Object> parameters);

	public StaticEntity findById(String id);

	public int insertStatic(StaticEntity staticEntity);

	public int update(StaticEntity staticEntity);

	public int searchListByRegionId(StaticEntity staticEntity);

}
