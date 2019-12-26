package com.dcits.govsbu.sd.taxbankplatform.fpkjxx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.fpkjxx.mapper.TaxFpKjxxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.fpkjxx.model.TaxFpKjxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.fpkjxx.service.TaxFpKjxxService;

@Service("taxFpKjxxService")
@Transactional
public class TaxFpKjxxServiceImpl extends AbstractService<TaxFpKjxxEntity, String> implements TaxFpKjxxService {
	@Autowired
	private TaxFpKjxxEntityMapper taxFpKjxxEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxFpKjxxEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxFpKjxxEntityMapper.deleteByDjxh(djxh);
	};
}
