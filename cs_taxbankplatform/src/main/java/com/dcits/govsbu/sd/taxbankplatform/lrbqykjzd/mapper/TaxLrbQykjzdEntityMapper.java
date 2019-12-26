package com.dcits.govsbu.sd.taxbankplatform.lrbqykjzd.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.lrbqykjzd.model.TaxLrbQykjzdEntity;

/**
 * 
 * @author Administrator
 *
 */
@Repository
public interface TaxLrbQykjzdEntityMapper extends BaseMapper<TaxLrbQykjzdEntity, String> {

    @Override
	int insert(TaxLrbQykjzdEntity record);

    int insertSelective(TaxLrbQykjzdEntity record);

    TaxLrbQykjzdEntity selectByPrimaryKey(Long ttlqId);

    int updateByPrimaryKeySelective(TaxLrbQykjzdEntity record);

    int updateByPrimaryKey(TaxLrbQykjzdEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}