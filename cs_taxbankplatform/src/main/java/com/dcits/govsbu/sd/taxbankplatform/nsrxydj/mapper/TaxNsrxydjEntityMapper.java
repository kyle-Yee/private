package com.dcits.govsbu.sd.taxbankplatform.nsrxydj.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.nsrxydj.model.TaxNsrxydjEntity;
@Repository
public interface TaxNsrxydjEntityMapper extends BaseMapper<TaxNsrxydjEntity, String> {

    @Override
	int insert(TaxNsrxydjEntity record);

    int insertSelective(TaxNsrxydjEntity record);

    TaxNsrxydjEntity selectByPrimaryKey(Long ttnId);

    int updateByPrimaryKeySelective(TaxNsrxydjEntity record);

    int updateByPrimaryKey(TaxNsrxydjEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}