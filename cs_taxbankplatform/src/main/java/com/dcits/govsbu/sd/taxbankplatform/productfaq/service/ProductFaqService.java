/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.productfaq.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.productfaq.model.ProductFaqEntity;

/**
 * @author 胡宝龙2016-8-11 下午3:11:19
 *
 */
public interface ProductFaqService {
	public List<ProductFaqEntity> queryListByPage(Map<String, Object> parameter);
	
	public int insert(ProductFaqEntity productFaqEntity);
	
	public ProductFaqEntity findById(String id);

	public int update(ProductFaqEntity productFaqEntity);
    
    public int deleteBatchById(List<String> ids);
}
