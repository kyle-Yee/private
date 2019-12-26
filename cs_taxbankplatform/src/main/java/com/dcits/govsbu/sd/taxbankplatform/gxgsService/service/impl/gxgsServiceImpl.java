package com.dcits.govsbu.sd.taxbankplatform.gxgsService.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.gxgsService.mapper.gxgsServiceMapper;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.model.LoanAfterInfo;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.service.gxgsService;
import com.dcits.govsbu.sd.taxbankplatform.util.DateFormatter;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;


/**
 * 
 * @description: 贷后数据入库服务类
 * @author: 方认
 * @version V1.0 
 * @date: 2018年2月7日 下午5:38:15
 * @copyright©2018东方微银网络信息（北京）有限公司 
 * @fileName:com.dcits.govsbu.sd.taxbankplatform.gxgsService.service.impl.gxgsServiceImpl.java
 */
@Service
public class gxgsServiceImpl implements gxgsService{

	@Autowired
	private gxgsServiceMapper gxgsServiceMapper;
	@Override
	public int insertLoanApply(Map<String, Object> map) {
		
		return gxgsServiceMapper.insertLoanApply(map);
	}

	@Override
	public Map<String, Object> queryBaseDataByCpid(String bankCpId) {
		return gxgsServiceMapper.queryBaseDataByCpid(bankCpId);
	}
	
	@Override
	public int updateLoanApply(Map<String, Object> map) {
		return gxgsServiceMapper.updateLoanApply(map);
	}

	@Override
	public int insertLoanApproveRec(Map<String, Object> map) {
		return gxgsServiceMapper.insertLoanApproveRec(map);
	}

	@Override
	public int insertLoanApplyFinal(Map<String, Object> map) {
		return gxgsServiceMapper.insertLoanApplyFinal(map);
	}

	@Override
	public int insertLoanApplyend(Map<String, Object> map) {
		return gxgsServiceMapper.insertLoanApplyend(map);
	}

	@Override
	public Map<String, Object> queryNsryhxx(String nsrsbh) {
		return gxgsServiceMapper.queryNsryhxx(nsrsbh);
	}

	@Override
	public Map<String, Object> querySyptBankApply(String businessID) {
		return gxgsServiceMapper.querySyptBankApply(businessID);
	}

	@Override
	public String queryLafId(String la_id) {
		return gxgsServiceMapper.queryLafId(la_id);
	}

	@Override
	public int updateNsryh(Map<String, String> parameter) {
		return gxgsServiceMapper.updateNsryh(parameter);
	}

	@Override
	public int updateNsryhxx(Map<String, String> parameter) {
		return gxgsServiceMapper.updateNsryhxx(parameter);
	}

	@Override
	public String searchUserId(String sqxh) {
		return gxgsServiceMapper.searchUserId(sqxh);
	}

	@Override
	public int insertToAr(Map<String, Object> map) {
		return gxgsServiceMapper.insertToAr(map);
	}

	@Override
	public int insertRecord(Map<String, Object> map) {
		return gxgsServiceMapper.insertRecord(map);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int insertData(Map<String, Object> requestcontentJsonmap) {
		//1.插入tb_authorization_record授权记录表
		Map<String,Object> authorizationMap=new HashMap<String,Object>();
		Map<String ,Object> baseDataMap = new HashMap<String ,Object>();
		String orgId =null;
		String fpId = null;
		String  agId = null;
		String bankCpId =null;
		if (requestcontentJsonmap!=null) {
			bankCpId = String.valueOf(requestcontentJsonmap.get("cpName"));
			baseDataMap=gxgsServiceMapper.queryBaseDataByCpid(bankCpId);
			if (baseDataMap!=null) {
				orgId = String.valueOf(baseDataMap.get("orgId"));
				fpId = String.valueOf(baseDataMap.get("fpId"));
				agId = String.valueOf(baseDataMap.get("agId"));
			}
		}
		String syptLaId = IDGenerate.getZJID("DKZX");
		String atId=IDGenerate.getZJID("XH");
		authorizationMap.put("atId", atId);//主键
		authorizationMap.put("atSqdid", String.valueOf(requestcontentJsonmap.get("bh")));//申请编号
		//authorizationMap.put("atDkqx", String.valueOf(requestcontentJsonmap.get("at_dkqx")));//贷款期限
		/******************************之前取值字段有问题 在此修改***********************开始***************************************/
		//authorizationMap.put("atSqsj", String.valueOf(requestcontentJsonmap.get("cr_sqsj")));//申请时间
		//authorizationMap.put("atSqsj", String.valueOf(requestcontentJsonmap.get("at_sqsj")));//申请时间
		/******************************之前取值字段有问题 在此修改***********************结束***************************************/
		authorizationMap.put("atQymc", String.valueOf(requestcontentJsonmap.get("cr_qymc")));//企业名称
		authorizationMap.put("atNsrsbh", String.valueOf(requestcontentJsonmap.get("cr_nsrsbh")));//纳税人识别号
		//authorizationMap.put("atSqkssj", String.valueOf(requestcontentJsonmap.get("at_sqkssj")));//授权开始时间
		//authorizationMap.put("atSqjssj", String.valueOf(requestcontentJsonmap.get("at_sqjssj")));//授权结束时间
		Date today = new Date();
		String at_sqkssj = DateFormatter.dateToStr(today, "yyyy-MM-dd");
		String at_sqjssj = DateFormatter.dateToStr(DateFormatter.timeAddDay(today,7), "yyyy-MM-dd");
		authorizationMap.put("atSqsj", at_sqkssj);//申请时间
		authorizationMap.put("atDkqx", String.valueOf(requestcontentJsonmap.get("at_sqsj")));//期限
		authorizationMap.put("atSqkssj", at_sqkssj);//授权开始时间
		authorizationMap.put("atSqjssj", at_sqjssj);//授权结束时间
		authorizationMap.put("atSqsyxq", String.valueOf(requestcontentJsonmap.get("at_sqsyxq")));//授权书有效期
		authorizationMap.put("laId", syptLaId);//申请单id
		authorizationMap.put("orgId", String.valueOf(requestcontentJsonmap.get("bankName")));//组织id
		authorizationMap.put("fpId", String.valueOf(requestcontentJsonmap.get("cpName")));//产品id
		authorizationMap.put("agId", agId);//协议id
		authorizationMap.put("sqzt", String.valueOf(requestcontentJsonmap.get("Sqzt")));//授权结果
		authorizationMap.put("sqjg", String.valueOf(requestcontentJsonmap.get("Sqjg")));//授权结果描述
		authorizationMap.put("atFrxm", String.valueOf(requestcontentJsonmap.get("at_frxm")));//法人姓名
		authorizationMap.put("atFrsfz", String.valueOf(requestcontentJsonmap.get("at_frsfz")));//法人身份证号
		authorizationMap.put("atSjmc", String.valueOf(requestcontentJsonmap.get("swjgdm")));//税务机关名称
		gxgsServiceMapper.insertToAr(authorizationMap);
		//2.插入tb_check_record认证记录表
		Map<String,Object> checkRecordMap=new HashMap<String,Object>();
		String crId= IDGenerate.getZJID("RZ");
		checkRecordMap.put("crId", crId);
		checkRecordMap.put("crQymc", String.valueOf(requestcontentJsonmap.get("cr_qymc")));//企业名称
		checkRecordMap.put("crNsrsbh", String.valueOf(requestcontentJsonmap.get("cr_nsrsbh")));//纳税人识别号
		checkRecordMap.put("crFrsjh", String.valueOf(requestcontentJsonmap.get("cr_frsjh")));//法人手机号码
		checkRecordMap.put("crZjlx", String.valueOf(requestcontentJsonmap.get("cr_zjlx")));//证件类型
		checkRecordMap.put("crZjhm", String.valueOf(requestcontentJsonmap.get("cr_zjhm")));//证件号码
		checkRecordMap.put("crSqsj", String.valueOf(requestcontentJsonmap.get("cr_sqsj")));//申请时间
		checkRecordMap.put("crRzsj", String.valueOf(requestcontentJsonmap.get("cr_rzsj")));//认证时间
		checkRecordMap.put("crShjg", String.valueOf(requestcontentJsonmap.get("cr_shjg")));//审核结果
		checkRecordMap.put("crSqdid", String.valueOf(requestcontentJsonmap.get("bh")));//申请编号
		checkRecordMap.put("creatorid", String.valueOf(requestcontentJsonmap.get("creatorid")));//创建人id
		gxgsServiceMapper.insertRecord(checkRecordMap);
		//3.插入sypt_bank_apply
		Map<String, Object> sbaMap = new HashMap<String, Object>();
		String id= IDGenerate.getZJID("SBAZX");
		//String syptLaId = IDGenerate.getZJID("DKZX");
		sbaMap.put("id", id);
		sbaMap.put("syptLaId", syptLaId);
		sbaMap.put("bankLaId", String.valueOf(requestcontentJsonmap.get("bh")));
		sbaMap.put("syptFpId", fpId);
		sbaMap.put("bankFpId", bankCpId);
		sbaMap.put("channelId", orgId);
		sbaMap.put("userId", String.valueOf(requestcontentJsonmap.get("creatorid")));
		sbaMap.put("nsrsbh", String.valueOf(requestcontentJsonmap.get("cr_nsrsbh")));

		gxgsServiceMapper.insertSyptBankApply(sbaMap);
		//4.插入申请表tb_loan_apply
		/*Map<String, Object> laMap = new HashMap<String, Object>();
		String laId= IDGenerate.getZJID("DKZX");
		laMap.put("la_id", laId);
		
		gxgsServiceMapper.insertLoanApply(laMap);*/
		return 1;
	}

	@Override
	public int updateAuRecord(Map<String, Object> map) {
		return gxgsServiceMapper.updateAuRecord(map);
	}

	@Override
	public String searchUserByNsrsbh(Map<String,Object> map) {
		return gxgsServiceMapper.searchUserByNsrsbh(map);
	}

	@Override
	public List<Map<String, Object>> getFinalTableData(String date) {
		return gxgsServiceMapper.getFinalTableData(date);
	}

	@Override
	public List<Map<String, Object>> getEndTableData(String date) {
		return gxgsServiceMapper.getEndTableData(date);
	}

	@Override
	public List<Map<String, Object>> getBankApplyData(String sfzhm) {
		return gxgsServiceMapper.getBankApplyData(sfzhm);
	}

	@Override
	public Map<String, Object> getBaseDataByFpid(String fpId) {
		return gxgsServiceMapper.getBaseDataByFpid(fpId);
	}

	@Override
	public Map<String, Object> getBankApplyDataByHth(String hth) {
		return gxgsServiceMapper.getBankApplyDataByHth(hth);
	}

	@Override
	public Map<String, Object> getBankApplyByHth(String hth) {
		return gxgsServiceMapper.getBankApplyByHth(hth);
	}

	@Override
	public int insertTaxLoan(Map<String,Object> map) {
		return gxgsServiceMapper.insertTaxLoan(map);
	}

	@Override
	public int delete(String businessid) {
		return gxgsServiceMapper.delete(businessid);
	}

	@Override
	public Map<String, Object> getNsrsbhBySqxh(String sqxh) {
		return gxgsServiceMapper.getNsrsbhBySqxh(sqxh);
	}

	@Override
	public Map<String, Object> getLaidByBusinessId(String businessId) {
		return gxgsServiceMapper.getLaidByBusinessId(businessId);
	}

	@Override
	public Map<String, Object> getBankApplyBySqdid(String sqdid) {
		return gxgsServiceMapper.getBankApplyBySqdid(sqdid);
	}

	@Override
	public int insertLoanAfterInfo(List<LoanAfterInfo> list) {
		return gxgsServiceMapper.insertLoanAfterInfo(list);
	}

}
