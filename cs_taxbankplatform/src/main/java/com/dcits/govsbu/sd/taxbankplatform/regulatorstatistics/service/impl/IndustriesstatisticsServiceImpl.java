package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.mapper.IndustriesstatisticsMapper;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.IndustriesdetailsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.IndustriesstatisticsService;

@Service("industriesstatisticsService")
public class IndustriesstatisticsServiceImpl extends AbstractService<IndustriesdetailsEntity, String> implements IndustriesstatisticsService {

    @Autowired
    private IndustriesstatisticsMapper industriesstatisticsMapper;

    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(industriesstatisticsMapper);
    }

    @Override
    public List<IndustriesdetailsEntity> Industriesstatistics(
            Map<String, Object> parameters) {
        List<IndustriesdetailsEntity> list = industriesstatisticsMapper.Industriesstatistics(parameters);
        for (IndustriesdetailsEntity ie : list) {
            if (ie.getHymc() == null) {
                ie.setHymc("其他");
            }
        }
        return list;
    }

  
    @Override
    public IndustriesdetailsEntity queryByHydm(String hydm) {
        IndustriesdetailsEntity hyxx = industriesstatisticsMapper.queryByHydm(hydm);
        return hyxx;
    }
    @Override
    public List<Map<String, Object>> industriesstatistics(Map<String, Object> parameters) {
        List<Map<String, Object>> list = industriesstatisticsMapper.industriesstatistics(parameters);

        return list;
    }
    @Override
    public List<IndustriesdetailsEntity> IndustriesstatisticsByMl(
            Map<String, Object> parameters) {
        List<IndustriesdetailsEntity> list = industriesstatisticsMapper.IndustriesstatisticsByMl(parameters);
        for (IndustriesdetailsEntity ie : list) {
            if (ie.getHymc() == null) {
                ie.setHymc("其他");
            }
        }
        return list;
    }
    
    @Override
    public List<IndustriesdetailsEntity> searchIndustries() {
        List<IndustriesdetailsEntity> list = industriesstatisticsMapper.searchALlIndustries();
        return list;
    }
    
}
