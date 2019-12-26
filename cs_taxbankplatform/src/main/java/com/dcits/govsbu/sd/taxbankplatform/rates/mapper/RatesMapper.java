package com.dcits.govsbu.sd.taxbankplatform.rates.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.rates.model.RatesEntity;

@Repository
public interface RatesMapper extends BaseMapper<RatesEntity, String>{

	public int delRates(@Param(value="id") String id);

}
