package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.FaqEntity;

public interface FaqService {

	public List<FaqEntity> queryListByPage(Map<String, Object> parameters);

	public FaqEntity findById(String id);

	public int update(FaqEntity faqEntity);

	public int insert(FaqEntity faqEntity);

}
