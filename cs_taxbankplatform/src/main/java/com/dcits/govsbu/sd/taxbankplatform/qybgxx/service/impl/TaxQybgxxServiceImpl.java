package com.dcits.govsbu.sd.taxbankplatform.qybgxx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.qybgxx.mapper.TaxQybgxxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.qybgxx.model.TaxQybgxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.qybgxx.service.TaxQybgxxService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxQybgxxService")
@Transactional
public class TaxQybgxxServiceImpl extends AbstractService<TaxQybgxxEntity, String> implements TaxQybgxxService {
	@Autowired
	private TaxQybgxxEntityMapper taxQybgxxEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxQybgxxEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxQybgxxEntityMapper.deleteByDjxh(djxh);
	};
}
