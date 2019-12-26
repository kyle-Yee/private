package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model.NsxypjyyqkEntity;

@Repository 
public interface NsxypjyyqkMapper extends BaseMapper<NsxypjyyqkEntity, String> {
	
	public List<Map<String,String>> sjtsyhs (Map<String, Object> parameters);
	public List<Map<String,String>> qzysxhs (Map<String, Object> parameters);
	
	public List<String> findXlHyDm(String dl_hydm);
	public List<String> findZlHyDm(String dl_hydm);
	
	public List<Map<String,String>> findHy();
}
