package com.dcits.govsbu.sd.taxbankplatform.qsclxx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.qsclxx.mapper.TaxQsclxxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.qsclxx.model.TaxQsclxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.qsclxx.service.TaxQsclxxService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxQsclxxService")
@Transactional
public class TaxQsclxxServiceImpl extends AbstractService<TaxQsclxxEntity, String> implements TaxQsclxxService {
	@Autowired
	private TaxQsclxxEntityMapper taxQsclxxEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxQsclxxEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxQsclxxEntityMapper.deleteByDjxh(djxh);
	};
}
