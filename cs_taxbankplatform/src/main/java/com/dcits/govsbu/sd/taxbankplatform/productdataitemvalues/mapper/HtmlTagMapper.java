package com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.mapper;

import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.HtmlTag;

public interface HtmlTagMapper  extends BaseMapper<HtmlTag, String> {
    int deleteByPrimaryKey(String htId);

    @Override
	int insert(HtmlTag record);

    int insertSelective(HtmlTag record);

    HtmlTag selectByPrimaryKey(String htId);

    int updateByPrimaryKeySelective(HtmlTag record);

    int updateByPrimaryKey(HtmlTag record);
    List<HtmlTag> select();
}