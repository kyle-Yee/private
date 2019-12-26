package com.dcits.govsbu.sd.taxbankplatform.qsclxx.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.qsclxx.model.TaxQsclxxEntity;
@Repository
public interface TaxQsclxxEntityMapper extends BaseMapper<TaxQsclxxEntity, String> {

    @Override
	int insert(TaxQsclxxEntity record);

    int insertSelective(TaxQsclxxEntity record);

    TaxQsclxxEntity selectByPrimaryKey(Long ttqId);

    int updateByPrimaryKeySelective(TaxQsclxxEntity record);

    int updateByPrimaryKey(TaxQsclxxEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}