package com.dcits.govsbu.sd.taxbankplatform.regions.mapper;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;


/**
 * 
 * @author liwangxiong
 *
 */
@Repository
public interface RegionsMapper extends BaseMapper<RegionsEntity, String > {
	
	public List<RegionsEntity> findByRegioncode(String regioncodes);
}
