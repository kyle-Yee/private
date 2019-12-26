package com.dcits.govsbu.sd.taxbankplatform.yjsf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.yjsf.mapper.TaxYjsfEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.yjsf.model.TaxYjsfEntity;
import com.dcits.govsbu.sd.taxbankplatform.yjsf.service.TaxYjsfService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxYjsfService")
@Transactional
public class TaxYjsfServiceImpl extends AbstractService<TaxYjsfEntity, String> implements TaxYjsfService {
	@Autowired
	private TaxYjsfEntityMapper taxYjsfEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxYjsfEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxYjsfEntityMapper.deleteByDjxh(djxh);
	};
}
