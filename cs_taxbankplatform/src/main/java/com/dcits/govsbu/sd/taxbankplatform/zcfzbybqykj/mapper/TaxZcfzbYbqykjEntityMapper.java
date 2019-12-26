package com.dcits.govsbu.sd.taxbankplatform.zcfzbybqykj.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.zcfzbybqykj.model.TaxZcfzbYbqykjEntity;
@Repository
public interface TaxZcfzbYbqykjEntityMapper extends BaseMapper<TaxZcfzbYbqykjEntity, String> {

    @Override
	int insert(TaxZcfzbYbqykjEntity record);

    int insertSelective(TaxZcfzbYbqykjEntity record);

    @Override
	TaxZcfzbYbqykjEntity findById(String ttzyId);

    int updateByPrimaryKeySelective(TaxZcfzbYbqykjEntity record);

    int updateByPrimaryKey(TaxZcfzbYbqykjEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}