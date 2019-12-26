package com.dcits.govsbu.sd.taxbankplatform.zcfzbybqykj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.zcfzbybqykj.mapper.TaxZcfzbYbqykjEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.zcfzbybqykj.model.TaxZcfzbYbqykjEntity;
import com.dcits.govsbu.sd.taxbankplatform.zcfzbybqykj.service.TaxZcfzbYbqykjService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxZcfzbYbqykjService")
@Transactional
public class TaxZcfzbYbqykjServiceImpl extends AbstractService<TaxZcfzbYbqykjEntity, String> implements TaxZcfzbYbqykjService {
	@Autowired
	private TaxZcfzbYbqykjEntityMapper taxZcfzbYbqykjEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxZcfzbYbqykjEntityMapper);
	}
	@Override
	
	public TaxZcfzbYbqykjEntity findById(String ttzyId){
		return taxZcfzbYbqykjEntityMapper.findById(ttzyId);
	};
	@Override
	public int deleteByDjxh(String djxh){
		return taxZcfzbYbqykjEntityMapper.deleteByDjxh(djxh);
	};
}
