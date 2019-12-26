package com.dcits.govsbu.sd.taxbankplatform.lrbxqykj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.lrbxqykj.mapper.TaxLrbXqykjEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.lrbxqykj.model.TaxLrbXqykjEntity;
import com.dcits.govsbu.sd.taxbankplatform.lrbxqykj.service.TaxLrbXqykjService;


@Service("TaxLrbXqykjService")
@Transactional
public class TaxLrbXqykjServiceImpl extends AbstractService<TaxLrbXqykjEntity, String> implements TaxLrbXqykjService {
	@Autowired
	private TaxLrbXqykjEntityMapper taxLrbXqykjEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxLrbXqykjEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxLrbXqykjEntityMapper.deleteByDjxh(djxh);
	};
}
