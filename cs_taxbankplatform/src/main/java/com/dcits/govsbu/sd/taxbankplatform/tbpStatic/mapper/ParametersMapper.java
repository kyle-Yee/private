package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.ParametersEntity;

@Repository
public interface ParametersMapper extends BaseMapper<ParametersEntity, String> {

	public ParametersEntity findCode(String code);
	
	public String QueryValueByCode(String code) throws Exception;

}
