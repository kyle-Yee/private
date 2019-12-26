package com.dcits.govsbu.sd.taxbankplatform.fpkjxx.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.fpkjxx.model.TaxFpKjxxEntity;

@Repository
public interface TaxFpKjxxEntityMapper extends BaseMapper<TaxFpKjxxEntity, String> {

    @Override
	int insert(TaxFpKjxxEntity record);

    int insertSelective(TaxFpKjxxEntity record);

    TaxFpKjxxEntity selectByPrimaryKey(Long ttfId);

    int updateByPrimaryKeySelective(TaxFpKjxxEntity record);

    int updateByPrimaryKey(TaxFpKjxxEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}