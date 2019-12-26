package com.dcits.govsbu.sd.taxbankplatform.ajxx.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.ajxx.model.TaxAjxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
/**
 * 
 * @author Administrator
 *
 */
@Repository
public interface TaxAjxxEntityMapper extends BaseMapper<TaxAjxxEntity, String> {

    @Override
	int insert(TaxAjxxEntity record);

    int insertSelective(TaxAjxxEntity record);

    TaxAjxxEntity selectByPrimaryKey(String ttaId);

    int updateByPrimaryKeySelective(TaxAjxxEntity record);

    int updateByPrimaryKey(TaxAjxxEntity record);
    //根据djxh 删除纳税信息wqwq
  	public int deleteByDjxh(String djxh);
  	
  	public List<TaxAjxxEntity> adderWithParameterMap(Map<String, Object> parameter);
}