package com.dcits.govsbu.sd.taxbankplatform.invokeRecord.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.invokeRecord.mapper.InvokeRecodeMapper;
import com.dcits.govsbu.sd.taxbankplatform.invokeRecord.model.InvokeRecordEntity;
import com.dcits.govsbu.sd.taxbankplatform.invokeRecord.service.InvokeRecodeService;

@Service("invokeRecodeService")
public class InvokeRecodeServiceImpl extends AbstractService<InvokeRecordEntity, String> implements InvokeRecodeService {
	
	@Autowired
	InvokeRecodeMapper invokeRecodeMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(invokeRecodeMapper);
	}
	
	@Override
	public int insertRecode(InvokeRecordEntity in) {
		return invokeRecodeMapper.insertRecode(in);
	}

	@Override
	public int updateRecode(InvokeRecordEntity in) {
		return invokeRecodeMapper.updateRecode(in);
	}


}
