package com.dcits.govsbu.sd.taxbankplatform.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.jhlabs.image.FourColorFilter;
 

/**
 * 字符串转换工具类
 * */
public class StringUtil {
	/**
	 * 异常值
	 * */
	private static final BigDecimal ycz = new BigDecimal(0);
	/**
	 * 字符串转BigDecimal
	 * */
	public static BigDecimal StringToBig(Object strobj){
		String str = String.valueOf(strobj);
		if(null == str || "".equals(str) || "null".equals(str) || "NULL".equals(str)){
			str = "0";
		}
		try{
		   return new BigDecimal(str.trim());
		}catch(Exception e){
		}
		return new BigDecimal("0");
	}
	
	public static BigDecimal objToBigIsNull(Object strobj){
		String str = String.valueOf(strobj);
		try {
			if(null != str && !"".equals(str) && !"null".equals(str)
					&& !"NULL".equals(str)){
				return  new BigDecimal(str).setScale(2,   BigDecimal.ROUND_DOWN);
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	 public static String getText(Object o){
			if(o!=null){
				return o.toString().trim();
			}else{
				return "";
			}
		 }
	
	public static String objToBigIsStrNotDouble(Object strobj){
		String str = String.valueOf(strobj);
		str = str.replace(",", "");
		str = str.replace("，", "");
		try {
			BigDecimal shejz = new BigDecimal(str).setScale(2,   BigDecimal.ROUND_DOWN);
			BigDecimal ycz = new BigDecimal("99999999999999");
			BigDecimal ycz2 = new BigDecimal("-9999999999999");
			if(shejz.compareTo(ycz) > 0){
				return "";
			}
			
			if(shejz.compareTo(ycz2) < 0){
				return "";
			}
			Double shejzdou = shejz.doubleValue();
			String ygz = String.valueOf(shejzdou);
			String[] yiqz = ygz.split("\\.");
			int bijz = Integer.parseInt(yiqz[0]);
			if(shejzdou - bijz != 0){
				return "";
			}
			return String.valueOf(shejz);
		} catch (Exception e) {
			return "";
		}
	}
	 
	public static String objToBigIsStr(Object strobj){
		String str = String.valueOf(strobj);
		str = str.replace(",", "");
		str = str.replace("，", "");
		try {
			BigDecimal shejz = new BigDecimal(str).setScale(2,   BigDecimal.ROUND_DOWN);
			BigDecimal ycz = new BigDecimal("99999999999999");
			BigDecimal ycz2 = new BigDecimal("-9999999999999");
			if(shejz.compareTo(ycz) > 0){
				return "";
			}
			
			if(shejz.compareTo(ycz2) < 0){
				return "";
			}
			return String.valueOf(shejz);
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 判断对象值是不是数字，
	 * 是则保留8位小数(不进行四舍五入，直接截取多余的小数位)，否则返回空字符串
	 * @param strobj
	 * @return
	 */
	public static String objToBigSave8w(Object strobj){
		String str = String.valueOf(strobj);
		str = str.replace(",", "").trim();
		str = str.replace("，", "");
		str = str.replaceFirst("^0*", "");  
		try {
			BigDecimal shejz = new BigDecimal(str).setScale(2,BigDecimal.ROUND_DOWN);
			BigDecimal ycz = new BigDecimal("99999999999999");
			BigDecimal ycz2 = new BigDecimal("-9999999999999");
			if(shejz.compareTo(ycz) > 0){
				return "";
			}
			
			if(shejz.compareTo(ycz2) < 0){
				return "";
			}
			return String.valueOf(shejz);
		} catch (Exception e) {
			return "";
		}
	}
 
	/**
	 * 判断对象值是不是数字，
	 * 是则保留8位小数(不进行四舍五入，直接截取多余的小数位)，否则返回空字符串
	 * @param strobj
	 * @return
	 */
	public static BigDecimal objRetBigSave8w(Object strobj){
		String str = String.valueOf(strobj);
		str = str.replace(",", "").trim();
		str = str.replace("，", "");
		str = str.replaceFirst("^0*", "");  
		try {
			BigDecimal shejz = new BigDecimal(str).setScale(2,BigDecimal.ROUND_DOWN);
			BigDecimal ycz = new BigDecimal("99999999999999");
			BigDecimal ycz2 = new BigDecimal("-9999999999999");
			if(shejz.compareTo(ycz) > 0){
				return null;
			}
			
			if(shejz.compareTo(ycz2) < 0){
				return null;
			}
			return shejz;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String objToIntIsStr(Object strobj){
		String str = String.valueOf(strobj);
		str = str.replace(",", "");
		str = str.replace("，", "");
		try {
			return String.valueOf(Integer.valueOf(str));
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String judeStrIsBig(String str){
		String result = "";
		if(str != null || !"".equals(str)){
			try {
				result = String.valueOf(new BigDecimal(str));
			} catch (Exception e) {
				result = "";
			}
		}
		return result;
	}
	
	public static String objToYfStr(String ob){
		String [] yfs = {"1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
                "12",
                "01",
                "02",
                "03",
                "04",
                "05",
                "06",
                "07",
                "08",
                "09"};
	if(ob!=null && !"".equals(ob)){
		if(ob.length()>2){
			return "";
		}else{
			for(String yfdb:yfs){
				if(yfdb.equals(ob)){
					return  ob;
				}
			}
			return  "";
		}
	}else{
		return "";
	}
	}
	/**
	 * 比较2个BigDecimal 得到最大的
	 * */
	public static BigDecimal maxBigDecimal(BigDecimal v1,BigDecimal v2){
		  if(null == v1){
			   return v2;
		   }else if(null == v2){
			   return v1;
		   }else if(v1.compareTo(v2) > 0){
			   return v1;
		   }else{
			   return v2;
		   }
		   
	}
	/**
	 * 比较2个BigDecimal 得到最小的
	 * */
	public static BigDecimal minBigDecimal(BigDecimal v1,BigDecimal v2){
		   if(null == v1){
			   return v2;
		   }else if(null == v2){
			   return v1;
		   }else if(v1.compareTo(v2) > 0){
			   return v2;
		   }else{
			   return v1;
		   }
	}
	/**
	 * 两个BigDecimal相除 留8位小数 比例处理
	 * */
	public static BigDecimal dividebl(BigDecimal fz,BigDecimal fm){
		   if(fz == null || fm == null){
			   return null;
		   }else if(fm.compareTo(ycz) != 0){
			   return fz.divide(fm,8,BigDecimal.ROUND_HALF_UP);
		   }else{
			   if(fz.compareTo(ycz) < 0){
			   return new BigDecimal("-999999999999999");
			   }else if(fz.compareTo(ycz) == 0){
				   return null;
			   }else{
				   return new BigDecimal("999999999999999");
			   }
		   }
	}
	
	public static BigDecimal newdividezzl(BigDecimal fz,BigDecimal fm){
		   if(fz == null || fm == null){
			   return null;
		   }else if(fm.compareTo(ycz) > 0 ){
			   return fz.subtract(fm).divide(fm,8,BigDecimal.ROUND_HALF_UP);
		   }else if(fm.compareTo(ycz) < 0){
			   return dividebl(fz.subtract(fm),ycz.subtract(fm));
		   }else if(fm.compareTo(ycz) == 0 && fz.compareTo(ycz) == 0){
			   return ycz;
		   }else{
			   return dividebl(fz.subtract(fm), fm);
		   }
		    
	}
	
	/**
	 * 两个BigDecimal相除 留8位小数  如果分母为零值也等于0
	 * */
	public static BigDecimal divide(BigDecimal fz,BigDecimal fm){
		   if(fm.compareTo(ycz) != 0){
			   return fz.divide(fm,8,BigDecimal.ROUND_HALF_UP);
		   }else{
			   return  ycz;
		   }
	}
	
	/**
	 * 多个BigDecimal取最大值
	 */
	public static BigDecimal maxBigDecimal(BigDecimal []p_arry){
		BigDecimal v_bdval = null;
		ArrayList<BigDecimal> v_list = new ArrayList<BigDecimal>();
		for(int i = 0; i < p_arry.length; i++){
			if(p_arry[i] != null){
				v_list.add(p_arry[i]);
			}
		}
		if(v_list.size() > 0){
			v_bdval = v_list.get(0);
			for(int i = 0; i < v_list.size(); i++){
				if(v_list.get(i).compareTo(v_bdval) > 0){
					v_bdval = v_list.get(i);
				}
			}
		}
		return v_bdval;
	}
	
 
	
	public static Integer strToint(String str){
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 多个BigDecimal取最小值
	 */
	public static BigDecimal minBigDecimal(BigDecimal []p_arry){
		BigDecimal v_bdval = null;
		ArrayList<BigDecimal> v_list = new ArrayList<BigDecimal>();
		for(int i = 0; i < p_arry.length; i++){
			if(p_arry[i] != null){
				v_list.add(p_arry[i]);
			}
		}
		if(v_list.size() > 0){
			v_bdval = v_list.get(0);
			for(int i = 0; i < v_list.size(); i++){
				if(v_list.get(i).compareTo(v_bdval) < 0){
					v_bdval = v_list.get(i);
				}
			}
		}
		return v_bdval;
	}
	/**
	 * 
	     * @discription 比较两个日期相差的年数、月数、天数
	     * @author xiecui       
	     * @created 2017-7-25 下午2:19:10     
	     * @param date1
	     * @param date2
	     * @param stype
	     * @return
	 */
	public static int compareDate(String date1,String date2,int stype){  
        int n = 0;  
        String formatStyle = stype==1?"yyyy-MM":"yyyy-MM-dd";  
        date2 = date2==null || "".equals(date2)?StringUtil.getCurrentDate():date2;  
        DateFormat df = new SimpleDateFormat(formatStyle);  
        Calendar c1 = Calendar.getInstance();  
        Calendar c2 = Calendar.getInstance();  
        try {  
            c1.setTime(df.parse(date1));  
            c2.setTime(df.parse(date2));  
        } catch (Exception e3) {  
            e3.printStackTrace();  
        }  
        while (!c1.after(c2)) {                     // 循环对比，直到相等，n 就是所要的结果  
            n++;  
            if(stype==1){  
                c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1  
            }  
            else{  
                c1.add(Calendar.DATE, 1);           // 比较天数，日期+1  
            }  
        }           
        n = n-1;        
        if(stype==2){  
            n = (int)n/365;  
        }        
        return n;  
    }  
       
    /**  
     * 得到当前日期  
     * @return  
     */ 
    public static String getCurrentDate() {  
        Calendar c = Calendar.getInstance();  
        Date date = c.getTime();  
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");  
        return simple.format(date);  
 
    }
    
    /**
     * 比较日期大小，返回最近日期
     * @param DATE1
     * @param DATE2
     * @return
     * @throws ParseException
     */
    public static String maxDate(String DATE1, String DATE2) throws ParseException {  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        Date dt1 = df.parse(DATE1);
        Date dt2 = df.parse(DATE2);
        if (dt1.getTime() >= dt2.getTime()) {
            return df.format(dt1);
        } else  {
            return df.format(dt2);
        } 
    }	
    public static String  resultDataNullToStr(Object obj){
    	if(null == obj){
    		return "";
    	}
    	return String.valueOf(obj);
    }
    
    /**
     * 
         * @discription 判断是否是合格的日期格式
         * @author xiecui       
         * @created 2017-8-8 下午7:23:08     
         * @param strDate
         * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 
         * @discription 字符串转合法日期格式
         * @author xiecui       
         * @created 2017-8-8 下午5:54:18     
         * @param strDate
         * @return
     */
    public static String strToDateFormat(String strDate){
//    	DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
    	if (isDate(strDate)){
    		strDate = strDate.replaceAll("/", "-");
    		return strDate;
    	}else {
    		return null;
    	}
    }
    
    public static String objToLongString(Object str){
    		try {
    			String strs = str.toString().trim();
    			if(strs!=null && !"NULL".equals(strs) && !"".equals(strs)){
    				return String.valueOf(Long.valueOf(strs));	
    			}else{
    				return "";
    			}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return "";
				
			}
    }
    
    public static Long strToLong (String str){
		try {
			if(str!=null && !"NULL".equals(str) && !"".equals(str)){
				return  Long.valueOf(str.toString()) ;	
			}else{
				return null;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
    }
    
	public static String objToNdStr(String nd) {
	if(nd!=null && !"".equals(nd)){
		try{
		Integer.parseInt(nd);
		if( nd.length()!=4 ){
			return "";
		}else{
			return nd;
		}
		}catch(Exception e){
			return "";
		}
	}else{
		return"";
	}
	}
	
	public static String getyf(Object nd) {
		String ndjs = String.valueOf(nd);
		String[] yfs = {"01","02","03","04","05","06","07","08","09"};
		for(String yfa :yfs){
		if(yfa.equals(ndjs)){
			 return ndjs.substring(1, 2);
		}
		}
		try{
		if(Integer.parseInt(ndjs) <= 12 && Integer.parseInt(ndjs) > 0){
			return ndjs;
		}else{
			return "";
		}
		}catch(Exception e){
			return "";
		}
		}
	
	public static String isDecimal(String str){
		try{
			if (str != null){
				if (str.indexOf(".") >= 0){
					if (Double.parseDouble(str) < 0 || Long.parseLong(str.substring(str.indexOf(".")+1)) > 0){
						return "";
					}else {
						return str.replaceAll(" ", "");
					}
				}else {
					return str.replaceAll(" ", "");
				}
			}else {
				return null;
			}
		}catch (Exception e){
			return null;
		}
	}
	
	public static String objToStr(Object obj){
		String objstr = String.valueOf(obj);
		if("null".equals(objstr) || "NULL".equals(objstr)){
			return "";
		}
		return objstr;
	}
	
	public static String subStr(String str){
		if (str != null && !"".equals(str)){
			if (str.indexOf(":") > 0){
				return str.substring(str.indexOf(":")+1);
			}else if (str.indexOf("：") > 0){
				return str.substring(str.indexOf("：")+1);
			}else {
				return "";
			}
		}else {
			return "";
		}
	}
	/**  
	* 提供精确的加法运算。  
	* @param v1 被加数  
	* @param v2 加数  
	* @return 两个参数的和  
	*/  
	public static double add(String v1,String v2){ 
		BigDecimal  b1 ;
		BigDecimal  b2;
		v1=objToBigSave8w(v1);
		v2=objToBigSave8w(v2);
		if(v1==null||("").equals(v1)){
			 b1 = new BigDecimal("0");  
		}else{
			b1=new BigDecimal(v1);
		}
		if(v2==null||("").equals(v2)){
			 b2 = new BigDecimal("0");  
		}else{
			b2=new BigDecimal(v2);
		}
	return  b1.add(b2).doubleValue();   
	} 
	  
	/**  
	* 提供精确的减法运算。  
	* @param v1 被减数  
	* @param v2 减数  
	* @return 两个参数的差  
	*/  
	public static double sub(double v1,double v2){   
	BigDecimal b1 = new BigDecimal(Double.toString(v1));   
	BigDecimal b2 = new BigDecimal(Double.toString(v2));   
	return b1.subtract(b2).doubleValue();   
	}   
	/**  
	* 提供精确的乘法运算。  
	* @param v1 被乘数  
	* @param v2 乘数  
	* @return 两个参数的积  
	*/  
	public static double mul(double v1,double v2){   
	BigDecimal b1 = new BigDecimal(Double.toString(v1));   
	BigDecimal b2 = new BigDecimal(Double.toString(v2));   
	return b1.multiply(b2).doubleValue();   
	}   
	/**
	    *将字符串格式yyyyMMdd的字符串转为日期，格式"yyyy-MM-dd"
	    *
	    * @param date 日期字符串
	    * @return 返回格式化的日期
	    * @throws ParseException 分析时意外地出现了错误异常
	    */
     public static String strToDateFormat2(String date) throws ParseException {
	       SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	       formatter.setLenient(false);
	       Date newDate= formatter.parse(date);
	       formatter = new SimpleDateFormat("yyyy-MM-dd");
	       return formatter.format(newDate);
	   }
     /**
      * 比较日期大小,返回
      * @param DATE1
      * @param DATE2
      * @return
      * @throws ParseException
      */
     public static String refDate(String DATE1, String DATE2) throws ParseException {  
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
         Date dt1 = df.parse(DATE1);
         Date dt2 = df.parse(DATE2);
         if (dt1.getTime() >= dt2.getTime()) {
             return "ok";
         } else  {
             return "fail";
         } 
     }
     
     /**
      * json获取数据返回字符串
      * liuc
      * */
     public static String getJsonValue(JSONObject json,String key) throws Exception{
    	 String value = "";
    	 if(null != json){
    		 if(json.containsKey(key)){
    			 value = String.valueOf(json.get(key));
    		 }
    	 }
    	 return  value;
     }
}
