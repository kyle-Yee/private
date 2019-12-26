package com.dcits.govsbu.sd.taxbankplatform.qytzf.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.qytzf.model.TaxQytzfEntity;
@Repository
public interface TaxQytzfEntityMapper extends BaseMapper<TaxQytzfEntity, String> {

    @Override
	int insert(TaxQytzfEntity record);

    int insertSelective(TaxQytzfEntity record);

    TaxQytzfEntity selectByPrimaryKey(Long tthyId);

    int updateByPrimaryKeySelective(TaxQytzfEntity record);

    int updateByPrimaryKey(TaxQytzfEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}