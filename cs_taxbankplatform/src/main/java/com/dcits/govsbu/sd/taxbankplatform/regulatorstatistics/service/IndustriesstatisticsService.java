package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.IndustriesdetailsEntity;

public interface IndustriesstatisticsService {
    List<IndustriesdetailsEntity> Industriesstatistics(Map<String, Object> parameters);

    List<IndustriesdetailsEntity> IndustriesstatisticsByMl(Map<String, Object> parameters);

    List<Map<String, Object>> industriesstatistics(Map<String, Object> parameters);

    //List<IndustriesdetailsEntity> searchIndustries();
    List<IndustriesdetailsEntity> searchIndustries();
 
    IndustriesdetailsEntity queryByHydm(String hydm);
}
