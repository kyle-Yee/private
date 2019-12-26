package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.mapper.FaqMapper;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.FaqEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.FaqService;

@Service
public class FaqServiceImpl extends AbstractService<FaqEntity, String> implements FaqService {

	@Autowired
	private FaqMapper faqMapper;

	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(faqMapper);
	}
}
