package com.dcits.govsbu.sd.taxbankplatform.count.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.count.model.BusinessdetailsEntity;

@Repository 
public interface BusinessdetailsMapper extends BaseMapper<BusinessdetailsEntity, String> {
	public List<BusinessdetailsEntity> searchDetails(Map<String,Object> parameters);
}
