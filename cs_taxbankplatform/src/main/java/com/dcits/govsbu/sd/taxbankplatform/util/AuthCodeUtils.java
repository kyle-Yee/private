package com.dcits.govsbu.sd.taxbankplatform.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 
	* @author dms
	* @date 2019年1月17日
	* @Description:生成授权码
 */

public class AuthCodeUtils {

	
	private AuthCodeUtils() {}
	
	public static synchronized String createGrandCode() {
		StringBuffer sb = new StringBuffer(50);
		try {
			Thread.sleep(100);
			sb.append(UUID.randomUUID().toString().trim().replaceAll("-", "")); 
			sb.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())); //17
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return sb.toString();
		
	}


	
	
}
