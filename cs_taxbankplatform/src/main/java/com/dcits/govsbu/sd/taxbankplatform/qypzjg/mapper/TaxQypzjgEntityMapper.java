package com.dcits.govsbu.sd.taxbankplatform.qypzjg.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.qypzjg.model.TaxQypzjgEntity;
@Repository
public interface TaxQypzjgEntityMapper extends BaseMapper<TaxQypzjgEntity, String> {

    @Override
	int insert(TaxQypzjgEntity record);

    int insertSelective(TaxQypzjgEntity record);

    TaxQypzjgEntity selectByPrimaryKey(Long tthqId);

    int updateByPrimaryKeySelective(TaxQypzjgEntity record);

    int updateByPrimaryKey(TaxQypzjgEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}