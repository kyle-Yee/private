package com.dcits.govsbu.sd.taxbankplatform.guaranteestyle.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.guaranteestyle.model.GuaranteeStyleEntity;

/**
 * 
 * @author liwangxiong
 *
 */
public interface GuaranteeStyleService {
	public List<GuaranteeStyleEntity> queryListByPage(Map<String, Object> parameter);
	
	public int insert(GuaranteeStyleEntity guaranteeStyleEntity);
	
	public GuaranteeStyleEntity findById(String id);

	public int update(GuaranteeStyleEntity guaranteeStyleEntity);
    
    public int deleteBatchById(List<String> styleids);
}
