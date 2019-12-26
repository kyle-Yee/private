package com.dcits.govsbu.sd.taxbankplatform.lrbqykjzd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.lrbqykjzd.mapper.TaxLrbQykjzdEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.lrbqykjzd.model.TaxLrbQykjzdEntity;
import com.dcits.govsbu.sd.taxbankplatform.lrbqykjzd.service.TaxLrbQykjzdService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxLrbQykjzdService")
@Transactional
public class TaxLrbQykjzdServiceImpl extends AbstractService<TaxLrbQykjzdEntity , String> implements TaxLrbQykjzdService {
	@Autowired
	private TaxLrbQykjzdEntityMapper taxLrbQykjzdEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxLrbQykjzdEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxLrbQykjzdEntityMapper.deleteByDjxh(djxh);
	};
}
