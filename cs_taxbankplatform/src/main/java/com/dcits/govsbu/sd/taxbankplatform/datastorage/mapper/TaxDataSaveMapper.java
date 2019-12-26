package com.dcits.govsbu.sd.taxbankplatform.datastorage.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;

/**
 * 数据保存Mapper接口
 * @author Administrator
 */
@Repository
public interface TaxDataSaveMapper extends BaseMapper<Map<String, String>, String> {
	
	//保存企业基础信息
	public int insertTaxNsrjcxx(Map<String, String> map);
	
	//保持企业基础信息扩展
	public int insertTaxNsrxxKz(Map<String, String> map);
	
	//保存企业批准机构信息
	public int insertTaxPzjgxx(Map<String, String> map);
	
	//保存企业变更信息
	public int insertTaxBgdjmx(Map<String, String> map);
	
	//保存企业投资方信息
	public int insertTaxTzfxx(Map<String, String> map);
	
	//保存企业分支机构信息
	public int insertTaxFzjgxx(Map<String, String> map);
	
	//保存企业总分机构信息
	public int insertTaxZzjgxx(Map<String, String> map);
	
	//保存企业违法违章信息
	public int insertTaxSswfxwdj(Map<String, String> map);
	
	//保存企业稽查案件信息
	public int insertTaxAjxx(Map<String, String> map);
	
	//保存企业申报信息
	public int insertTaxSbxx(Map<String, String> map);
	
	//保存企业征收信息
	public int insertTaxYjsf(Map<String, String> map);
	
	//保存企业资产负债表(小企业会计制度)
	public int insertTaxXqyzcfzb(Map<String, String> map);
	
	//保存企业利润表(小企业会计制度)
	public int insertTaxXqylrb(Map<String, String> map);
	
	//保存企业发票开具
	public int insertTaxFpkj(Map<String, String> map);
	
	//保存企业认证发票
	public int insertTaxRzfp(Map<String, String> map);
	
	//保存一般纳税人申报明细主表
	public int insertTaxYnbsr(Map<String, String> map);
	
	//保存小规模纳税人申报明细主表
	public int insertTaxXgmnsr(Map<String, String> map);
	
	//保存纳税人资格信息结果表
	public int insertTaxNsrzgxxjgb(Map<String, String> map);
	
	//保存稽查案源信息
	public int insertTaxAyxx(Map<String, String> map);
	
	//保存欠税信息
	public int insertTaxQsclxx(Map<String, String> map);
	
	//保存财报申报主表
	public int insertTaxZlbscjb(Map<String, String> map);
	
	//保存企业资产负债表(企业会计制度)
	public int insertTaxQyzcfzb(Map<String, String> map);
	
	//保存企业利润表(企业会计制度)
	public int insertTaxQylrb(Map<String, String> map);
	
	//保存企业资产负债表(一般企业会计)
	public int insertTaxYbqyzcfzb(Map<String, String> map);
	
	//保存企业利润表(一般企业会计制度)
	public int insertTaxYbqylrb(Map<String, String> map);
	
	//保存利润表(小企业会计制度)_年报
	public int insertTaxXqylrbnb(Map<String, String> map);
	
	//保存编码有效期
	public int insertTaxLpbmcx(Map<String, String> map);
	
	//保存纳税信用级别
	public int insertTaxNsxyjb(Map<String, String> map);
	//调用接口成功后修改纳税人基础信息表里的接口调用是否成功
	public int updataNsryhxx(String id);
}
