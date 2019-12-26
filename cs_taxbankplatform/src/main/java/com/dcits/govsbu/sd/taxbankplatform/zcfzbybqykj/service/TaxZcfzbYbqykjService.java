package com.dcits.govsbu.sd.taxbankplatform.zcfzbybqykj.service;

import com.dcits.govsbu.sd.taxbankplatform.zcfzbybqykj.model.TaxZcfzbYbqykjEntity;

/**
 * 
 * @author Administrator
 *
 */
public interface TaxZcfzbYbqykjService {
	
	TaxZcfzbYbqykjEntity findById(String ttzyId);
	//根据djxh 删除纳税信息
	public int deleteByDjxh(String djxh);
}
