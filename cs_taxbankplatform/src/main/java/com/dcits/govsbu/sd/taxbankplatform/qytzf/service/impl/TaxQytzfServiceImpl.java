package com.dcits.govsbu.sd.taxbankplatform.qytzf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.qytzf.mapper.TaxQytzfEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.qytzf.model.TaxQytzfEntity;
import com.dcits.govsbu.sd.taxbankplatform.qytzf.service.TaxQytzfService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxQytzfService")
@Transactional
public class TaxQytzfServiceImpl extends AbstractService<TaxQytzfEntity, String> implements TaxQytzfService {
	@Autowired
	private TaxQytzfEntityMapper taxQytzfEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxQytzfEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxQytzfEntityMapper.deleteByDjxh(djxh);
	};
}
