package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.mapper.ProductstatisticsMapper;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.GuaranteeStyleEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.ProductstatisticsService;

@Service("productstatisticsService")
public class ProductstatisticsServiceImpl extends AbstractService<BanklistInfoEntity, String> implements ProductstatisticsService {

    @Autowired
    private ProductstatisticsMapper productstatisticsMapper;

    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(productstatisticsMapper);
    }

    @Override
    public List<BanklistInfoEntity> productstatistics(
            Map<String, Object> parameters) {
        List<BanklistInfoEntity> productstatistics = productstatisticsMapper.productstatistics(parameters);
        for (BanklistInfoEntity bi : productstatistics) {
            if (bi.getCgsxbs() == null) {
                bi.setCgsxbs("0");
            }
            if (bi.getCgsxed() == null) {
                bi.setCgsxed("0");
            }
            if (bi.getBll() == null) {
                bi.setBll("0%");
            } else {
                bi.setBll(bi.getBll() + "%");
            }
        }
        return productstatistics;
    }

    @Override
    public List<Map<String, Object>> productstatistics_new(Map<String, Object> parameters) {
        List<Map<String, Object>> list = productstatisticsMapper.productstatistics_new(parameters);

        return list;
    }

    @Override
    public List<GuaranteeStyleEntity> findgsById(String id) {
        return productstatisticsMapper.findgsById(id);
    }

}
