package com.dcits.govsbu.sd.taxbankplatform.financialProduct.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.model.FinancialProduct;

@Repository 
public interface FinancialProductMapper extends BaseMapper<FinancialProduct, String>{
    
	int insertSelective(FinancialProduct record);
	int updateFaq(FinancialProduct financialProduct);
	
	/**
	 * 查询所有产品名
	 */
	List<FinancialProduct> selectAllName(Map<String, Object> parameter);
	List<FinancialProduct> checkName(Map<String, Object> parameter);//校验此产品名称是否已存在
	/**
	 *  根据产品ID查询产品常见问题
	 */
   FinancialProduct selectfaqByfpId(FinancialProduct financialProduct);
  
}