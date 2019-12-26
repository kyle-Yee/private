package com.dcits.govsbu.sd.taxbankplatform.xgmnsrsbmx.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.xgmnsrsbmx.model.TaxXgmnsrsbmxEntity;
@Repository
public interface TaxXgmnsrsbmxEntityMapper extends BaseMapper<TaxXgmnsrsbmxEntity, String> {

	    @Override
		int insert(TaxXgmnsrsbmxEntity record);

	    int insertSelective(TaxXgmnsrsbmxEntity record);

	    TaxXgmnsrsbmxEntity selectByPrimaryKey(Long ttxId);

	    int updateByPrimaryKeySelective(TaxXgmnsrsbmxEntity record);

	    int updateByPrimaryKey(TaxXgmnsrsbmxEntity record);
	  //根据djxh 删除纳税信息
		public int deleteByDjxh(String djxh);
}