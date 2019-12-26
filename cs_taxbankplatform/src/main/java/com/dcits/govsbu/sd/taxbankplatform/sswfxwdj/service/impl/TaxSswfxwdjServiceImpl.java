package com.dcits.govsbu.sd.taxbankplatform.sswfxwdj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.sswfxwdj.mapper.TaxSswfxwdjEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.sswfxwdj.model.TaxSswfxwdjEntity;
import com.dcits.govsbu.sd.taxbankplatform.sswfxwdj.service.TaxSswfxwdjService;
/**
 * 
 * @author Administrator
 *
 */
@Service("taxSswfxwdjService")
@Transactional
public class TaxSswfxwdjServiceImpl extends AbstractService<TaxSswfxwdjEntity, String> implements TaxSswfxwdjService {
	@Autowired
	private TaxSswfxwdjEntityMapper taxSswfxwdjEntityMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(taxSswfxwdjEntityMapper);
	}
	@Override
	public int deleteByDjxh(String djxh){
		return taxSswfxwdjEntityMapper.deleteByDjxh(djxh);
	};
}
