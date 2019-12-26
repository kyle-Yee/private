package com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfBggsEntity;

@Repository
public interface TaxPdfBggsMapper extends BaseMapper<TaxPdfBggsEntity, String> {
	public List<TaxPdfBggsEntity> findByDjxh(Map<String,Object> params);
	//查询近两年总纳税额度
	public BigDecimal findZnsed(String sqxh);
	//根据行业代码获取企业的行业信息
	public TaxPdfBggsEntity findHyxx(String hydm);
	//根据申请人id 获取纳税人id
	public String findUserIdById(String id);
	//获取湖南接口数据最新申请序号
	public String findHNSqxh();
}
