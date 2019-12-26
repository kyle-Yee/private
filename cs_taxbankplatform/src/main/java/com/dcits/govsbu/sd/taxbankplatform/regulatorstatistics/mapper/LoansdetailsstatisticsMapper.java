package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.LoansdetailsEntity;

@Repository 
public interface LoansdetailsstatisticsMapper extends BaseMapper<LoansdetailsEntity, String> {
	public List<LoansdetailsEntity> loansdetailsstatistics(Map<String, Object> parameters);
}
