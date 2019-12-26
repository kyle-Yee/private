package com.dcits.govsbu.sd.taxbankplatform.ybnsrsbmxzb.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.ybnsrsbmxzb.model.TaxYbnsrsbmxzbEntity;
@Repository
public interface TaxYbnsrsbmxzbEntityMapper extends BaseMapper<TaxYbnsrsbmxzbEntity, String> {

	    @Override
		int insert(TaxYbnsrsbmxzbEntity record);

	    int insertSelective(TaxYbnsrsbmxzbEntity record);

	    TaxYbnsrsbmxzbEntity selectByPrimaryKey(Long ttybId);

	    int updateByPrimaryKeySelective(TaxYbnsrsbmxzbEntity record);

	    int updateByPrimaryKey(TaxYbnsrsbmxzbEntity record);
	  //根据djxh 删除纳税信息
		public int deleteByDjxh(String djxh);
}