package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model.DataRefreshEntity;

@Repository 
public interface DataRefreshMapper extends BaseMapper<DataRefreshEntity, String> {
	
	//获取
	public DataRefreshEntity getDataRefresh(Map<String,Object> map);
	
	//插入
	public int insertDataRefresh(DataRefreshEntity dataRefreshEntity)throws Exception;

	//更新
	public int updateDataRefresh(DataRefreshEntity dataRefreshEntity) throws Exception;
	
	
	//获取所有银行总部组织
	public List<Map<String,Object>> getCoreBank();
	
	//获取指定银行所有用户
	public List<Map<String,Object>> getBankAllUser(String org_id);
	
	
}
