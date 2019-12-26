package com.dcits.govsbu.sd.taxbankplatform.nsrxydj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.nsrxydj.mapper.TaxNsrxydjEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.nsrxydj.model.TaxNsrxydjEntity;
import com.dcits.govsbu.sd.taxbankplatform.nsrxydj.service.TaxNsrxydjService;
@Service("taxNsrxydjService")
@Transactional
public class TaxNsrxydjServiceImpl extends AbstractService<TaxNsrxydjEntity, String> implements TaxNsrxydjService {
	@Autowired
	private TaxNsrxydjEntityMapper taxNsrxydjEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxNsrxydjEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxNsrxydjEntityMapper.deleteByDjxh(djxh);
	};
}
