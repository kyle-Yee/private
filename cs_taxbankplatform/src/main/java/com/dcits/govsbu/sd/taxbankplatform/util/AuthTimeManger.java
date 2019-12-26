package com.dcits.govsbu.sd.taxbankplatform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/***
 * 
	* @author dms
	* @date 2019年1月16日
	* @Description:授权时间管理
 */

public class AuthTimeManger {

	private AuthTimeManger() {}
	
	/***
	 *   @param authTime:授权时间
	 *	 @return true:授权时间没有过期 false:授权时间过期了
	 */
	public static Boolean AuthCanUse(String authTime) { 
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date authTime_date = simpleDateFormat.parse(authTime);
			Date curTime_date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
			if(curTime_date.getTime() > authTime_date.getTime() + (1000 * 60 * 60 * 24 * 7)) {//7天缓冲
				return false;
			}else {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;		
	}
		
	

	
}
