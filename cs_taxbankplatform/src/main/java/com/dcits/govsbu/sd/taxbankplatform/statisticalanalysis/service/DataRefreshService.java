package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model.DataRefreshEntity;


public interface DataRefreshService {
	//获取
	public DataRefreshEntity getDataRefresh(Map<String,Object> map);
	
	//插入
	public int insertDataRefresh(DataRefreshEntity dataRefreshEntity);

	//更新
	public int updateDataRefresh(DataRefreshEntity dataRefreshEntity);
	
	//获取所有银行总部组织
	public List<Map<String,Object>> getCoreBank();
	
	//获取指定银行所有用户
	public List<Map<String,Object>> getBankAllUser(String org_id);
	
}
