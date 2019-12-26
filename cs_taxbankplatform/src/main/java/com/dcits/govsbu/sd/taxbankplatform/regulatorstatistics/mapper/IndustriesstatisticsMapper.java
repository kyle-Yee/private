package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.IndustriesdetailsEntity;

@Repository
public interface IndustriesstatisticsMapper extends BaseMapper<IndustriesdetailsEntity, String> {
    List<IndustriesdetailsEntity> Industriesstatistics(Map<String, Object> parameters);

    List<IndustriesdetailsEntity> IndustriesstatisticsByMl(Map<String, Object> parameters);

   
    List<IndustriesdetailsEntity> searchALlIndustries();
   
    IndustriesdetailsEntity queryByHydm(String hydm);

    List<Map<String, Object>> industriesstatistics(Map<String, Object> parameters);
}
