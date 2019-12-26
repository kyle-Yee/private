package com.dcits.govsbu.sd.taxbankplatform.qyzcfzbxqykjzd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.qyzcfzbxqykjzd.mapper.TaxQyzcfzbXqykjzdEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.qyzcfzbxqykjzd.model.TaxQyzcfzbXqykjzdEntity;
import com.dcits.govsbu.sd.taxbankplatform.qyzcfzbxqykjzd.service.TaxQyzcfzbXqykjzdService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxQyzcfzbXqykjzdService")
@Transactional
public class TaxQyzcfzbXqykjzdServiceImpl extends AbstractService<TaxQyzcfzbXqykjzdEntity, String> implements TaxQyzcfzbXqykjzdService {
	@Autowired
	private TaxQyzcfzbXqykjzdEntityMapper taxQyzcfzbXqykjzdEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxQyzcfzbXqykjzdEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxQyzcfzbXqykjzdEntityMapper.deleteByDjxh(djxh);
	};
}
