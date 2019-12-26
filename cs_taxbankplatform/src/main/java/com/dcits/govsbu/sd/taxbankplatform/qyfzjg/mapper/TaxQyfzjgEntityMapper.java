package com.dcits.govsbu.sd.taxbankplatform.qyfzjg.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.qyfzjg.model.TaxQyfzjgEntity;
@Repository
public interface TaxQyfzjgEntityMapper extends BaseMapper<TaxQyfzjgEntity, String> {

    @Override
	int insert(TaxQyfzjgEntity record);

    int insertSelective(TaxQyfzjgEntity record);

    TaxQyfzjgEntity selectByPrimaryKey(Long tthfId);

    int updateByPrimaryKeySelective(TaxQyfzjgEntity record);

    int updateByPrimaryKey(TaxQyfzjgEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}