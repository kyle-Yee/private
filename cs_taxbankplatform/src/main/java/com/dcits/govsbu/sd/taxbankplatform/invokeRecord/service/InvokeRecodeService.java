package com.dcits.govsbu.sd.taxbankplatform.invokeRecord.service;

import com.dcits.govsbu.sd.taxbankplatform.invokeRecord.model.InvokeRecordEntity;


public interface InvokeRecodeService {
	public int insertRecode(InvokeRecordEntity in);
	
	public int updateRecode(InvokeRecordEntity in);
}