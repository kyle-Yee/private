/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.productfaq.mapper;

import org.apache.ibatis.annotations.Param;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.productfaq.model.ProductFaqEntity;

/**
 * @author 胡宝龙2016-8-11 下午3:09:41
 *
 */
public interface ProductFaqMapper extends BaseMapper<ProductFaqEntity, String >  {
	public int ProductFaq(@Param(value="id") String id);
}
