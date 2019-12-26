package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;

@Repository 
public interface AreastatisticsMapper extends BaseMapper<BanklistInfoEntity, String> {
	public List<BanklistInfoEntity> areastatistics(Map<String, Object> parameters);
	public List<BanklistInfoEntity> areastatistics1(Map<String, Object> parameters);
}
