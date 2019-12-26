package com.dcits.govsbu.sd.taxbankplatform.bmyxcx.service;

import com.dcits.govsbu.sd.taxbankplatform.bmyxcx.model.TaxBmyxcxEntity;


/**
 * 
 * @author Administrator
 *
 */
public interface TaxBmyxcxService {
	//根据djxh 删除纳税信息
	public int deleteByDjxh(String djxh);
	public int insert(TaxBmyxcxEntity record);
}
