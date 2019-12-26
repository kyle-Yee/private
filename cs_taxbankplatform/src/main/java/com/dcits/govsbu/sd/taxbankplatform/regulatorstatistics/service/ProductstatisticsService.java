package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.GuaranteeStyleEntity;

public interface ProductstatisticsService {
    List<BanklistInfoEntity> productstatistics(Map<String, Object> parameters);

    List<Map<String, Object>> productstatistics_new(Map<String, Object> parameters);

    List<GuaranteeStyleEntity> findgsById(String id);
}
