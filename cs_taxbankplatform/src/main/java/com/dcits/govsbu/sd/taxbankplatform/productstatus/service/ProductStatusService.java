/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.productstatus.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.productstatus.model.ProductStatusEntity;


/**
 * @author 胡宝龙2016-8-11 上午11:10:10
 *
 */
public interface ProductStatusService {
	public List<ProductStatusEntity> queryListByPage(Map<String, Object> parameter);
	
	public int insert(ProductStatusEntity productStatusEntity);
	
	public ProductStatusEntity findById(String id);

	public int update(ProductStatusEntity productStatusEntity);
    
    public int deleteBatchById(List<String> citiesids);
}
