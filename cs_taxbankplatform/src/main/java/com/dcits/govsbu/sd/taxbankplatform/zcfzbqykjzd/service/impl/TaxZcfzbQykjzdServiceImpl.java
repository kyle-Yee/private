package com.dcits.govsbu.sd.taxbankplatform.zcfzbqykjzd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.zcfzbqykjzd.mapper.TaxZcfzbQykjzdEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.zcfzbqykjzd.model.TaxZcfzbQykjzdEntity;
import com.dcits.govsbu.sd.taxbankplatform.zcfzbqykjzd.service.TaxZcfzbQykjzdService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxZcfzbQykjzdService")
@Transactional
public class TaxZcfzbQykjzdServiceImpl extends AbstractService<TaxZcfzbQykjzdEntity, String> implements TaxZcfzbQykjzdService {
	@Autowired
	private TaxZcfzbQykjzdEntityMapper taxZcfzbQykjzdEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxZcfzbQykjzdEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxZcfzbQykjzdEntityMapper.deleteByDjxh(djxh);
	};
}
