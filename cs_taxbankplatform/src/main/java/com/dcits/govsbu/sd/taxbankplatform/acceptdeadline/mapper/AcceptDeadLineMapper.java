package com.dcits.govsbu.sd.taxbankplatform.acceptdeadline.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.acceptdeadline.model.AcceptDeadLineEntity;
import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
/**
 * 
 * @author Administrator
 *
 */
@Repository
public interface AcceptDeadLineMapper extends BaseMapper<AcceptDeadLineEntity, String> {
	@Override
	public List<AcceptDeadLineEntity> queryListAll(Map<String, Object> parameter);
	
	public Integer deadLineCount(String orgid);
	
}
