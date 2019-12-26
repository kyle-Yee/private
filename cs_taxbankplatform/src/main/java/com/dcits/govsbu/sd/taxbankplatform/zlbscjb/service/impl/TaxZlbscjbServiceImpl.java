package com.dcits.govsbu.sd.taxbankplatform.zlbscjb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.zlbscjb.mapper.TaxZlbscjbEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.zlbscjb.model.TaxZlbscjbEntity;
import com.dcits.govsbu.sd.taxbankplatform.zlbscjb.service.TaxZlbscjbService;

/**
 * 
 * @author Administrator
 *
 */
@Service("taxZlbscjbService")
@Transactional
public class TaxZlbscjbServiceImpl extends AbstractService<TaxZlbscjbEntity, String> implements TaxZlbscjbService {
	@Autowired
	private TaxZlbscjbEntityMapper taxZlbscjbEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxZlbscjbEntityMapper);
	}
	@Override
	public TaxZlbscjbEntity findById(String id){
		return taxZlbscjbEntityMapper.findById(id);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxZlbscjbEntityMapper.deleteByDjxh(djxh);
	};
}
