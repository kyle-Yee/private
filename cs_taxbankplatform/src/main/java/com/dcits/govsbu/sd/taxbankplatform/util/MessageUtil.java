package com.dcits.govsbu.sd.taxbankplatform.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("MessageUtil") 
public class MessageUtil {
	
	@Value("#{messageUtil.appId}")
	private static String appId;
	
	@Value("#{messageUtil.appSecret}")
	private static String appSecret;
	
	@Value("#{messageUtil.accessToken}")
	private static String accessToken;
	
	@Value("#{messageUtil.loanapplytemplateId}")
	private static String loanapplytemplateId;
	
	@Value("#{messageUtil.usermanagertemplateId}")
	private static String usermanagertemplateId;
	
	@Value("#{messageUtil.dataRefreshtemplateId}")
	private static String dataRefreshtemplateId;
	
	private static String dqyhDM;
	
	public static String getDataRefreshtemplateId() {
		return dataRefreshtemplateId;
	}

	public static void setDataRefreshtemplateId(String dataRefreshtemplateId) {
		MessageUtil.dataRefreshtemplateId = dataRefreshtemplateId;
	}

	public static String getAppId() {
		return appId;
	}

	public static void setAppId(String appId) {
		MessageUtil.appId = appId;
	}

	public static String getAppSecret() {
		return appSecret;
	}

	public static void setAppSecret(String appSecret) {
		MessageUtil.appSecret = appSecret;
	}

	public static String getAccessToken() {
		return accessToken;
	}

	public static void setAccessToken(String accessToken) {
		MessageUtil.accessToken = accessToken;
	}

	public static String getLoanapplytemplateId() {
		return loanapplytemplateId;
	}

	public static String getUsermanagertemplateId() {
		return usermanagertemplateId;
	}

	public static void setUsermanagertemplateId(String usermanagertemplateId) {
		MessageUtil.usermanagertemplateId = usermanagertemplateId;
	}

	public static void setLoanapplytemplateId(String loanapplytemplateId) {
		MessageUtil.loanapplytemplateId = loanapplytemplateId;
	}

	public static String getDqyhDM() {
		return dqyhDM;
	}

	public static void setDqyhDM(String dqyhDM) {
		MessageUtil.dqyhDM = dqyhDM;
	}
	
}
