package com.dcits.govsbu.sd.taxbankplatform.sbxx.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.sbxx.model.TaxSbxxEntity;
@Repository
public interface TaxSbxxEntityMapper extends BaseMapper<TaxSbxxEntity, String> {

	    @Override
		int insert(TaxSbxxEntity record);

	    int insertSelective(TaxSbxxEntity record);

	    TaxSbxxEntity selectByPrimaryKey(Long ttsId);

	    int updateByPrimaryKeySelective(TaxSbxxEntity record);

	    int updateByPrimaryKey(TaxSbxxEntity record);
	  //根据djxh 删除纳税信息
		public int deleteByDjxh(String djxh);
}