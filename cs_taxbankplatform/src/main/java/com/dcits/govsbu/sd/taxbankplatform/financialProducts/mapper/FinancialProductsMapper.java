/**
 * <p>Description: </p>
 * <p>versions:1.0 
 * <p>file name：FinancialProductsmapper.java
 * <p>Company:dfwyBank</p>
 * <p>@author: Zhongyj 
 * <p>Created: 2018-7-9下午上午8:12:162:57:43 
 * <p>department:深圳IT部门  
 * <p>Copyright Copyright (c) dfwy. All rights reserved.</p>
 */
package com.dcits.govsbu.sd.taxbankplatform.financialProducts.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.financialProducts.model.FinancialProductsEntity;
import com.dcits.govsbu.sd.taxbankplatform.portmager.model.PortmagerEntity;

/**
 * @versions:1.0 
 * @filename：FinancialProductsmapper.java
 * @Company:dfwyBank
 * @Created: 2018-7-9下午上午8:12:162:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName FinancialProductsmapper
 */
@Repository
public interface FinancialProductsMapper extends BaseMapper<FinancialProductsEntity, String> {
	
	/***插入组织表数据***/
	public int insertOrganizations(Map<String,Object> paramaters);
	
	/***更新组织表数据***/
	public int updateOrganizations(Map<String,Object> paramaters);
	
	/***插入金融产品表数据***/
	public int insertFinancialProduct(Map<String,Object> paramaters);
	
	/***更新金融产品表数据***/
	public int updateFinancialProduct(Map<String,Object> paramaters);
	/********编辑时反写该条数据到页面上****************/
	public FinancialProductsEntity findByCpDm(String cpDm);
	
}
