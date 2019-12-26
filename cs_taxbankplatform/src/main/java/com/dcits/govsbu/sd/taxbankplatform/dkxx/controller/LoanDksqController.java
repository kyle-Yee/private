package com.dcits.govsbu.sd.taxbankplatform.dkxx.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.service.AuthorizationrecordService;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper.SyptbankApplyMapper;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.LoanApplyInfo;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

/**
 * 贷款申请信息接收接口
 * @author Sigua.Huang
 * @date 2018年6月27日
 */
@Controller
@Scope("prototype")
public class LoanDksqController {
	/**
	 * 日志
	 */
	public Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private SyptbankApplyMapper bankApplyMapper;
    /**
     * 贷款申请data接口
     * @author Sigua.Huang
     * @date 2018年6月27日
     */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	@RequestMapping(value = "/saveLoanApplyInfo", method = RequestMethod.POST)
	protected void saveLoanApplyInfo(HttpServletRequest request,HttpServletResponse response){
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
				logger.info("=========saveLoanApplyInfo请求参数========="+content);  
//				  requestJsonmap = JSON.parseObject(content);
//				  String statusCode=requestJsonmap.get("statusCode").toString();
//				  if(("000000").equals(statusCode)){
//					  String resultMsg=requestJsonmap.get("result").toString();
//					  if(StringUtils.isNotBlank(resultMsg)){
						  JSONObject fromObject = JSONObject.fromObject(content);
						  LoanApplyInfo loanApplyInfo = (LoanApplyInfo)JSONObject.toBean(fromObject, LoanApplyInfo.class);
						  //生成唯一主键ID作为主键
						  String f=IDGenerate.getZJID("ZXDKSQ");
						  java.util.Random random = new java.util.Random();
						  int a = random.nextInt(9000) + 1000;
						  loanApplyInfo.setDksqId(f+a);
						  //同步授权结束时间
						  Map<String,Object> sqMap = new HashMap<String,Object>();
						  sqMap.put("authUpdateTime", loanApplyInfo.getGrantFindTimeEnd());
						  sqMap.put("grantCode", loanApplyInfo.getGrantCode());
						  int i = bankApplyMapper.updateGrantEndTime(sqMap);
						  //插入贷款信息
			              int result=  bankApplyMapper.insertLoanApplyInfo(loanApplyInfo);
                          if(result>0){
                        	   respMap.put("statusCode","000000");
      						   respMap.put("statusMsg","入库成功");
      						   respString= JSON.toJSONString(respMap);
						  }else{
							  respMap.put("statusCode","999999");
     						  respMap.put("statusMsg","入库失败");
     						  respString= JSON.toJSONString(respMap);						  
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
			if (content.equals("")) {
				respMap.put("statusCode","999999");
				respMap.put("statusMsg","请求参数为空");
				respString= JSON.toJSONString(respMap);
			}else {
				respMap.put("content", content);
				respMap.put("statusCode", "999999");
				respMap.put("statusMsg", "系统错误");
				respString= JSON.toJSONString(respMap);
			}
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
}
