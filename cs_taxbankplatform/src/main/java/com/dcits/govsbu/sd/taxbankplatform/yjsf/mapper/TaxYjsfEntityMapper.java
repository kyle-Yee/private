package com.dcits.govsbu.sd.taxbankplatform.yjsf.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.yjsf.model.TaxYjsfEntity;
@Repository
public interface TaxYjsfEntityMapper extends BaseMapper<TaxYjsfEntity, String> {

	    @Override
		int insert(TaxYjsfEntity record);

	    int insertSelective(TaxYjsfEntity record);

	    TaxYjsfEntity selectByPrimaryKey(Long ttyId);

	    int updateByPrimaryKeySelective(TaxYjsfEntity record);

	    int updateByPrimaryKey(TaxYjsfEntity record);
	  //根据djxh 删除纳税信息
		public int deleteByDjxh(String djxh);
}