package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.ParametersEntity;

public interface ParametersService {

	public List<ParametersEntity> queryListByPage(Map<String, Object> parameters);

	public ParametersEntity findById(String id);

	public int insert(ParametersEntity parametersEntity);

	public int update(ParametersEntity parametersEntity);

	public int findByCode(String code, String id);
	
	public String QueryValueByCode(String code) throws Exception;

}
