package com.dcits.govsbu.sd.taxbankplatform.datastorage.service;

import java.util.List;
import java.util.Map;

/**
 * 纳税人基础信息接口
 * @author Administrator
 */
public interface TaxDataSaveService {
	//新增企业基础信息
	public int insertNsrjcxx(List<Map<String, String>> dataList);
	
	//新增企业基础信息扩展
	public int insertNsrxxKz(List<Map<String, String>> dataList);
	
	//新增企业批准机构信心
	public int insertPzjgxx(List<Map<String, String>> dataList);
	
	//新增企业变更信息
	public int insertBgdjmx(List<Map<String, String>> dataList);
	
	//新增企业投资方信息
	public int insertTzfxx(List<Map<String, String>> dataList);
	
	//新增企业分支机构信息
	public int insertFzjgxx(List<Map<String, String>> dataList);
	
	//新增企业总分机构信息
	public int insertZzjgxx(List<Map<String, String>> dataList);
	
	//新增企业违法违章信息
	public int insertSswfxwdj(List<Map<String, String>> dataList);
	
	//新增企业稽查案件信息
	public int insertAjxx(List<Map<String, String>> dataList);
	
	//新增企业申报信息
	public int insertSbxx(List<Map<String, String>> dataList);
	
	//新增企业征收信息
	public int insertYjsf(List<Map<String, String>> dataList);
	
	//新增企业资产负债表(小企业会计制度)
	public int insertXqyzcfzb(List<Map<String, String>> dataList);
	
	//新增企业利润表(小企业会计制度)
	public int insertXqylrb(List<Map<String, String>> dataList);
	
	//新增企业发票开具
	public int insertFpkj(List<Map<String, String>> dataList);
	
	//新增企业认证发票
	public int insertRzfp(List<Map<String, String>> dataList);
	
	//新增一般纳税人申报明细主表
	public int insertYnbsr(List<Map<String, String>> dataList);
	
	//新增小规模纳税人申报明细主表
	public int insertXgmnsr(List<Map<String, String>> dataList);
	
	//新增纳税人资格信息结果表
	public int insertNsrzgxxjgb(List<Map<String, String>> dataList);
	
	//新增稽查案源信息
	public int insertAyxx(List<Map<String, String>> dataList);
	
	//新增欠税信息
	public int insertQsclxx(List<Map<String, String>> dataList);
	
	//新增财报申报主表
	public int insertZlbscjb(List<Map<String, String>> dataList);
	
	//新增企业资产负债表(企业会计制度)
	public int insertQyzcfzb(List<Map<String, String>> dataList);
	
	//新增企业利润表(企业会计制度)
	public int insertQylrb(List<Map<String, String>> dataList);
	
	//新增企业资产负债表(一般企业会计)
	public int insertYbqyzcfzb(List<Map<String, String>> dataList);
	
	//新增企业利润表(一般企业会计制度)
	public int insertYbqylrb(List<Map<String, String>> dataList);
	
	//新增利润表(小企业会计制度)_年报
	public int insertXqylrbnb(List<Map<String, String>> dataList);
	
	//新增编码有效期
	public int insertLpbmcx(List<Map<String, String>> dataList);
	
	//新增纳税信用级别
	public int insertNsxyjb(List<Map<String, String>> dataList);
	
	//调用接口成功后修改纳税人基础信息表里的接口调用是否成功
	public int updataNsryhxx(String id);
	
}
