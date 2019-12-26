package com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model.AuthorizationrecordEntity;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.service.AuthorizationrecordService;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.teplate.AuthorizeEnum;
import com.dcits.govsbu.sd.taxbankplatform.util.DateFormatter;
import com.dcits.govsbu.sd.taxbankplatform.util.HttpClientUtil;
import com.dcits.govsbu.sd.taxbankplatform.util.StringUtil;
/**
 * 授权编码有效性检验
 * @author liuc
 * @time 2018年6月26日14:21:38
 */
@Controller
@Scope("prototype")
@RequestMapping("/")

public class AuthorizeIsValidController {
	@Autowired
	private AuthorizationrecordService authorizationrecordService;
	/**
	 * 日志
	 */
	public Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 验证授权编码是否有效
	 * liuc
	 * */
	@RequestMapping(value = "/authorizeIsValid", method = RequestMethod.POST)
	public void authorizeIsValid(HttpServletRequest request,HttpServletResponse response){
		String isValid = "N";
		String statusMsg = "";
		String statusCode = "";	
		String validTime = "";	
		try{
			//获取接口报文
			String content = HttpClientUtil.getHttpContent(request);
			logger.info("请求验证授权编码有效性："+content);
			if(null != content && !"".equals(content)){
				JSONObject json = JSONObject.parseObject(content);
				//String tbpName = StringUtil.getJsonValue(json, "tbpName");
				String tbpCode = StringUtil.getJsonValue(json, "tbpCode");
				String businessType = StringUtil.getJsonValue(json, "businessType");
				//String indName = StringUtil.getJsonValue(json, "indName");
				//String indCertType = StringUtil.getJsonValue(json, "indCertType");
				String indCertID = StringUtil.getJsonValue(json, "indCertID");
				String entName = StringUtil.getJsonValue(json, "entName");
				String entCreditID = StringUtil.getJsonValue(json, "entCreditID");
				String entTaxID = StringUtil.getJsonValue(json, "entTaxID");
				String grantCode = StringUtil.getJsonValue(json, "grantCode");
				String nsrsbh = null != entCreditID && !"".equals(entCreditID)?entCreditID:entTaxID; 
				Date tbday = new Date();
				//根据纳税人识别号和企业名称、法定代表人证件号、授权编码查询授权记录
				AuthorizationrecordEntity authorizationrecordEntity = authorizationrecordService.findByGrantCode(grantCode);
				if(null != authorizationrecordEntity){
					if(String.valueOf(nsrsbh).equals(authorizationrecordEntity.getAt_nsrsbh())
						&& String.valueOf(entName).equals(authorizationrecordEntity.getAt_qymc())
						&& String.valueOf(indCertID).equals(authorizationrecordEntity.getAt_frsfz())
						&& String.valueOf(tbpCode).equals(authorizationrecordEntity.getOrg_id())
						&& String.valueOf(businessType).equals(authorizationrecordEntity.getFp_id())){
						String startTime = authorizationrecordEntity.getAt_sqkssj()+ " 00:00";
						String endTime = authorizationrecordEntity.getAt_sqjssj()+ " 24:00";
						 if(DateFormatter.isEffectiveDate(tbday, DateFormatter.StrToDate(startTime,DateFormatter.LX2),
								 DateFormatter.StrToDate(endTime,DateFormatter.LX2))){//判断是否在有效期之内
							 isValid = "Y";
							 validTime = authorizationrecordEntity.getAt_sqjssj();
							 statusMsg = AuthorizeEnum.DM00.getStatusMsg();
							 statusCode = AuthorizeEnum.DM00.getStatusCode(); 
						 }else{
							statusMsg = AuthorizeEnum.DM03.getStatusMsg();
							statusCode = AuthorizeEnum.DM03.getStatusCode(); 
						 }
					}else{
						statusMsg = AuthorizeEnum.DM02.getStatusMsg();
						statusCode = AuthorizeEnum.DM02.getStatusCode();
					}
				}else{
					statusMsg = AuthorizeEnum.DM04.getStatusMsg();
					statusCode = AuthorizeEnum.DM04.getStatusCode();
				}	
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String , String> respMap = new HashMap<String, String>();
		respMap.put("statusMsg", statusMsg);
		respMap.put("statusCode", statusCode);
		respMap.put("isValid", isValid);
		respMap.put("validTime", validTime);
		logger.info("请求验证授权编码有效性："+JSON.toJSONString(respMap));
		HttpClientUtil.setHttpContent(response,JSON.toJSONString(respMap));
	}
}
