package com.dcits.govsbu.sd.taxbankplatform.nsrzgxxjgb.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.nsrzgxxjgb.model.TaxNsrzgxxjgbEntity;
@Repository
public interface TaxNsrzgxxjgbEntityMapper extends BaseMapper<TaxNsrzgxxjgbEntity, String> {

    @Override
	int insert(TaxNsrzgxxjgbEntity record);

    int insertSelective(TaxNsrzgxxjgbEntity record);

    TaxNsrzgxxjgbEntity selectByPrimaryKey(Long ttnId);

    int updateByPrimaryKeySelective(TaxNsrzgxxjgbEntity record);

    int updateByPrimaryKey(TaxNsrzgxxjgbEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(Long djxh);
}