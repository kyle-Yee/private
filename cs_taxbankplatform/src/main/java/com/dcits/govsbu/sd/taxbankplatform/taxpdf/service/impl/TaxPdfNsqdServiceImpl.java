package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper.TaxPdfNsqdMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfNsqdEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfNsqdService;

@Service("taxPdfNsqdService")
public class TaxPdfNsqdServiceImpl extends AbstractService<TaxPdfNsqdEntity, String> implements TaxPdfNsqdService {
	@Autowired
	private TaxPdfNsqdMapper taxPdfNsqdMapper;
	

	public TaxPdfNsqdMapper getTaxPdfNsqdMapper() {
		return taxPdfNsqdMapper;
	}

	public void setTaxPdfNsqdMapper(TaxPdfNsqdMapper taxPdfNsqdMapper) {
		this.taxPdfNsqdMapper = taxPdfNsqdMapper;
	}

	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(taxPdfNsqdMapper);
	}
	
	@Override
	public TaxPdfNsqdEntity findByDjxh(){
		return taxPdfNsqdMapper.findByDjxh();
	}
	
	//获取增值税销售额分析
	@Override
	public TaxPdfNsqdEntity findZesxsefx(){
		return taxPdfNsqdMapper.findZesxsefx();
	}
	
	//所得税营业额收入分析数据
	@Override
	public TaxPdfNsqdEntity findSdsyyesrfx(){
		return taxPdfNsqdMapper.findSdsyyesrfx();
	}
	
	//增值税纳税情况（增值税一般纳税人）
	@Override
	public TaxPdfNsqdEntity findZzsns(){
		return taxPdfNsqdMapper.findZzsns();
	}
	
	//所得税月季纳税情况
	@Override
	public TaxPdfNsqdEntity findSdsnsqk(){
		return taxPdfNsqdMapper.findSdsnsqk();
	}
	//获取具体税种的清单
	@Override
	public TaxPdfNsqdEntity findJtnsqd(String parameter){
		return taxPdfNsqdMapper.findJtnsqd(parameter);
	}
	
	//下面是数据分析的存储过程
	@Override
	//增值税纳税情况（增值税一般纳税人）
	public int zzsnsqkPrcdure(Map<String,Object> params){
		try {
			return taxPdfNsqdMapper.zzsnsqkPrcdure(params);
		} catch (Exception e) {
			return 0;
		}
	}; 
	
	@Override
	//所得税月季纳税情况（A表） 
	public int sdsyjnsqkPrcdure(Map<String,Object> params){
		return taxPdfNsqdMapper.sdsyjnsqkPrcdure(params);
	}
	@Override
	//纳税清单 
	public int nsqdPrcdure(Map<String,Object> params){
		return taxPdfNsqdMapper.nsqdPrcdure(params);
	}
	@Override
	//具体的税种入库
	public int jtszPrcdure(Map<String,Object> params){
		return taxPdfNsqdMapper.jtszPrcdure(params);
	}
	@Override
	//所得税营业收入分析
	public int sdsyyefxPrcdure(Map<String,Object> params){
		return taxPdfNsqdMapper.sdsyyefxPrcdure(params);
	}
	@Override
	//增值税销售额分析
	public int zzsxsefxPrcdure(Map<String,Object> params){
		return taxPdfNsqdMapper.zzsxsefxPrcdure(params);
	}
	@Override
	//计算近两年的总纳税额度
	public BigDecimal znsedPrcdure(String sqxh){
		return taxPdfNsqdMapper.znsedPrcdure(sqxh);
	}
	//删除PDF表信息
	@Override
	public int deleteDxcznlfx(String djxh) {
		return taxPdfNsqdMapper.deleteDxcznlfx(djxh);
	}
	@Override
	public int deleteJtnsqd(String djxh) {
		return taxPdfNsqdMapper.deleteJtnsqd(djxh);
	}
	@Override
	public int deleteNsqd(String djxh) {
		return taxPdfNsqdMapper.deleteNsqd(djxh);
	}
	@Override
	public int deleteQsxx(String djxh) {
		return taxPdfNsqdMapper.deleteQsxx(djxh);
	}
	@Override
	public int deleteSdsnsqk(String djxh) {
		return taxPdfNsqdMapper.deleteSdsnsqk(djxh);
	}
	@Override
	public int deleteSdsyyesrfx(String djxh) {
		return taxPdfNsqdMapper.deleteSdsyyesrfx(djxh);
	}
	@Override
	public int deleteYlnlfx(String djxh) {
		return taxPdfNsqdMapper.deleteYlnlfx(djxh);
	}
	@Override
	public int deleteYynlfx(String djxh) {
		return taxPdfNsqdMapper.deleteYynlfx(djxh);
	}
	@Override
	public int deleteZzsns(String djxh) {
		return taxPdfNsqdMapper.deleteZzsns(djxh);
	}
	@Override
	public int deleteZzsxsefx(String djxh) {
		return taxPdfNsqdMapper.deleteZzsxsefx(djxh);
	}
}
