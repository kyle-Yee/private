package com.dcits.govsbu.sd.taxbankplatform.sendmessage.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.sendmessage.templet.TemplateSms;
import com.dcits.govsbu.sd.taxbankplatform.sendmessage.token.TokenUtil;
import com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.model.SystemEntity;
import com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.service.SystemService;
import com.dcits.govsbu.sd.taxbankplatform.util.MessageUtil;

/**
 * 发送短信通知
 * @author 16420
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/sendmessage/")
public class SendmessageController {
	public static String code = null;
	@Autowired
	private SystemService systemService;
	/**
	 * 贷款审批通知
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "approveresult.html")
	@ResponseBody
	public Object sendmessage1(HttpServletRequest request) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SystemEntity SystemEntity = systemService.queryFlagByXtcs("10002");
			String enable = SystemEntity.getEnabled();
			if("Y".equals(enable)){
				//测试，不做验证码校验
				map.put("success", Boolean.TRUE);
			}else{
				String APP_ID = MessageUtil.getAppId();
				String APP_SECRET = MessageUtil.getAppSecret();
				String ACCESS_TOKEN = MessageUtil.getAccessToken();
				String phonenumber = request.getParameter("phonenumber");
	//			String phonenumber = "";
				//选择短信发送模板类型
				String  TEMPLATE_ID = MessageUtil.getLoanapplytemplateId();
				//获取企业贷款信息（企业名称，申贷日期，申请银行，申请产品信息）
				int year = 0;
				int month = 0;
				int day = 0;
				if (request.getParameter("dksqsj") != null && request.getParameter("dksqsj") != "" ){
					String date = request.getParameter("dksqsj");
					SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
					SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date1 = sdf2.format(sdf1.parse(date));
					String[] split = date1.substring(0, 10).split("-");
					
					year = Integer.parseInt( split[0]);
					month = Integer.parseInt(split[1]);
					day = Integer.parseInt(split[2]);
				}
				String sqyh1 = request.getParameter("sqyh");
				String sqyh = sqyh1.substring(0, sqyh1.length()-2);
				map.put("qymc", request.getParameter("qymc"));
	//			map.put("qymc", "送你回家公司");
				map.put("year", year);
				map.put("month", month);
				map.put("day", day);
				map.put("sqyh", sqyh);
				map.put("phonenumber", phonenumber);
				map.put("code", "loanapproveresult");
				//发送到手机
				String result = TemplateSms.sendSms(map, APP_ID, APP_SECRET, ACCESS_TOKEN, TEMPLATE_ID);
				
				if(null == result){
					//发送失败（token不正确，可能已经过期）
					String oauth_model = "CC";
					String accessToken = TokenUtil.getAccessToken(oauth_model);
					JSONObject jsonObject =  JSONObject.fromObject(accessToken);
					String success = jsonObject.getString("res_message");
					if(success.equals("Success")){
						ACCESS_TOKEN = jsonObject.getString("access_token");
						String results = TemplateSms.sendSms(map, APP_ID, APP_SECRET, ACCESS_TOKEN, TEMPLATE_ID);
						if(results == null){
							map.put("success", Boolean.FALSE);
						}else{
							map.put("success", Boolean.TRUE);
						}
					}else{
						map.put("success", Boolean.TRUE);
					}
				}else{
					map.put("success", Boolean.TRUE);
				}
			}
		}catch(ServiceException e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	@RequestMapping(value = "createUser.html")
	@ResponseBody
	public Object sendmessage2(HttpServletRequest request) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SystemEntity SystemEntity = systemService.queryFlagByXtcs("10002");
			String enable = SystemEntity.getEnabled();
			if("Y".equals(enable)){
				//测试，不做验证码校验
				map.put("success", Boolean.TRUE);
			}else{
				String APP_ID = MessageUtil.getAppId();
				String APP_SECRET = MessageUtil.getAppSecret();
				String ACCESS_TOKEN = MessageUtil.getAccessToken();
				String phonenumber = request.getParameter("phonenumber");
	//			String phonenumber = "";
				//选择短信发送模板类型
				String  TEMPLATE_ID = MessageUtil.getUsermanagertemplateId();
				//获取账户、密码
				map.put("accountName", request.getParameter("accountName"));
				map.put("passWord", request.getParameter("password"));
				map.put("phonenumber", phonenumber);
				map.put("code", "createuser");
				//发送到手机
				String result = TemplateSms.sendSms(map, APP_ID, APP_SECRET, ACCESS_TOKEN, TEMPLATE_ID);
				
				if(null == result){
					//发送失败（token不正确，可能已经过期）
					String oauth_model = "CC";
					String accessToken = TokenUtil.getAccessToken(oauth_model);
					JSONObject jsonObject =  JSONObject.fromObject(accessToken);
					String success = jsonObject.getString("res_message");
					if(success.equals("Success")){
						ACCESS_TOKEN = jsonObject.getString("access_token");
						String results = TemplateSms.sendSms(map, APP_ID, APP_SECRET, ACCESS_TOKEN, TEMPLATE_ID);
						if(results == null){
							map.put("success", Boolean.FALSE);
						}else{
							map.put("success", Boolean.TRUE);
						}
					}else{
						map.put("success", Boolean.TRUE);
					}
				}else{
					map.put("success", Boolean.TRUE);
				}
			}
		}catch(ServiceException e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
}
