package com.dcits.govsbu.sd.taxbankplatform.lrbxqykjzd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.lrbxqykjzd.mapper.TaxLrbXqykjzdEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.lrbxqykjzd.model.TaxLrbXqykjzdEntity;
import com.dcits.govsbu.sd.taxbankplatform.lrbxqykjzd.service.TaxLrbXqykjzdService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxLrbXqykjzdService")
@Transactional
public class TaxLrbXqykjzdServiceImpl extends AbstractService<TaxLrbXqykjzdEntity, String> implements TaxLrbXqykjzdService {
	@Autowired
	private TaxLrbXqykjzdEntityMapper taxLrbXqykjzdEntityMapper;
	
	@Autowired
	public void setBasemapper(){
		super.setBaseMapper(taxLrbXqykjzdEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxLrbXqykjzdEntityMapper.deleteByDjxh(djxh);
	};
}
