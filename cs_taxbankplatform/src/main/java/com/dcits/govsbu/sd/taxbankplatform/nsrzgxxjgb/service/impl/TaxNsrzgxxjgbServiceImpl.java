package com.dcits.govsbu.sd.taxbankplatform.nsrzgxxjgb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.nsrzgxxjgb.mapper.TaxNsrzgxxjgbEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.nsrzgxxjgb.model.TaxNsrzgxxjgbEntity;
import com.dcits.govsbu.sd.taxbankplatform.nsrzgxxjgb.service.TaxNsrzgxxjgbService;
/**
 * 
 * @author Administrator
 *
 */
@Service("TaxNsrzgxxjgb")
@Transactional
public class TaxNsrzgxxjgbServiceImpl extends AbstractService<TaxNsrzgxxjgbEntity, String> implements TaxNsrzgxxjgbService {
	@Autowired
	private TaxNsrzgxxjgbEntityMapper taxNsrzgxxjgbEntityMapper;
	
	@Autowired
	public void setBasemapper(){
		super.setBaseMapper(taxNsrzgxxjgbEntityMapper);
	}
	@Override
	public int deleteByDjxh(Long djxh){
		return taxNsrzgxxjgbEntityMapper.deleteByDjxh(djxh);
	};
}
