package com.dcits.govsbu.sd.taxbankplatform.ajxx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.ajxx.mapper.TaxAjxxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.ajxx.model.TaxAjxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.ajxx.service.TaxAjxxService;
import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxAjxxService")
@Transactional
public class TaxAjxxServiceImpl extends AbstractService<TaxAjxxEntity, String> implements TaxAjxxService {
	@Autowired
	private TaxAjxxEntityMapper taxAjxxEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxAjxxEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxAjxxEntityMapper.deleteByDjxh(djxh);
	};
	@Override
	public List<TaxAjxxEntity> adderWithParameterMap(Map<String, Object> parameter){
		return taxAjxxEntityMapper.adderWithParameterMap(parameter);
	};
}
