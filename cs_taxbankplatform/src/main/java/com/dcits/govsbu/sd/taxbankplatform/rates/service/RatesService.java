package com.dcits.govsbu.sd.taxbankplatform.rates.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.rates.model.RatesEntity;

public interface RatesService {

	List<RatesEntity> queryListByPage(Map<String, Object> parameters);  //add by baolong.hu
	
	public RatesEntity findById(String ratesEntityId); //add by baolong.hu

	public int insert(RatesEntity ratesEntity);

	public RatesEntity findByName(String ratesName);

	public int delRates(String id);
}
