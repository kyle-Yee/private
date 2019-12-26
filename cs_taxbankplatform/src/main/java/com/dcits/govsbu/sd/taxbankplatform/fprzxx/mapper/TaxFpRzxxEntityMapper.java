package com.dcits.govsbu.sd.taxbankplatform.fprzxx.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.fprzxx.model.TaxFpRzxxEntity;

@Repository
public interface TaxFpRzxxEntityMapper extends BaseMapper<TaxFpRzxxEntity, String> {

    @Override
	int insert(TaxFpRzxxEntity record);

    int insertSelective(TaxFpRzxxEntity record);

    TaxFpRzxxEntity selectByPrimaryKey(Long ttfzId);

    int updateByPrimaryKeySelective(TaxFpRzxxEntity record);

    int updateByPrimaryKey(TaxFpRzxxEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}