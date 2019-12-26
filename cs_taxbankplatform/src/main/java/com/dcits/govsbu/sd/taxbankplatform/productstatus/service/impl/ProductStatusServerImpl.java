/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.productstatus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.productstatus.model.ProductStatusEntity;
import com.dcits.govsbu.sd.taxbankplatform.productstatus.service.ProductStatusService;
import com.dcits.govsbu.sd.taxbankplatform.productstatus.mapper.ProductStatusMapper;

/**
 * @author 胡宝龙2016-8-11 上午11:12:19
 *
 */
@Service("productStatusService")
public class ProductStatusServerImpl extends AbstractService<ProductStatusEntity, String> implements ProductStatusService{
	@Autowired
	private ProductStatusMapper productStatusMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(productStatusMapper);
	}
}