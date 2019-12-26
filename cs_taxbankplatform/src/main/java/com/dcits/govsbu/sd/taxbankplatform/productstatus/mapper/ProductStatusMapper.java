package com.dcits.govsbu.sd.taxbankplatform.productstatus.mapper;

import org.apache.ibatis.annotations.Param;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.productstatus.model.ProductStatusEntity;

/**
 * @author 胡宝龙2016-8-11 上午11:06:29
 *
 */
public interface ProductStatusMapper extends BaseMapper<ProductStatusEntity, String >  {
	public int delProductStatus(@Param(value="id") String id);
}
