package com.dcits.govsbu.sd.taxbankplatform.gxgsService.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.gxgsService.model.LoanAfterInfo;



public interface gxgsService {
	//根据银行传入的ip查找在 平台配置的对应参数
	public Map<String,Object>queryBaseDataByCpid(String bankCpId);
	
	//新增贷款申请信息
	public int insertLoanApply(Map<String,Object> map);
	
	//修改贷款申请信息
	public int updateLoanApply(Map<String,Object> map);
	
	//新增产品申贷审批记录表
	public int insertLoanApproveRec(Map<String,Object> map);
	
	//新增产品申贷成功记录表
	public int insertLoanApplyFinal(Map<String,Object> map);
	
	// 新增信贷终止表
	public int insertLoanApplyend(Map<String,Object> map);
	
	// 查询纳税人信息
	public Map<String,Object> queryNsryhxx(String nsrsbh);
	
	// 查询对照表信息
	public Map<String,Object> querySyptBankApply(String businessID);
	
	// 查询final laf_if
	public String queryLafId(String la_id);
	
	 //修改纳税人用户表
	 public int updateNsryh(Map<String, String> parameter);
	 
	 //修改纳税人用户信息表
	 public int updateNsryhxx(Map<String, String> parameter);
	 
	/* //更新授权结果 
	 public int updateSqBySqdId(Map<String, String> parameter);
	 
	 //更新 认证结果 
	 public int updateRzBySqdId(Map<String, String> parameter);*/
	 
	 //查询用户的id
	 public String searchUserId(String sqxh);
	 
	 //新增tb_authorization_record税务授权记录表
	 public int insertToAr(Map<String,Object> map);
	 
	 //新增tb_check_record用户认证记录表
	 public int insertRecord(Map<String,Object> map);

	public int insertData(Map<String, Object> requestcontentJsonmap);
	
	
	public int updateAuRecord(Map<String,Object> map);
	
	 //根据纳税人识别号查询用户名
	 public String searchUserByNsrsbh(Map<String,Object> map);
	 //查找新增授信成功的增量数据tb_dh_sxjgxx
	 public List<Map<String, Object>> getFinalTableData(String date);
	 //查找新增贷款结束信息的增量数据 tb_dh_dkjsxx
	 public List<Map<String, Object>> getEndTableData(String date);
	 //根据身份证号码获取到需要操作申请记录
	 public List<Map<String, Object>> getBankApplyData(String sfzhm);
	 //根据产品id获取区域id和 组织id
	 public Map<String, Object> getBaseDataByFpid(String fpId);
	 //根据合同号码查找对应的基础信息
	 public Map<String, Object> getBankApplyDataByHth(String hth);
	 //根据合同号码过滤出需要构造为授信成功的申请单id
	 public Map<String, Object> getBankApplyByHth(String hth);
	 //插入桂林银行贷前贷后数据对照表
	 public int insertTaxLoan(Map<String,Object> map);
	 //清空贷前贷后数据对照表
	 public int delete(String businessid);
	 //根据申请单id查找到对应的申请时的税号 
	 public Map<String, Object> getNsrsbhBySqxh(String sqxh);
	 //根据申请单id查找对应的laid
	 public Map<String, Object> getLaidByBusinessId(String businessId);
	 //根据银行申请单id过滤出需要构造为授信成功的申请单id
	 public Map<String, Object> getBankApplyBySqdid(String sqdid);

	/**
	 * 贷后数据接收
	 * @author Sigua.Huang
	 * @date 2018年6月27日
	 */
	public int insertLoanAfterInfo(List<LoanAfterInfo> list);
}
