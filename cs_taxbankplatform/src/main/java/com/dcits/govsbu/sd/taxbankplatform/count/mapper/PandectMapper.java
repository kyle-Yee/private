package com.dcits.govsbu.sd.taxbankplatform.count.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.count.model.DataCountEntity;
import com.dcits.govsbu.sd.taxbankplatform.count.model.PandectEntity;

@Repository 
public interface PandectMapper extends BaseMapper<PandectEntity, String> {

	public List<PandectEntity> searchByRegionId();
	public List<DataCountEntity> findDataCount(Map<String, Object> parameters);
	public List<PandectEntity> findDataCountByMonth(Map<String, Object> parameters); 


}
