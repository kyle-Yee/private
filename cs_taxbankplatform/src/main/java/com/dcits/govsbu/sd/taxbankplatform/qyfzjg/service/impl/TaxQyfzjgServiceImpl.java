package com.dcits.govsbu.sd.taxbankplatform.qyfzjg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.qyfzjg.mapper.TaxQyfzjgEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.qyfzjg.model.TaxQyfzjgEntity;
import com.dcits.govsbu.sd.taxbankplatform.qyfzjg.service.TaxQyfzjgService;
/**
 * 
 * @author Administrator
 *
 */
@Service("TaxQyfzjgService")
@Transactional
public class TaxQyfzjgServiceImpl extends AbstractService<TaxQyfzjgEntity, String> implements TaxQyfzjgService {
	@Autowired
	private TaxQyfzjgEntityMapper taxQyfzjgEntityMapper;
	
	@Autowired
	public void setBaesMapper(){
		super.setBaseMapper(taxQyfzjgEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxQyfzjgEntityMapper.deleteByDjxh(djxh);
	};
}
