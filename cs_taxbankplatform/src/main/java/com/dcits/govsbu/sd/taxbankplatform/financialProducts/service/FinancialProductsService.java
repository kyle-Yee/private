/**
 * <p>Description: </p>
 * <p>versions:1.0 
 * <p>file name：FinancialProductsService.java
 * <p>Company:dfwyBank</p>
 * <p>@author: Zhongyj 
 * <p>Created: 2018-7-9下午上午7:59:502:57:43 
 * <p>department:深圳IT部门  
 * <p>Copyright Copyright (c) dfwy. All rights reserved.</p>
 */
package com.dcits.govsbu.sd.taxbankplatform.financialProducts.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.financialProducts.model.FinancialProductsEntity;
import com.dcits.govsbu.sd.taxbankplatform.portmager.model.PortmagerEntity;

/**
 * @versions:1.0 
 * @filename：FinancialProductsService.java
 * @Company:dfwyBank
 * @Created: 2018-7-9下午上午7:59:502:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName FinancialProductsService
 */
public interface FinancialProductsService {
	/***查询银行金融产品列表数据***/
	public List<FinancialProductsEntity> queryListByPage(Map<String, Object> parameters) throws Exception;
	/*********将组织和产品信息插入到对应的表中**************/
	public boolean insertOrgAndCp(FinancialProductsEntity financialProductsEntity) throws Exception;
	/***********更新组织和产品表************/
	public boolean updateOrgAndCp(FinancialProductsEntity financialProductsEntity) throws Exception;
	/********编辑时反写该条数据到页面上****************/
    public FinancialProductsEntity findByCpDm(String cpDm) throws Exception;
}
