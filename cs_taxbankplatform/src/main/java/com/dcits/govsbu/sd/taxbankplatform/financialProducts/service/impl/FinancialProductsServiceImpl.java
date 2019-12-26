/**
 * <p>Description: </p>
 * <p>versions:1.0 
 * <p>file name：FinancialProductsServiceImpl.java
 * <p>Company:dfwyBank</p>
 * <p>@author: Zhongyj 
 * <p>Created: 2018-7-9下午上午8:00:362:57:43 
 * <p>department:深圳IT部门  
 * <p>Copyright Copyright (c) dfwy. All rights reserved.</p>
 */
package com.dcits.govsbu.sd.taxbankplatform.financialProducts.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.financialProducts.mapper.FinancialProductsMapper;
import com.dcits.govsbu.sd.taxbankplatform.financialProducts.model.FinancialProductsEntity;
import com.dcits.govsbu.sd.taxbankplatform.financialProducts.service.FinancialProductsService;
import com.dcits.govsbu.sd.taxbankplatform.portmager.model.XlztEntity;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

/**
 * @versions:1.0 
 * @filename：FinancialProductsServiceImpl.java
 * @Company:dfwyBank
 * @Created: 2018-7-9下午上午8:00:362:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName FinancialProductsServiceImpl
 */
@Service("financialProductsService")
public class FinancialProductsServiceImpl extends AbstractService<FinancialProductsEntity, String>  implements FinancialProductsService {

	@Autowired
	private FinancialProductsMapper financialProductsMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(financialProductsMapper);
	}

	@Override
	public boolean insertOrgAndCp(FinancialProductsEntity financialProductsEntity)
			throws Exception {
		insertOrgAndCpA(financialProductsEntity);
		return true;
	}

	@Override
	public boolean updateOrgAndCp(FinancialProductsEntity financialProductsEntity)
			throws Exception {
		
		updateOrgAndCpA(financialProductsEntity);
		return true;
	}
	
	/************插入组织表和产品表****************/
	public void insertOrgAndCpA (FinancialProductsEntity financialProductsEntity) {
		String yhmc = "";
		String yhdm = "";
		String cpmc = "";
		String cpdm = "";
		String orgId = "";
 		Map<String, Object> paramaters = new HashMap<String, Object>();
 		if (financialProductsEntity != null) {
 			yhmc = financialProductsEntity.getYhmc();
 			
 			yhdm = financialProductsEntity.getYhdm();
 			
 			cpmc = financialProductsEntity.getCpmc();
 			
 			cpdm = financialProductsEntity.getCpdm();
 			
 			orgId = financialProductsEntity.getOrgId();
		}
		paramaters.put("orgname", yhmc);
		paramaters.put("orgcode", yhdm);
		paramaters.put("orgId", orgId);
		paramaters.put("cpmc", cpmc);
		paramaters.put("id", cpdm);
		financialProductsMapper.insertFinancialProduct(paramaters);
		financialProductsMapper.insertOrganizations(paramaters);
		
	}
	
	/*************更新组织表和产品表**************************/
	public void updateOrgAndCpA (FinancialProductsEntity financialProductsEntity) {
		String yhmc = "";
		String yhdm = "";
		String cpmc = "";
		String cpdm = "";
		String orgId = "";
		String cpdmL = "";
 		Map<String, Object> paramaters = new HashMap<String, Object>();
 		if (financialProductsEntity != null) {
 			yhmc = financialProductsEntity.getYhmc();
 			
 			yhdm = financialProductsEntity.getYhdm();
 			
 			cpmc = financialProductsEntity.getCpmc();
 			
 			cpdm = financialProductsEntity.getCpdm();
 			
 			orgId = financialProductsEntity.getOrgId();
 			
 			cpdmL = financialProductsEntity.getCpdmL();
		}
		paramaters.put("orgname", yhmc);
		paramaters.put("orgcode", yhdm);
		paramaters.put("orgId", orgId);
		paramaters.put("cpmc", cpmc);
		paramaters.put("cpdm", cpdm);
		paramaters.put("id", cpdmL);
		financialProductsMapper.updateFinancialProduct(paramaters);
		financialProductsMapper.updateOrganizations(paramaters);
		
	}

	@Override
	public FinancialProductsEntity findByCpDm(String cpDm) throws Exception {
		
		return financialProductsMapper.findByCpDm(cpDm);
	}
	
}
