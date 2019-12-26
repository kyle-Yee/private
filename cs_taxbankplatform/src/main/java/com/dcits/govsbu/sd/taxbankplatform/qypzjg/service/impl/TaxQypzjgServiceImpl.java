package com.dcits.govsbu.sd.taxbankplatform.qypzjg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.qypzjg.mapper.TaxQypzjgEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.qypzjg.model.TaxQypzjgEntity;
import com.dcits.govsbu.sd.taxbankplatform.qypzjg.service.TaxQypzjgService;
/**
 * 
 * @author Administrator
 *
 */
@Service("TaxQypzjgService")
@Transactional
public class TaxQypzjgServiceImpl extends AbstractService<TaxQypzjgEntity, String> implements TaxQypzjgService {
	@Autowired
	private TaxQypzjgEntityMapper taxQypzjgEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxQypzjgEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxQypzjgEntityMapper.deleteByDjxh(djxh);
	};
}
