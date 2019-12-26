package com.dcits.govsbu.sd.taxbankplatform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

/**
 * 
 * DateFormatter.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption springmvc日期转换器
 * springmvc默认只接收String类型，日期类型的需要自定义转换器
 */
public class DateFormatter implements Formatter<Date>{
	public final static String LX1= "yyyy-MM-dd";
	public final static String LX2= "yyyy-MM-dd HH:mm";
	@Override
	public String print(Date object, Locale locale) {
		return null;
	}
	
	/**
	 * 时间转换方法
	 * */
	public static Date StrToDate(String text, String format) throws ParseException {
		SimpleDateFormat  simpleDateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = simpleDateFormat.parse(text);
			return date;
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(text);
		} catch (Exception e) {
			format = new SimpleDateFormat("yyyy-MM-dd");
			date = format.parse(text);
		}
		return date;
	}
	/**
	 * 特定时间减特定的月份
	 * **/
	public static String dateToStr(Date timeStr,String dateFormat){
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		try {
		    return format.format(timeStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	}
	
	/**
	 * 特定时间减特定的一天
	 * **/
	public static String timeJsDay(Date timeStr,String dateFormat,int day){
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		try {
			Date d = timeStr;
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(Calendar.DAY_OF_MONTH, -day);
		    return format.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	}
	
	
	/**
	 * 特定时间加减特定的一天
	 * **/
	public static Date timeAddDay(Date timeStr,int day){
		try {
			Date d = timeStr;
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(Calendar.DAY_OF_MONTH, day);
		    return  c.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	}
	
	
	 /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     * 
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
       try{
    	if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
       }catch(Exception e){
    	   e.printStackTrace();
       }
       return false;
    }
	
}
