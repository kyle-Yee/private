package com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfNsqdEntity;

@Repository
public interface TaxPdfNsqdMapper extends BaseMapper<TaxPdfNsqdEntity, String> {
	//获取纳税清单
	public TaxPdfNsqdEntity findByDjxh();
	//获取增值税销售额分析
	public TaxPdfNsqdEntity findZesxsefx();
	//所得税营业额收入分析数据
	public TaxPdfNsqdEntity findSdsyyesrfx();
	//增值税纳税情况（增值税一般纳税人）
	public TaxPdfNsqdEntity findZzsns();
	//所得税月季纳税情况
	public TaxPdfNsqdEntity findSdsnsqk();
	//获取具体税种的清单
	public TaxPdfNsqdEntity findJtnsqd(String parameter);
	
	
	//下面是数据分析的存储过程
	//增值税纳税情况（增值税一般纳税人）
	public int zzsnsqkPrcdure(Map<String,Object> params); 
	//所得税月季纳税情况（A表） 
	public int sdsyjnsqkPrcdure(Map<String,Object> params);
	//纳税清单 
	public int nsqdPrcdure(Map<String,Object> params);
	//具体的税种入库
	public int jtszPrcdure(Map<String,Object> params);
	//所得税营业收入分析
	public int sdsyyefxPrcdure(Map<String,Object> params);
	//增值税销售额分析
	public int zzsxsefxPrcdure(Map<String,Object> params);
	//计算近两年的总纳税额度
	public BigDecimal znsedPrcdure(String sqxh);
	//删除PDF表信息
	public int deleteDxcznlfx(String djxh);
	public int deleteJtnsqd(String djxh);
	public int deleteNsqd(String djxh);
	public int deleteQsxx(String djxh);
	public int deleteSdsnsqk(String djxh);
	public int deleteSdsyyesrfx(String djxh);
	public int deleteYlnlfx(String djxh);
	public int deleteYynlfx(String djxh);
	public int deleteZzsns(String djxh);
	public int deleteZzsxsefx(String djxh);	
}
