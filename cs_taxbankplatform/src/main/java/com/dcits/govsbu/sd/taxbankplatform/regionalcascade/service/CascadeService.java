package com.dcits.govsbu.sd.taxbankplatform.regionalcascade.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.dcits.govsbu.sd.taxbankplatform.regionalcascade.model.CascadeEntity;

public interface CascadeService {
	public JSONArray querycityListAll(String regionId);

	public JSONArray queryareaListAll(String regionId);
	
	public  List<CascadeEntity> queryUserArea(String userId);
	
	public JSONArray queryareaBycity(String pcId);
}
