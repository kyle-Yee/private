/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.productfaq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.productfaq.mapper.ProductFaqMapper;
import com.dcits.govsbu.sd.taxbankplatform.productfaq.model.ProductFaqEntity;
import com.dcits.govsbu.sd.taxbankplatform.productfaq.service.ProductFaqService;

/**
 * @author 胡宝龙2016-8-11 下午3:15:31
 *
 */
@Service("productFaqService")
public class ProductFaqServerImpl  extends AbstractService<ProductFaqEntity, String> implements ProductFaqService{
	@Autowired
	private ProductFaqMapper productFaqMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(productFaqMapper);
	}
}
