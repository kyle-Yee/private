package com.dcits.govsbu.sd.taxbankplatform.regions.service.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.regions.mapper.RegionsMapper;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;


/**
 * 
 * @author liwangxiong
 *
 */
@Service("regionsService")
public class RegionsServiceImpl extends AbstractService<RegionsEntity, String> implements RegionsService{
	@Autowired
	private RegionsMapper regionsMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(regionsMapper);
	}

	@Override
	public List<RegionsEntity> findByRegioncode(String regioncodes) {
		// TODO Auto-generated method stub
		return regionsMapper.findByRegioncode(regioncodes);
	}
}
