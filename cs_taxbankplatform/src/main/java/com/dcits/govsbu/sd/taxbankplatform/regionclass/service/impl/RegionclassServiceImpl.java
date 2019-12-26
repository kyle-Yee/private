package com.dcits.govsbu.sd.taxbankplatform.regionclass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.regionclass.mapper.RegionclassMapper;
import com.dcits.govsbu.sd.taxbankplatform.regionclass.model.RegionclassEntity;
import com.dcits.govsbu.sd.taxbankplatform.regionclass.service.RegionclassService;

@Service("RegionclassService")
public class RegionclassServiceImpl extends AbstractService<RegionclassEntity, String>  implements RegionclassService{

	@Autowired
	private RegionclassMapper regionclassMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(regionclassMapper);
	}

}
