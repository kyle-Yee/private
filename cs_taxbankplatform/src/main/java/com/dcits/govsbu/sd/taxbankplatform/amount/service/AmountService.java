package com.dcits.govsbu.sd.taxbankplatform.amount.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.amount.model.AmountEntity;

public interface AmountService {

	public int insert(AmountEntity amountEntity);

	public AmountEntity findByName(String amountName);

	public List<AmountEntity> queryListByPage(Map<String, Object> parameters); //add by baolong.hu
	
	public AmountEntity findById(String amountId); //add by baolong.hu

	public int delAmount(String id);

}
