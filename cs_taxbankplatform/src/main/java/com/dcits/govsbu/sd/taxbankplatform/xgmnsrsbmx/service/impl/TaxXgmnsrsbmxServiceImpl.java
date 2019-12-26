package com.dcits.govsbu.sd.taxbankplatform.xgmnsrsbmx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.xgmnsrsbmx.mapper.TaxXgmnsrsbmxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.xgmnsrsbmx.model.TaxXgmnsrsbmxEntity;
import com.dcits.govsbu.sd.taxbankplatform.xgmnsrsbmx.service.TaxXgmnsrsbmxService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxXgmnsrsbmxService")
@Transactional
public class TaxXgmnsrsbmxServiceImpl extends AbstractService<TaxXgmnsrsbmxEntity, String> implements TaxXgmnsrsbmxService {
	@Autowired
	private TaxXgmnsrsbmxEntityMapper taxXgmnsrsbmxEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxXgmnsrsbmxEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxXgmnsrsbmxEntityMapper.deleteByDjxh(djxh);
	};
}
