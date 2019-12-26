package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper.TaxPdfBggsMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfBggsEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfBggsService;

@Service("taxPdfBggsService")
public class TaxPdfBggsServiceImpl extends AbstractService<TaxPdfBggsEntity, String> implements TaxPdfBggsService {
	@Autowired
	private TaxPdfBggsMapper taxPdfBggsMapper;
	

	public TaxPdfBggsMapper getTaxPdfBggsMapper() {
		return taxPdfBggsMapper;
	}

	public void setTaxPdfBggsMapper(TaxPdfBggsMapper taxPdfBggsMapper) {
		this.taxPdfBggsMapper = taxPdfBggsMapper;
	}

	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(taxPdfBggsMapper);
	}
	
	@Override
	public List<TaxPdfBggsEntity> findByDjxh(Map<String,Object> params){
		return taxPdfBggsMapper.findByDjxh(params);
	}
	
	//查询近两年总纳税额度
	@Override
	public BigDecimal findZnsed(String sqxh){
		return taxPdfBggsMapper.findZnsed(sqxh);
	}
	@Override
	//根据行业代码获取企业的行业信息
	public TaxPdfBggsEntity findHyxx(String hydm){
		return taxPdfBggsMapper.findHyxx(hydm);
	}
	@Override
	//根据申请人id 获取纳税人id
	public String findUserIdById(String id){
		return taxPdfBggsMapper.findUserIdById(id);
	}
	@Override
	//获取湖南接口数据最新申请序号
	public String findHNSqxh(){
		return taxPdfBggsMapper.findHNSqxh();
	}
}
