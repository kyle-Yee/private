package com.dcits.govsbu.sd.taxbankplatform.count.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.count.model.BusinessdetailsEntity;

public interface BusinessdetailsService {
	public List<BusinessdetailsEntity> searchDetails(Map<String,Object> parameters);
}
