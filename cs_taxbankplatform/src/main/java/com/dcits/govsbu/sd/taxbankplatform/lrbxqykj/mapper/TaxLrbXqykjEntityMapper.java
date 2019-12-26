package com.dcits.govsbu.sd.taxbankplatform.lrbxqykj.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.lrbxqykj.model.TaxLrbXqykjEntity;

/**
 * 
 * @author Administrator
 *
 */
@Repository
public interface TaxLrbXqykjEntityMapper extends BaseMapper<TaxLrbXqykjEntity, String> {

    @Override
	int insert(TaxLrbXqykjEntity record);

    int insertSelective(TaxLrbXqykjEntity record);

    TaxLrbXqykjEntity selectByPrimaryKey(Long ttlxId);

    int updateByPrimaryKeySelective(TaxLrbXqykjEntity record);

    int updateByPrimaryKey(TaxLrbXqykjEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}