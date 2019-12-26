package com.dcits.govsbu.sd.taxbankplatform.financialProduct.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.financialProduct.model.FinancialProduct;

/**
 * 理财产品服务
 * @author xudan
 *
 */
public interface FinancialProductService {
	/**
	 * 自定义方法
	 * 获取理财产品列表
	 * @param userId
	 * @return
	 */
	public List<FinancialProduct> queryListByPage(Map<String, Object> parameter);
	public List<FinancialProduct> checkName(Map<String, Object> parameter);//校验此产品名称是否已存在
	public int insert(FinancialProduct financialProduct);
	public FinancialProduct findById(String id);
	public int update(FinancialProduct financialProduct);
	/**
	 * 查询所有产品名
	 */
	List<FinancialProduct> selectAllName(Map<String, Object> parameter);
	/**
	 *  根据产品ID查询产品常见问题
	 */
   FinancialProduct selectfaqByfpId(FinancialProduct financialProduct);
}
