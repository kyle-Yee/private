package com.dcits.govsbu.sd.taxbankplatform.invokeRecord.mapper;

import org.springframework.stereotype.Repository;
import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.invokeRecord.model.InvokeRecordEntity;


@Repository
public interface InvokeRecodeMapper extends BaseMapper<InvokeRecordEntity, String>{
	public int insertRecode(InvokeRecordEntity in);
	
	public int updateRecode(InvokeRecordEntity in);
}
