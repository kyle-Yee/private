package com.dcits.govsbu.sd.taxbankplatform.sbxx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.sbxx.mapper.TaxSbxxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.sbxx.model.TaxSbxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.sbxx.service.TaxSbxxService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxSbxxService")
@Transactional
public class TaxSbxxServiceImpl extends AbstractService<TaxSbxxEntity, String> implements TaxSbxxService {
	@Autowired
	private TaxSbxxEntityMapper taxSbxxEntityMapper;
	
	@Autowired 
	public void setBaseMapper(){
		super.setBaseMapper(taxSbxxEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxSbxxEntityMapper.deleteByDjxh(djxh);
	};
}
