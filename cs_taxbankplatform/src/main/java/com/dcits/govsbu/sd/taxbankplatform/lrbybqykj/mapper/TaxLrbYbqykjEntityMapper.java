package com.dcits.govsbu.sd.taxbankplatform.lrbybqykj.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.lrbybqykj.model.TaxLrbYbqykjEntity;
@Repository
public interface TaxLrbYbqykjEntityMapper extends BaseMapper<TaxLrbYbqykjEntity, String> {

    @Override
	int insert(TaxLrbYbqykjEntity record);

    int insertSelective(TaxLrbYbqykjEntity record);

    @Override
	public TaxLrbYbqykjEntity findById(String ttlxId);

    int updateByPrimaryKeySelective(TaxLrbYbqykjEntity record);

    int updateByPrimaryKey(TaxLrbYbqykjEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}