package com.dcits.govsbu.sd.taxbankplatform.qyzfjgxx.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.qyzfjgxx.model.TaxQyzfjgxxEntity;
@Repository
public interface TaxQyzfjgxxEntityMapper extends BaseMapper<TaxQyzfjgxxEntity, String> {

    @Override
	int insert(TaxQyzfjgxxEntity record);

    int insertSelective(TaxQyzfjgxxEntity record);

    TaxQyzfjgxxEntity selectByPrimaryKey(Long tthzfId);

    int updateByPrimaryKeySelective(TaxQyzfjgxxEntity record);

    int updateByPrimaryKey(TaxQyzfjgxxEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}