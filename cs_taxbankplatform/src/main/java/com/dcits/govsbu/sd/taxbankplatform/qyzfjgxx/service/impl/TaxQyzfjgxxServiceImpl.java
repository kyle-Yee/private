package com.dcits.govsbu.sd.taxbankplatform.qyzfjgxx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.qyzfjgxx.mapper.TaxQyzfjgxxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.qyzfjgxx.model.TaxQyzfjgxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.qyzfjgxx.service.TaxQyzfjgxxService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxQyzfjgxxService")
@Transactional
public class TaxQyzfjgxxServiceImpl extends AbstractService<TaxQyzfjgxxEntity, String> implements TaxQyzfjgxxService {
	@Autowired
	private TaxQyzfjgxxEntityMapper taxQyzfjgxxEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxQyzfjgxxEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxQyzfjgxxEntityMapper.deleteByDjxh(djxh);
	};
}
