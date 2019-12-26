package com.dcits.govsbu.sd.taxbankplatform.financialProduct.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.mapper.FinancialProductMapper;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.model.FinancialProduct;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.service.FinancialProductService;

/**
* 理财产品服务
* @author xudan
*
*/
@Service("financialProductService")
public class FinancialProductServiceImpl extends AbstractService<FinancialProduct, String> implements FinancialProductService{

	@Autowired
	private FinancialProductMapper financialProductMapper;
	
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为financialProductMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(financialProductMapper);
	}
	@Override
	public List<FinancialProduct> selectAllName(Map<String, Object> parameter) {
		return financialProductMapper.selectAllName(parameter);
	}
	@Override
	public FinancialProduct selectfaqByfpId(FinancialProduct financialProduct) {
		return financialProductMapper.selectfaqByfpId(financialProduct);
	}
	@Override
	public List<FinancialProduct> checkName(Map<String, Object> parameter) {
		return financialProductMapper.checkName(parameter);
	}
}
