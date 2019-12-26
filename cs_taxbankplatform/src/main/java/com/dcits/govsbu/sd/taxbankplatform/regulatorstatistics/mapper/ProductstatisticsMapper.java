package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.GuaranteeStyleEntity;

@Repository
public interface ProductstatisticsMapper extends BaseMapper<BanklistInfoEntity, String> {
    List<BanklistInfoEntity> productstatistics(Map<String, Object> parameters);

    List<Map<String, Object>> productstatistics_new(Map<String, Object> parameters);

    List<GuaranteeStyleEntity> findgsById(String id);
}
