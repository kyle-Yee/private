package com.dcits.govsbu.sd.taxbankplatform.qybgxx.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.qybgxx.model.TaxQybgxxEntity;
@Repository
public interface TaxQybgxxEntityMapper extends BaseMapper<TaxQybgxxEntity, String> {

    @Override
	int insert(TaxQybgxxEntity record);

    int insertSelective(TaxQybgxxEntity record);

    TaxQybgxxEntity selectByPrimaryKey(Long tthbId);

    int updateByPrimaryKeySelective(TaxQybgxxEntity record);

    int updateByPrimaryKey(TaxQybgxxEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}