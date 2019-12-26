package com.dcits.govsbu.sd.taxbankplatform.ajxx.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.ajxx.model.TaxAjxxEntity;

/**
 * 
 * @author Administrator
 *
 */
public interface TaxAjxxService {
	//根据djxh 删除纳税信息
	public int deleteByDjxh(String djxh);
	//test 存储过程
	public List<TaxAjxxEntity> adderWithParameterMap(Map<String, Object> parameter);
}
