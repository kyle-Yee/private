package com.dcits.govsbu.sd.taxbankplatform.bmyxcx.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.bmyxcx.model.TaxBmyxcxEntity;

@Repository
public interface TaxBmyxcxEntityMapper extends BaseMapper<TaxBmyxcxEntity, String> {

    @Override
	int insert(TaxBmyxcxEntity record);

    int insertSelective(TaxBmyxcxEntity record);

    TaxBmyxcxEntity selectByPrimaryKey(String ttbId);

    int updateByPrimaryKeySelective(TaxBmyxcxEntity record);

    int updateByPrimaryKey(TaxBmyxcxEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}