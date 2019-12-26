package com.dcits.govsbu.sd.taxbankplatform.gxgsService.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;
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
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.RegisterService;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.common.HttpUtils;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.common.util.JsonUtil;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.model.RegisterModel;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.service.gxgsService;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.ParametersService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;


/**
 * 
 * @description: 贷后数据入库
 * @author: 方认
 * @version V1.0
 * @date: 2018年2月7日 下午3:11:40
 * @copyright©2018东方微银网络信息（北京）有限公司
 * @fileName:com.dcits.govsbu.sd.taxbankplatform.gxgsService.controller.gxgsServiceController.java
 */
@Controller
@Scope("prototype")
public class gxgsServiceController {
	/**
	 * log日志
	 */
	public Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private gxgsService gxgsService;

	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private ParametersService parametersService;

	/**
	 * 
	 * @description: 供第三方调取 贷后数据入库
	 * @Autor: 方认
	 * @param request
	 * @param response
	 * @time:2018年2月7日 下午3:11:59
	 */
	@RequestMapping(value = "/loanAfterData", method = RequestMethod.POST)
	public void loanAfterData(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("/loanAfterData*******************************begin^*^");
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		Map<String, String> respMap = new HashMap<String, String>();
		// 请求的内容
		String content = "";
		String respString = "";
		try {
			reader = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			content = sb.toString();
			// 如果请求json不为空
			if (StringUtils.isNotEmpty(content)) {
				Map<String, Object> requestcontentJsonmap = JSON
						.parseObject(content);
				// 贷款结束信息
				String endLoan = requestcontentJsonmap.get("endLoan")
						.toString();
				// 授信结果信息
				String creditResults = requestcontentJsonmap.get(
						"creditResults").toString();
				// 贷款申请信息
				String loanApply = requestcontentJsonmap.get("loanApply")
						.toString();
				String laId = null;
				String fpid = null;
				// 贷款申请信息不为空
				if (StringUtils.isNotEmpty(loanApply)) {
					Map<String, Object> loanApplyMap = JSON.parseObject(loanApply);
					String businessID = loanApplyMap.get("businessID").toString();
					Map<String, Object> syptBankMap = gxgsService.querySyptBankApply(businessID);
					if (syptBankMap!=null) {
						if (syptBankMap.get("sypt_laId")!=null) {
							laId = String.valueOf(syptBankMap.get("sypt_laId"));
							fpid = String.valueOf(syptBankMap.get("sypt_fpid"));
						}else {
							syptBankMap = gxgsService.getLaidByBusinessId(businessID);
							if (syptBankMap!=null) {
								if (syptBankMap.get("la_id")!=null) {
									laId = String.valueOf(syptBankMap.get("la_id"));
								}
								
							}
						}
						
					}
					String nsrsbh = "";
					loanApplyMap.put("la_id", laId);
					loanApplyMap.put("lar_id", IDGenerate.getZJID("DKJLZX"));
					// 如果统一社会信用代码不为空
					if (loanApplyMap.get("entCreditID") != null) {
						nsrsbh = loanApplyMap.get("entCreditID").toString();
					}
					// 如果纳税人识别号不为空
					if (loanApplyMap.get("entTaxID") != null) {
						nsrsbh = loanApplyMap.get("entTaxID").toString();
					}
					loanApplyMap.put("nsrsbh", nsrsbh);
					// 根据纳税人识别号查询纳税人信息
					Map<String, Object> nsryhxxModel = gxgsService
							.queryNsryhxx(nsrsbh);
					if (nsryhxxModel == null) {
						respMap.put("resultcode", "09");
						respMap.put("resultmsg", "平台纳税人用户不存在");
						respString = JSON.toJSONString(respMap);
					} else {
						// 等待确认
						loanApplyMap.put("fp_id", fpid);
						loanApplyMap.put("region_id",
								nsryhxxModel.get("region_id"));
						loanApplyMap.put("creatorid",
								nsryhxxModel.get("creatorid"));
						loanApplyMap.put("updatorid",
								nsryhxxModel.get("creatorid"));
						loanApplyMap.put("las_id", "DKZT01");
						loanApplyMap.put("lafs_id", null);
						loanApplyMap.put("lac_id", null);
						loanApplyMap.put("lar_end", null);
						loanApplyMap.put("lar_rate", null);
						loanApplyMap.put("lar_isoverlay_area", 0);
						loanApplyMap.put("lar_bank_name", null);
						loanApplyMap.put("lar_bank_account", null);
						loanApplyMap.put("lar_contract", null);
						loanApplyMap.put("lar_opinion", null);
						gxgsService.insertLoanApply(loanApplyMap);
						//新增更新授权表表申请单id tb_authorization_record
						gxgsService.updateAuRecord(loanApplyMap);
						gxgsService.insertLoanApproveRec(loanApplyMap);
					}

				}
				// 授信结果信息不为空
				if (StringUtils.isNotEmpty(creditResults)) {
					Map<String, Object> creditResultsMap = JSON
							.parseObject(creditResults);
					String nsrsbh = "";
					creditResultsMap.put("laf_id", IDGenerate.getZJID("DKWCZX"));
					creditResultsMap.put("lar_id", IDGenerate.getZJID("DKJLZX"));
					// 如果统一社会信用代码不为空
					if (creditResultsMap.get("entCreditID") != null) {
						nsrsbh = creditResultsMap.get("entCreditID").toString();
					}
					// 如果纳税人识别号不为空
					if (creditResultsMap.get("entTaxID") != null) {
						nsrsbh = creditResultsMap.get("entTaxID").toString();
					}
					creditResultsMap.put("nsrsbh", nsrsbh);
					// 根据纳税人识别号查询纳税人信息
					Map<String, Object> nsryhxxModel = gxgsService
							.queryNsryhxx(nsrsbh);
					if (nsryhxxModel == null) {
						respMap.put("resultcode", "09");
						respMap.put("resultmsg", "平台纳税人用户不存在");
						respString = JSON.toJSONString(respMap);
					} else {
						String businessID = creditResultsMap.get("businessID")
								.toString();
						Map<String, Object> syptBankMap = gxgsService
								.querySyptBankApply(businessID);
						String admissionResult = creditResultsMap.get(
								"admissionResult").toString();
						creditResultsMap.put("fp_id",
								syptBankMap.get("sypt_fpid").toString());
						creditResultsMap.put("la_id",
								syptBankMap.get("sypt_laId").toString());
						//creditResultsMap.put("lafs_id", null);
						creditResultsMap.put("lar_opinion", null);
						creditResultsMap.put("region_id",
								nsryhxxModel.get("region_id"));
						creditResultsMap.put("creatorid",
								nsryhxxModel.get("creatorid"));
						creditResultsMap.put("updatorid",
								nsryhxxModel.get("creatorid"));

						creditResultsMap.put("lafs_id", null);
						creditResultsMap.put("lar_end", null);
						creditResultsMap.put("lar_rate", null);
						creditResultsMap.put("lar_isoverlay_area", 0);
						creditResultsMap.put("lar_bank_name", null);
						creditResultsMap.put("lar_bank_account", null);
						creditResultsMap.put("lar_contract", null);
						creditResultsMap.put("lar_opinion", null);
						if (admissionResult.equals("0")) {
							creditResultsMap.put("lac_id", "YDKZT03");
							creditResultsMap.put("las_id", "DKZT03");
							gxgsService.insertLoanApplyFinal(creditResultsMap);
						} else {
							creditResultsMap.put("lac_id", "YDKZT06");
							creditResultsMap.put("las_id", "DKZT06");
						}
						gxgsService.insertLoanApproveRec(creditResultsMap);
						gxgsService.updateLoanApply(creditResultsMap);
					}

				}
				// 贷款结束信息不为空
				if (StringUtils.isNotEmpty(endLoan)) {
					Map<String, Object> endLoanMap = JSON.parseObject(endLoan);
					endLoanMap.put("lae_id", IDGenerate.getZJID("DKZZZX"));
					endLoanMap.put("lar_id", IDGenerate.getZJID("DKJLZX"));
					String nsrsbh = "";
					// 如果统一社会信用代码不为空
					if (endLoanMap.get("entCreditID") != null) {
						nsrsbh = endLoanMap.get("entCreditID").toString();
					}
					// 如果纳税人识别号不为空
					if (endLoanMap.get("entTaxID") != null) {
						nsrsbh = endLoanMap.get("entTaxID").toString();
					}
					endLoanMap.put("nsrsbh", nsrsbh);
					// 根据纳税人识别号查询纳税人信息
					Map<String, Object> nsryhxxModel = gxgsService
							.queryNsryhxx(nsrsbh);
					if (nsryhxxModel == null) {
						respMap.put("resultcode", "09");
						respMap.put("resultmsg", "平台纳税人用户不存在");
						respString = JSON.toJSONString(respMap);
					} else {
						if (("00").equals(endLoanMap.get("endType"))) {
							endLoanMap.put("lafs_id", "DHZT02");// 贷款按期完成
						} else if (("01").equals(endLoanMap.get("endType"))) {
							endLoanMap.put("lafs_id", "DHZT01");// 贷款手动终止
						} else if (("11").equals(endLoanMap.get("endType"))) {
							endLoanMap.put("lafs_id", "DHZT03");// 其他非正常终止
						}
						String businessID = endLoanMap.get("businessID")
								.toString();
						Map<String, Object> syptBankMap = gxgsService
								.querySyptBankApply(businessID);
						String la_id = syptBankMap.get("sypt_laId").toString();
						String laf_id = gxgsService.queryLafId(la_id);
						endLoanMap.put("creatorid",
								nsryhxxModel.get("creatorid"));
						endLoanMap.put("updatorid",
								nsryhxxModel.get("creatorid"));

						endLoanMap.put("org_id", null);
						endLoanMap.put("la_id", la_id);
						endLoanMap.put("las_id", "DKZT07");
						endLoanMap.put("laf_id", laf_id);
						gxgsService.insertLoanApplyend(endLoanMap);
						gxgsService.updateLoanApply(endLoanMap);
					}
				}

			} else {
				respMap.put("resultcode", "09");
				respMap.put("resultmsg", "请求数据为空");
				respString = JSON.toJSONString(respMap);
			}
		} catch (Exception e) {
			logger.info("&贷后数据入库接口失败&");
			respMap.put("resultcode", "09");
			respMap.put("resultmsg", "系统错误");
			respString = JSON.toJSONString(respMap);
			e.printStackTrace();
		} finally {
			try {
				if (null != reader) {
					reader.close();
				}
			} catch (Exception e) {

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
			logger.info("======================返回贷后数据入库结果报错=====================");
			e.printStackTrace();
		} finally {
			try {
				if (null != out) {
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("/loanAfterData*******************************end^*^");
	}

	/**
	 * 
	 * @Author Zhongyj
	 * @date 2018-2-10 下午3:57:30
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getRzSqDataAction", method = RequestMethod.POST)
	public void getRzSqDataAction(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("/getRzSqDataAction*******************************begin^*^");
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		// Map<String, String> respMap = new HashMap<String, String>();
		// 请求的内容
		String content = "";
		String respString = "";
		PrintWriter out = null;
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			reader = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			content = sb.toString();
			//如果请求json不为空
			System.out.println("*************getRzSqDataAction*************外联网区接口 2.1的请求参数*******content************"+content);
			if (StringUtils.isNotEmpty(content)) {

			Map<String, Object> requestcontentJsonmap = JSON
						.parseObject(content);
			Map<String ,Object> baseDataMap = new HashMap<String ,Object>();
			String regionId =null;
			String bankCpId =null;
			if (requestcontentJsonmap!=null) {
				bankCpId = String.valueOf(requestcontentJsonmap.get("cpName"));
				baseDataMap=gxgsService.queryBaseDataByCpid(bankCpId);
				if (baseDataMap!=null) {
					regionId = String.valueOf(baseDataMap.get("regionId"));
				}
			}
				String nsrsbh = requestcontentJsonmap.get("nsrsbh").toString();// 纳税人识别号
				String crNsrsbh=requestcontentJsonmap.get("cr_nsrsbh").toString();//社会统一信用代码
				String bh=requestcontentJsonmap.get("bh").toString();//授权委托书编号
				String qymc=requestcontentJsonmap.get("cr_qymc").toString();//企业名称
				String zjlx=requestcontentJsonmap.get("cr_zjlx").toString();//证件类型
				String zjhm=requestcontentJsonmap.get("cr_zjhm").toString();//证件号码
				String frsjh=requestcontentJsonmap.get("cr_frsjh").toString();//法人手机号码
				String bankName=requestcontentJsonmap.get("bankName").toString();//银行名称代码

				Map<String,Object> map=new HashMap<String,Object>();
				map.put("nsrsbh", nsrsbh);
				map.put("crNsrsbh", crNsrsbh);
				//String userName = registerService.searchYByNsrsbh(map);// 通过纳税人识别号查询认证通过的用户名
				String userName = gxgsService.searchUserByNsrsbh(map);// 通过纳税人识别号查询认证通过的用户名
				if (!StringUtils.isEmpty(userName)) {
					logger.info("===========该用户已存在，直接登录==============");
					// 有，登录该通过认证的用户
					RegisterModel registerEntity = new RegisterModel();
					if (registerService.userLogin(userName)!=null&&registerService.userLogin(userName).size()>0) {
						registerEntity = registerService.userLogin(userName).get(0);
						requestcontentJsonmap.put("creatorid", registerEntity.getUserId());
						request.getSession().setAttribute("userSession", registerEntity);	
					}
				}else{
					logger.info("===========该用户不存在，先注册，后登录==============");
					
					RegisterModel register=new RegisterModel();
					register.setQdcode(bankName);
					register.setUserName("zx"+bh);
					register.setNsrmc(qymc);
					register.setFrsjh(frsjh);
					register.setQymc(qymc);
					register.setPapersName(zjlx);
					register.setPapersNumb(zjhm);
					register.setPassword("password");
					register.setRegionId(regionId);
					register.setCredentialsSalt("password");
					register.setEnabled("K");
					register.setNsrsbh(nsrsbh);
					register.setShtyxydm(crNsrsbh);
					//注册
					registerService.insertUser(register);
					//登陆该账户
					RegisterModel registerModel = new RegisterModel();
					if (registerService.userLogin(register.getUserName())!=null && registerService.userLogin(register.getUserName()).size()>0) {
						registerModel = registerService.userLogin(register.getUserName()).get(0);
						requestcontentJsonmap.put("creatorid", registerModel.getUserId());
						request.getSession().setAttribute("userSession", registerModel);
					}
				}
				//插入税务授权和用户认证记录
				gxgsService.insertData(requestcontentJsonmap);
				
			}
			response.setContentType("text/xml");
			response.setCharacterEncoding("utf-8");

			resultMap.put("resultcode", "00");
			resultMap.put("resultmsg", "成功");
			respString = Map2Json(resultMap);

			out = response.getWriter();
			System.out.println(">>>>>>>>>>>3>>>>>>>>>>>>>>"+respString);
			out.print(respString);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				resultMap.put("resultcode", "09");
				resultMap.put("resultmsg", "系统异常");
				respString = Map2Json(resultMap);
				out = response.getWriter();
				System.out.println(respString);
				out.print(respString);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

	/**
	 * 
	 * @Author Zhongyj
	 * @date 2018-2-10 下午3:57:36
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateEnterMsgAction", method = RequestMethod.POST)
	public void updateEnterMsg(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("/updateEnterMsg*******************************begin^*^");
		BufferedReader reader = null;
		Map<String, String> respMap = new HashMap<String, String>();
		StringBuilder sb = new StringBuilder();
		PrintWriter out = null;
		String xydj = "";
		String hydm = "";
		String swjgdm = "";
		String zgswjdm = "";
		/*String code = "N";*/
		String sqxh = "";
		String id = "";
		// 客户端请求的内容
		String content = "";
		// 返回给客服端的信息
		String respString = "";

		try {
			reader = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			content = sb.toString();
			System.out.println(">>>>>>>>>>>>>updateEnterMsgAction>>>>>content>>>>>>>>>"+content);
			// 如果请求json不为空 将请求内容转换为map
			if (StringUtils.isNotEmpty(content)) {
				Map<String, String> requestmap = Json2Map(content);
				// 更新纳税人基础信息操作
				if (requestmap != null) {

					/*code = requestmap.get("code");*/

					sqxh = requestmap.get("sqxh");

					xydj = requestmap.get("xydj");

					hydm = requestmap.get("hydm");

					swjgdm = requestmap.get("swjgdm");

					if (swjgdm.length() > 5) {
						zgswjdm = swjgdm.substring(1, 5);
					}
				}

				Map<String, String> parameter = new HashMap<String, String>();
				// 如果sqxh不为空的情况下，在平台查询出对应需要更新的纳税人id
				if (sqxh != null && sqxh != "") {

					id = gxgsService.searchUserId(sqxh);
				}
				// 如果返回的状态吗是Y则将需要更新的纳税人信息放到map中
				parameter.put("id", id);
				parameter.put("xydj", xydj);
				parameter.put("hydm", hydm);
				parameter.put("swjgdm", swjgdm);
				parameter.put("zgswjdm", zgswjdm);
				// 调用更新纳税人基础信息 ，更新纳税人基础信息
				int rusult = gxgsService.updateNsryhxx(parameter);
				if (rusult == 1) {
					respMap.put("resultcode","00");
					respMap.put("resultmsg","更新数据成功");
				}else {
					respMap.put("resultcode","09");
					respMap.put("resultmsg","更新数据失败");
				}
				respString= JSON.toJSONString(respMap);
				/*if ("Y".equals(code)) {
				}*/
				// 返回给客户端信息
				response.setContentType("text/xml");
				response.setCharacterEncoding("utf-8");
				try {
					out = response.getWriter();
					System.out.println(respString);
					out.print(respString);

				} catch (IOException e) {
					logger.info("======================更新纳税人基础信息出错=====================");
					respMap.put("resultcode","09");
					respMap.put("resultmsg","系统异常");
					respString= JSON.toJSONString(respMap);
					out = response.getWriter();
					out.print(respString);
					e.printStackTrace();
				} finally {
					try {
						if (null != out) {
							out.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
		}
	}
	
	//增加将将电子税局传送过来的基础信息从税局内外传到税局外联网区的服务
	/*
	 * 广西税局税银平台数据构造业务逻辑说明
	 * 1.广西带着电子调用税局内网的身份认证的接口，税局内网的身份认证接口将数据转发到税局外外联网
	 * 2.在税局的外联网区构造认证授权的基础信息
	 * 3.贷后数据构造
	 * 4.将构造后的数据同步到税局的内网
	 */
	/**
	 * 
	 * @Author Zhongyj
	 * @date 2018-2-10 下午3:57:30
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/sentRzSqDataAction", method = RequestMethod.POST)
	public void sentRzSqDataAction(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("/sentRzSqDataAction*******************************begin^*^");
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		// Map<String, String> respMap = new HashMap<String, String>();
		// 请求的内容
		String content = "";
		String respString = "";
		PrintWriter out = null;
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			reader = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			content = sb.toString();
			System.out.println("*************sentRzSqDataAction*************内外网区接口 2.1的请求参数*******content************"+content);
			String gjDataCode = parametersService.QueryValueByCode("getGzDataCode");//获取是否调用外网构造数据的标识 如果是为Y则需要构建否则这不需要构建
			//String url = "http://127.0.0.1:8080/tbp/sentRzSqDataAction";
			if ("Y".contains(gjDataCode)) {
				String url = parametersService.QueryValueByCode("getGzDataUrl");//获取调用外联网构造数据的url地址
				String encoding = "UTF-8";
				String rusult  = HttpUtils.sendPostMethod(url, content, encoding);
				
				if (rusult!=null) {
					resultMap = JsonUtil.JsonToMap(rusult);
					if (resultMap != null) {
						resultMap.get("resultcode");
						resultMap.get("resultmsg");
					}
				}
				System.out.println(rusult);
			}
			response.setContentType("text/xml");
			response.setCharacterEncoding("utf-8");

			resultMap.put("resultcode", "00");
			resultMap.put("resultmsg", "成功");
			respString = Map2Json(resultMap);

			out = response.getWriter();
			System.out.println(">>>>>>>>>>2>>>>>>>>>>>"+respString);
			out.print(respString);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				resultMap.put("resultcode", "09");
				resultMap.put("resultmsg", "系统异常");
				respString = Map2Json(resultMap);
				out = response.getWriter();
				System.out.println(respString);
				out.print(respString);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

	/**
	 * Json数据转成 Map数据
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> Json2Map(String json) {

		return JSON.parseObject(json, Map.class);
	}

	/**
	 * Map数据转成 Json 数据
	 * 
	 * @param object
	 * @return
	 */
	public static String Map2Json(Object object) {

		return JSON.toJSONString(object);
	}
}
