package com.dcits.govsbu.sd.taxbankplatform.guaranteestyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.guaranteestyle.mapper.GuaranteeStyleMapper;
import com.dcits.govsbu.sd.taxbankplatform.guaranteestyle.model.GuaranteeStyleEntity;
import com.dcits.govsbu.sd.taxbankplatform.guaranteestyle.service.GuaranteeStyleService;


/**
 * 
 * @author liwangxiong
 *
 */
@Service("guaranteeStyleService")
public class GuaranteeStyleServiceImpl extends AbstractService<GuaranteeStyleEntity, String> implements GuaranteeStyleService{
	@Autowired
	private GuaranteeStyleMapper guaranteeStyleMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(guaranteeStyleMapper);
	}
}
