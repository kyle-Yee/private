package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.mapper.ParametersMapper;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.ParametersEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.ParametersService;

@Service
public class ParametersServiceImpl extends AbstractService<ParametersEntity, String> implements ParametersService {


	@Autowired
	private ParametersMapper parametersMapper;

	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(parametersMapper);
	}

	/**
	 * 判断code唯一性
	 */
	@Override
	public int findByCode(String code, String id) {
		ParametersEntity ckCode = parametersMapper.findCode(code);
		if(null != ckCode){
			if(ckCode.getId().equals(id)){
				return 0;
			}else{
				return 1;
			}
		}else{
			return 0;
		}
	}

	@Override
	public String QueryValueByCode(String code) throws Exception {
		return parametersMapper.QueryValueByCode(code);
	}
}
