package com.dcits.govsbu.sd.taxbankplatform.productfaq.mapper;

import com.dcits.govsbu.sd.taxbankplatform.productfaq.model.TbProductFaq;

public interface TbProductFaqMapper {
    int deleteByPrimaryKey(Integer pfId);

    int insert(TbProductFaq record);

    int insertSelective(TbProductFaq record);

    TbProductFaq selectByPrimaryKey(Integer pfId);

    int updateByPrimaryKeySelective(TbProductFaq record);

    int updateByPrimaryKeyWithBLOBs(TbProductFaq record);

    int updateByPrimaryKey(TbProductFaq record);
}