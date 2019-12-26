package com.dcits.govsbu.sd.taxbankplatform.zlbscjb.service;

import com.dcits.govsbu.sd.taxbankplatform.zlbscjb.model.TaxZlbscjbEntity;


/**
 * 
 * @author Administrator
 *
 */
public interface TaxZlbscjbService {
	public TaxZlbscjbEntity findById(String id);
	//根据djxh 删除纳税信息
	public int deleteByDjxh(String djxh);
}
