package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model.ActualissuanceLoansEntity;

@Repository 
public interface ActualissuanceLoansMapper extends BaseMapper<ActualissuanceLoansEntity, String> {
	public List<ActualissuanceLoansEntity> actualissuanceLoansEntities (Map<String, Object> parameters);
	
	
	//获取不随搜索变化数据
	public ActualissuanceLoansEntity getStaticData(Map<String, Object> parameters);
	
	//获取随搜索变化数据
	public ActualissuanceLoansEntity getDynamicData(Map<String, Object> parameters);
	
	//获取该pc_id 下辖的所有pc_id
	public List<String> getUnderArea(Map<String,Object> map);

}
