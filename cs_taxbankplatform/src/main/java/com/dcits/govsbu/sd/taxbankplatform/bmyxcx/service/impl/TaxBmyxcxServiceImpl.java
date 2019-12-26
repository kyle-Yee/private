package com.dcits.govsbu.sd.taxbankplatform.bmyxcx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.bmyxcx.mapper.TaxBmyxcxEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.bmyxcx.model.TaxBmyxcxEntity;
import com.dcits.govsbu.sd.taxbankplatform.bmyxcx.service.TaxBmyxcxService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxBmyxcxService")
public class TaxBmyxcxServiceImpl extends AbstractService<TaxBmyxcxEntity, String> implements TaxBmyxcxService {
	@Autowired
	private TaxBmyxcxEntityMapper taxBmyxcxEntityMapper;
	
	@Autowired
	public void setMapper(){
		super.setBaseMapper(taxBmyxcxEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxBmyxcxEntityMapper.deleteByDjxh(djxh);
	};
	@Override
	public int insert(TaxBmyxcxEntity record){
		return taxBmyxcxEntityMapper.insert(record);
	};
}
