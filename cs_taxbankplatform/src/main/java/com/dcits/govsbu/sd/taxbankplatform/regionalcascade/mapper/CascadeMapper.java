package com.dcits.govsbu.sd.taxbankplatform.regionalcascade.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.count.model.PandectEntity;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;
import com.dcits.govsbu.sd.taxbankplatform.regionalcascade.model.CascadeEntity;

@Repository 
public interface CascadeMapper extends BaseMapper<PandectEntity, String> { 
	public List<ProvinceCitiesEntity> querycityListAll(String regionId);
	public List<ProvinceCitiesEntity> queryareaListAll(String regionId);
	public  List<CascadeEntity> queryUserArea(String userId);
	public List<ProvinceCitiesEntity> queryareaBycity(String pcId);
}
