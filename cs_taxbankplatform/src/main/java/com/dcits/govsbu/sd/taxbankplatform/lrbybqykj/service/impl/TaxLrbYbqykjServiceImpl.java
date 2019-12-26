package com.dcits.govsbu.sd.taxbankplatform.lrbybqykj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.lrbybqykj.mapper.TaxLrbYbqykjEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.lrbybqykj.model.TaxLrbYbqykjEntity;
import com.dcits.govsbu.sd.taxbankplatform.lrbybqykj.service.TaxLrbYbqykjService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxLrbYbqykjService")
@Transactional
public class TaxLrbYbqykjServiceImpl extends AbstractService<TaxLrbYbqykjEntity, String> implements TaxLrbYbqykjService {
	@Autowired
	private TaxLrbYbqykjEntityMapper taxLrbYbqykjEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxLrbYbqykjEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxLrbYbqykjEntityMapper.deleteByDjxh(djxh);
	};
}
