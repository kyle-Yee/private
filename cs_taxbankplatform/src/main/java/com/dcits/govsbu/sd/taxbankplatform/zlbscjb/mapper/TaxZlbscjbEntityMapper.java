package com.dcits.govsbu.sd.taxbankplatform.zlbscjb.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.zlbscjb.model.TaxZlbscjbEntity;

@Repository
public interface TaxZlbscjbEntityMapper extends BaseMapper<TaxZlbscjbEntity, String> {
   @Override
public TaxZlbscjbEntity findById(String id);
   //根据djxh 删除纳税信息
   public int deleteByDjxh(String djxh);
}