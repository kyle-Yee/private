package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;

@Repository 
public interface BankliststatisticsMapper extends BaseMapper<BanklistInfoEntity, String> {
	public List<BanklistInfoEntity> bankliststatistics(Map<String, Object> parameters);
}
