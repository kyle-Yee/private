package com.dcits.govsbu.sd.taxbankplatform.lrbxqykjzd.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.lrbxqykjzd.model.TaxLrbXqykjzdEntity;

@Repository
public interface TaxLrbXqykjzdEntityMapper extends BaseMapper<TaxLrbXqykjzdEntity, String> {

    @Override
	int insert(TaxLrbXqykjzdEntity record);

    int insertSelective(TaxLrbXqykjzdEntity record);

    TaxLrbXqykjzdEntity selectByPrimaryKey(Long ttlxId);

    int updateByPrimaryKeySelective(TaxLrbXqykjzdEntity record);

    int updateByPrimaryKey(TaxLrbXqykjzdEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}