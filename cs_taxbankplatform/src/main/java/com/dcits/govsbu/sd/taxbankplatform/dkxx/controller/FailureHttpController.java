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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper.DhDhrmxxMapper;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.DhFailure;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.service.gxgsService;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.ParametersService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;




/**
 * 接口
 * @author yaofang
 * @time 2017年10月13日14:21:38
 */
@Controller
@Scope("prototype")
public class FailureHttpController {
	/**
	 * 日志
	 */
	public Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ParametersService parametersService;
    @Autowired
    private DhDhrmxxMapper dhDhrmxxMapper;
    @Autowired
    private gxgsService gxgsService;
		

		//小V推送审批不通过数据  税银平台入库
			@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
			@RequestMapping(value = "/saveCreditfailure", method = RequestMethod.POST)
			protected void saveCreditfailure(HttpServletRequest request,HttpServletResponse response){
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
					if (!content.equals("")) {
						Map<String, Object> requestJsonmap = new HashMap<>();
						logger.info("=========saveCreditfailure请求参数========="+content);  
						  requestJsonmap = JSON.parseObject(content);
						  String statusCode=requestJsonmap.get("statusCode").toString();
						  if(("000000").equals(statusCode)){
							  if(requestJsonmap.get("result").toString()!=null&&!("").equals(requestJsonmap.get("result").toString())){
								  String resultMsg=requestJsonmap.get("result").toString();
								  JSONArray jsonArray = JSONArray.fromObject(resultMsg); 
								  List<DhFailure> list = (List) JSONArray.toCollection(jsonArray, 
										  DhFailure.class);
								  if(list.size()>0&&list!=null){
									  for (DhFailure dhFailure : list) {
										  String f=IDGenerate.getZJID("failure");
										  java.util.Random random = new java.util.Random();
										     int a = random.nextInt(9000) + 1000;
										    dhFailure.setFailureId(f+a);
										    dhFailure.setChannelid("GLBANK");
										   
									}
						              int result=  dhDhrmxxMapper.insertFailure(list);
			                          System.out.println(result); 
			                          if(result>0){
			                        	   respMap.put("resultcode","00");
			      						   respMap.put("resultmsg","入库成功");
			      						   respString= JSON.toJSONString(respMap);
			                          }else {
			                        	   respMap.put("resultcode","01");
				      					   respMap.put("resultmsg","入库失败");
				      					   respString= JSON.toJSONString(respMap);
									  }
									
								  }
							  }else{
								  logger.info("=======================推送result为空==========================");
							  }
							  /*********************2018-04-19 新增贷前贷后对照关系***********************************************/
							  if(requestJsonmap.get("dhglsj").toString()!=null&&!("").equals(requestJsonmap.get("dhglsj").toString())){
								  List<Map<String, Object>> List =(List<Map<String, Object>>) requestJsonmap.get("dhglsj");
					              //先清库再入库
								  int sum = 0;
								  int nub = gxgsService.delete("");
								  Map<String, Object> insertMap = new HashMap<String, Object>();
								  for (Map<String, Object> map : List) {
									  if (map!=null) {
										  insertMap.put("pql_flowno", map.get("PQL_FLOWNO"));
										  insertMap.put("pql_bsncode", map.get("PQL_BSNCODE"));
										  insertMap.put("pql_stt", map.get("PQL_STT"));
										  insertMap.put("pql_cstno", map.get("PQL_CSTNO"));
										  insertMap.put("pql_accno", map.get("PQL_ACCNO"));
										  insertMap.put("pql_cstname", map.get("PQL_CSTNAME"));
										  insertMap.put("pql_certtype", map.get("PQL_CERTTYPE"));
										  insertMap.put("pql_certno", map.get("PQL_CERTNO"));
										  insertMap.put("pql_loanterm", map.get("PQL_LOANTERM"));
										  insertMap.put("pql_loanamount", map.get("PQL_LOANAMOUNT"));
										  insertMap.put("pql_time", map.get("PQL_TIME"));
										  insertMap.put("pql_rate", map.get("PQL_RATE"));
										  insertMap.put("pql_channel", map.get("PQL_CHANNEL"));
										  insertMap.put("industrycode", map.get("INDUSTRYCODE"));
										  insertMap.put("regioncode", map.get("REGIONCODE"));
										  insertMap.put("dkhthm", map.get("DKHTHM"));
										  insertMap.put("businessid", map.get("BUSINESSID"));
									}
									  sum = gxgsService.insertTaxLoan(insertMap);
									}
								  
							  }else{
								  logger.info("=======================推送result为空==========================");
							  }
						  }else{
							  logger.info("=======================statusCode为"+statusCode+"==========================");
						  }
					}else{
						respMap.put("resultcode","09");
						respMap.put("resultmsg","请求参数为空");
						respString= JSON.toJSONString(respMap);
					}
				} catch (Exception e) {
					logger.info("===================== 失败 =========================");
					if (content.equals("")) {
						respMap.put("resultcode","09");
						respMap.put("resultmsg","请求参数为空");
						respString= JSON.toJSONString(respMap);
					}else {
						respMap.put("content", content);
						respMap.put("resultcode", "09");
						respMap.put("resultmsg", "系统错误");
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
