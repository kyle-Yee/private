package com.dcits.govsbu.sd.taxbankplatform.ybnsrsbmxzb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.ybnsrsbmxzb.mapper.TaxYbnsrsbmxzbEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.ybnsrsbmxzb.model.TaxYbnsrsbmxzbEntity;
import com.dcits.govsbu.sd.taxbankplatform.ybnsrsbmxzb.service.TaxYbnsrsbmxzbService;

@Service("taxYbnsrsbmxzbService")
@Transactional
public class TaxYbnsrsbmxzbServiceImpl extends AbstractService<TaxYbnsrsbmxzbEntity, String> implements TaxYbnsrsbmxzbService {
	@Autowired
	private TaxYbnsrsbmxzbEntityMapper taxYbnsrsbmxzbEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxYbnsrsbmxzbEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxYbnsrsbmxzbEntityMapper.deleteByDjxh(djxh);
	};
}
