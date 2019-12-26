package com.dcits.govsbu.sd.taxbankplatform.fprzxx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.fprzxx.mapper.TaxFpRzxxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.fprzxx.model.TaxFpRzxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.fprzxx.service.TaxFpRzxxService;

/**
 * 
 * @author Administrator
 *
 */
@Service("taxFpRzxxService")
@Transactional
public class TaxFpRzxxServiceImpl extends AbstractService<TaxFpRzxxEntity, String> implements TaxFpRzxxService {
	@Autowired
	private TaxFpRzxxEntityMapper taxFpRzxxEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxFpRzxxEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxFpRzxxEntityMapper.deleteByDjxh(djxh);
	};
}
