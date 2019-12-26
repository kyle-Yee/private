package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.StaticEntity;

@Repository
public interface StaticMapper extends BaseMapper<StaticEntity, String>{

	public StaticEntity searchByCode(StaticEntity staticEntity);

	public StaticEntity searchListByRegionId(StaticEntity staticEntity);

}
