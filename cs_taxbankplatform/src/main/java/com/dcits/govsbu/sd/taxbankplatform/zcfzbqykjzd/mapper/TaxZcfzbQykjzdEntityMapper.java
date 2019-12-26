package com.dcits.govsbu.sd.taxbankplatform.zcfzbqykjzd.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.zcfzbqykjzd.model.TaxZcfzbQykjzdEntity;
@Repository
public interface TaxZcfzbQykjzdEntityMapper extends BaseMapper<TaxZcfzbQykjzdEntity, String> {

    @Override
	int insert(TaxZcfzbQykjzdEntity record);

    int insertSelective(TaxZcfzbQykjzdEntity record);

    TaxZcfzbQykjzdEntity selectByPrimaryKey(String ttzqId);

    int updateByPrimaryKeySelective(TaxZcfzbQykjzdEntity record);

    int updateByPrimaryKey(TaxZcfzbQykjzdEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}