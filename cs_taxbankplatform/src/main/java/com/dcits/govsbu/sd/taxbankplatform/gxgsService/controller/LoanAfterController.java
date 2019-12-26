package com.dcits.govsbu.sd.taxbankplatform.gxgsService.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper.SyptbankApplyMapper;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.model.LoanAfterInfo;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.service.gxgsService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 贷后数据接收接口
 * @author Sigua.Huang
 * @date 2018年6月27日
 */
@Controller
@Scope("prototype")
public class LoanAfterController {
	/**
	 * log日志
	 */
	public Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private gxgsService gxgsService;
	@Autowired
    private SyptbankApplyMapper bankApplyMapper;
//	@Autowired 
//	private AuthorizationrecordService authorizationrecordService;
	/**
	 * 贷后数据接收接口
	 * @author Sigua.Huang
	 * @date 2018年6月27日
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	@RequestMapping(value = "/saveLoanAfterInfo", method = RequestMethod.POST)
	protected void saveLoanAfterInfo(HttpServletRequest request,HttpServletResponse response){
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		Map<String, String> respMap = new HashMap<String, String>();
		//请求的内容
		String content = "";
		String respString = "";
		try {
			reader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {   
				sb.append(line);   
			}  
			content = sb.toString();
			//如果请求json不为空
			if (StringUtils.isNotBlank(content)) {
//				Map<String, Object> requestJsonmap = new HashMap<>();
				logger.info("=========tbp内网saveLoanAfterInfo请求参数========="+content);  
//				  requestJsonmap = JSON.parseObject(content);
//				  String statusCode=requestJsonmap.get("statusCode").toString();
//				  if(("000000").equals(statusCode)){
//					  String resultMsg=requestJsonmap.get("result").toString();
//					  if(StringUtils.isNotBlank(statusMsg)){
						  JSONArray jsonArray = JSONArray.fromObject(content); 
						 // List<LoanAfterInfo> list = (List) JSONArray.toCollection(jsonArray, LoanAfterInfo.class);
						  List<LoanAfterInfo> list = new ArrayList<LoanAfterInfo>();
						  if(jsonArray!=null && jsonArray.size()>0){
							  for(int i = 0 ; i < jsonArray.size() ; i++){
								  JSONObject jsonObj = jsonArray.getJSONObject(i);
								  LoanAfterInfo loanAfterInfo  =  getLoanAf(jsonObj);
								  //生成唯一主键ID作为主键
								  String f=IDGenerate.getZJID("ZXDHSX");
								  java.util.Random random = new java.util.Random();
								  int a = random.nextInt(9000) + 1000;
								  loanAfterInfo.setDhsxId(f+a);
								  //添加对象
								  list.add(loanAfterInfo);
								  //同步授权结束时间
								  String isExtension = loanAfterInfo.getIsExtension();
								  if("Y".equals(isExtension)){
									  Map<String,Object> sqMap = new HashMap<String,Object>();
									  sqMap.put("authUpdateTime", loanAfterInfo.getExtensionTime());
									  sqMap.put("grantCode", bankApplyMapper.queryGrantCodeByBusinessid(loanAfterInfo.getBusinessid()));
									  int sqResult=bankApplyMapper.updateGrantEndTime(sqMap);
								  }
							  }
							  System.out.println("******list******"+list.toString());
				              int result=  gxgsService.insertLoanAfterInfo(list);
	                          if(result>0){
	                        	   respMap.put("statusCode","000000");
	      						   respMap.put("statusMsg","入库成功");
	      						   respString= JSON.toJSONString(respMap);
	                          }else {
	                        	   respMap.put("statusCode","999999");
		      					   respMap.put("statusMsg","入库失败");
		      					   respString= JSON.toJSONString(respMap);
							  }
							
						  }else{
							  logger.info("========================jsonArray转List出错=========================");
						  }
//					  }else{
//						  logger.info("=======================推送result为空==========================");
//					  }
//				  }else{
//					  logger.info("=======================statusCode为"+statusCode+"==========================");
//				  }
			}else{
				respMap.put("statusCode","999999");
				respMap.put("statusMsg","请求参数为空");
				respString= JSON.toJSONString(respMap);
			}
		} catch (Exception e) {
			logger.info("===================== 失败 =========================");
			respMap.put("content", content);
			respMap.put("statusCode", "999999");
			respMap.put("statusMsg", "系统错误");
			respString= JSON.toJSONString(respMap);
			e.printStackTrace();
		}finally{
			try
			{
				if (null != reader){
					reader.close();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		response.setContentType("text/xml");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			System.out.println(respString);
			out.print(respString);
		} catch (IOException e) {
			logger.info("======================接收授信不通过数据接口失败=====================");
			e.printStackTrace();
		}finally{
			try
			{
				if (null != out)
				{
					out.close();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	 private LoanAfterInfo  getLoanAf(JSONObject jsonObj){
		  LoanAfterInfo laf = new LoanAfterInfo();
		  if(jsonObj.containsKey("extensionTime")){
			  laf.setExtensionTime(objToStr(jsonObj.getString("extensionTime")));
		  }else{
			  laf.setExtensionTime("");
		  }
		  laf.setIndName(objToStr(jsonObj.getString("indName")));
		  laf.setEntName(objToStr(jsonObj.getString("entName")));
		  laf.setLoanBalance(objToStr(jsonObj.getString("loanBalance")));
		  laf.setIsExtension(objToStr(jsonObj.getString("isExtension")));
		  laf.setIndCertType(objToStr(jsonObj.getString("indCertType")));
		  laf.setEntCreditID(objToStr(jsonObj.getString("entCreditID")));
		  laf.setLoanTime(objToStr(jsonObj.getString("loanTime")));
		  laf.setLoanAccount(objToStr(jsonObj.getString("loanAccount")));
		  laf.setTbpCode(objToStr(jsonObj.getString("tbpCode")));
		  laf.setEntTaxID(objToStr(jsonObj.getString("entTaxID")));
		  laf.setLoanApplyAmount(objToStr(jsonObj.getString("loanApplyAmount")));
		  laf.setGrantFindTimeStart(objToStr(jsonObj.getString("grantFindTimeStart")));
		  laf.setIndCertID(objToStr(jsonObj.getString("indCertID")));
		  laf.setDrawingBalance(objToStr(jsonObj.getString("drawingBalance")));
		  laf.setBusinessid(objToStr(jsonObj.getString("businessid")));
		  laf.setGrantCode(objToStr(jsonObj.getString("grantCode")));
		  laf.setLoanAmount(objToStr(jsonObj.getString("loanAmount")));
		  laf.setTbpName(objToStr(jsonObj.getString("tbpName")));
		  laf.setLoanTerm(objToStr(jsonObj.getString("loanTerm")));
		  laf.setApplyTimeEnd(objToStr(jsonObj.getString("applyTimeEnd")));
		  laf.setApplyTimeStart(objToStr(jsonObj.getString("applyTimeStrat")));
		  laf.setLoanGrantTime(objToStr(jsonObj.getString("loanGrantTime")));
		  laf.setBusinessType(objToStr(jsonObj.getString("businessType")));
		  laf.setGrantFindTimeEnd(objToStr(jsonObj.getString("grantFindTimeEnd")));
		  laf.setLoanOverdue(objToStr(jsonObj.getString("loanOverdue")));
		  laf.setBackups(objToStr(jsonObj.getString("backups")));
		  return laf;
	 }
	 
	 public static String objToStr(Object obj){
			if(obj == null) {
				return "";
			}
			String objstr = String.valueOf(obj);
			if("null".equals(objstr) || "NULL".equals(objstr)){
				return "";
			}
			return objstr.trim();
	}
}
