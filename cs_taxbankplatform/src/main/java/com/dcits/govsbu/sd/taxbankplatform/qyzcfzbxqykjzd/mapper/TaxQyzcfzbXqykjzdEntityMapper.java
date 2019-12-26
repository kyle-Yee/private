package com.dcits.govsbu.sd.taxbankplatform.qyzcfzbxqykjzd.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.qyzcfzbxqykjzd.model.TaxQyzcfzbXqykjzdEntity;
@Repository
public interface TaxQyzcfzbXqykjzdEntityMapper extends BaseMapper<TaxQyzcfzbXqykjzdEntity, String> {

	    @Override
		int insert(TaxQyzcfzbXqykjzdEntity record);

	    int insertSelective(TaxQyzcfzbXqykjzdEntity record);

	    TaxQyzcfzbXqykjzdEntity selectByPrimaryKey(Long ttqxId);

	    int updateByPrimaryKeySelective(TaxQyzcfzbXqykjzdEntity record);

	    int updateByPrimaryKey(TaxQyzcfzbXqykjzdEntity record);
	  //根据djxh 删除纳税信息
		public int deleteByDjxh(String djxh);
}