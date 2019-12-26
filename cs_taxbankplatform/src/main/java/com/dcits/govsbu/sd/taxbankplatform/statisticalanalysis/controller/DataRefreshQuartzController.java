package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.sendmessage.templet.TemplateSms;
import com.dcits.govsbu.sd.taxbankplatform.sendmessage.token.TokenUtil;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.DataRefreshService;
import com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.model.SystemEntity;
import com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.service.SystemService;
import com.dcits.govsbu.sd.taxbankplatform.util.MessageUtil;
/**
 * 任务调度：
 * 模块：银税互动资料更新
 * 提醒功能：每月的1日，向银行的总行用户发送手机短信提醒
 * @author 10856
 *  
 */
@Controller
@Scope("prototype")
public class DataRefreshQuartzController{
	public Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DataRefreshService dataRefreshService;
	
	@Autowired
	private SystemService systemService;
	
	public void test(){
		System.out.println();System.out.println();System.out.println();
		System.out.println(new Date()+"###########################任务调度###########################");
		System.out.println();System.out.println();System.out.println();
	}
	
	public void sendMessage() throws Exception{
		System.out.println(new Date()+"###########################任务调度短信接口###########################");
		
		//所有银行总部
		List<Map<String,Object>> orgList = dataRefreshService.getCoreBank();
		if(orgList.size()==0){
			System.out.println(new Date()+"#######################该系统没有找到银行总部#########################");
			return;
		}
		
		for (Map<String,Object> map : orgList) {
			String org_id = (String)map.get("org_id");//银行总部组织id
			
			//该银行总部所有的用户
			List<Map<String,Object>> userList = dataRefreshService.getBankAllUser(org_id);
			if(userList.size()==0){
				System.out.println(new Date()+"#######################没有找到该银行【"+map.get("org_name")+"】总部用户#########################");
				continue;
			}
			
			for (Map<String, Object> mapU : userList) {
				if(mapU.get("phone") == null){
					System.out.println(new Date()+"#######################没有找到该银行【"+map.get("org_name")+"】总部用户【"+mapU.get("u_name")+"】的联系号码#########################");
					continue;
				}

				//参数
				Map<String,Object> parmMap = new HashMap<>();
				parmMap.put("phonenumber", mapU.get("phone"));
				parmMap.put("code", "dataRefresh");
				
				//*****************************调用短信接口start******************************//
				Map<String,Object> resultMap = new HashMap<>();
				try {
					SystemEntity SystemEntity = systemService.queryFlagByXtcs("10002");
					String enable = SystemEntity.getEnabled();
//					enable = "N";
					if("Y".equals(enable)){
						//测试，不做验证码校验
						resultMap.put("success", Boolean.TRUE);
					}else{
						String APP_ID = MessageUtil.getAppId();
						String APP_SECRET = MessageUtil.getAppSecret();
						String ACCESS_TOKEN = MessageUtil.getAccessToken();
						
						//选择短信发送模板类型
						String  TEMPLATE_ID = MessageUtil.getDataRefreshtemplateId();
						
						//发送到手机
						String result = TemplateSms.sendSms(parmMap, APP_ID, APP_SECRET, ACCESS_TOKEN, TEMPLATE_ID);
						
						if(null == result){
							//发送失败（token不正确，可能已经过期）
							String oauth_model = "CC";
							String accessToken = TokenUtil.getAccessToken(oauth_model);
							JSONObject jsonObject =  JSONObject.fromObject(accessToken);
							String success = jsonObject.getString("res_message");
							if(success.equals("Success")){
								ACCESS_TOKEN = jsonObject.getString("access_token");
								String results = TemplateSms.sendSms(parmMap, APP_ID, APP_SECRET, ACCESS_TOKEN, TEMPLATE_ID);
								if(results == null){
									resultMap.put("success", Boolean.FALSE);
								}else{
									resultMap.put("success", Boolean.TRUE);
								}
							}else{
								resultMap.put("success", Boolean.TRUE);
							}
						}else{
							resultMap.put("success", Boolean.TRUE);
						}
					}
				}catch(ServiceException e)
				{
					throw new AjaxException(e);
				}
				//*****************************调用短信接口end******************************//
				
				if((boolean) resultMap.get("success")){
					System.out.println(new Date()+"#######################银行【"+map.get("org_name")+"】总部用户【"+mapU.get("u_name")+"】号码【"+mapU.get("phone")+"】短信提醒发送成功#########################");
				}else{
					System.out.println(new Date()+"#######################银行【"+map.get("org_name")+"】总部用户【"+mapU.get("u_name")+"】号码【"+mapU.get("phone")+"】短信提醒发送失败#########################");
				}
				
			}
			
			
		}
		
		
		
		
	}
	
	
	
}
