package com.dcits.govsbu.sd.taxbankplatform.sswfxwdj.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.sswfxwdj.model.TaxSswfxwdjEntity;
@Repository
public interface TaxSswfxwdjEntityMapper extends BaseMapper<TaxSswfxwdjEntity, String> {

    @Override
	int insert(TaxSswfxwdjEntity record);

    int insertSelective(TaxSswfxwdjEntity record);

    TaxSswfxwdjEntity selectByPrimaryKey(Long ttsId);

    int updateByPrimaryKeySelective(TaxSswfxwdjEntity record);

    int updateByPrimaryKey(TaxSswfxwdjEntity record);
  //根据djxh 删除纳税信息
  	public int deleteByDjxh(String djxh);
}