package com.dcits.govsbu.sd.taxbankplatform.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明:字符串处理工具集
 * @author liwangxiong
 */
public class StringHelper {

	/**
	 * 
	 * @Title: replaceLast
	 * @Description: TODO(截取替换last字符)
	 * @param @param string
	 * @param @param toReplace
	 * @param @param replacement
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	public static String replaceLast(String string, String toReplace, String replacement) {
		int pos = string.lastIndexOf(toReplace);
		if (pos > -1) {
			return string.substring(0, pos)
					+ replacement
					+ string.substring(pos + toReplace.length(), string.length());
		} else {
			return string;
		}
	}
	/**
	 *将空字符串转换为""
	 * @param 原始字符串
	 * @return 返回转换后的字符串
	 */
	public static final String convertStringNull(String strOrig) {
		String strReturn= "";
		if (strOrig == null || strOrig.equals("null")) {
			strReturn= "";
		} else {
			strReturn= strOrig.trim();
		}
		return strReturn;
	}

	public static String doWithNull(Object o) {
		if(o==null)
			return "";
		else
			return o.toString().replaceAll("&amp;", "&");
	}

	public static String[] split(String strSource, String strDiv) {
		int arynum = 0, intIdx = 0, intIdex = 0;
		int div_length = strDiv.length();
		if (strSource.compareTo("") != 0) {
			if (strSource.indexOf(strDiv) != -1) {
				intIdx = strSource.indexOf(strDiv);
				for (int intCount = 1; ; intCount++) {
					if (strSource.indexOf(strDiv, intIdx + div_length) != -1) {
						intIdx = strSource.indexOf(strDiv, intIdx + div_length);
						arynum = intCount;
					}
					else {
						arynum += 2;
						break;
					}
				}
			}
			else {
				arynum = 1;
			}
		}
		else {
			arynum = 0;

		}
		intIdx = 0;
		intIdex = 0;
		String[] returnStr = new String[arynum];

		if (strSource.compareTo("") != 0) {
			if (strSource.indexOf(strDiv) != -1) {
				intIdx = strSource.indexOf(strDiv);
				returnStr[0] = strSource.substring(0, intIdx);
				for (int intCount = 1; ; intCount++) {
					if (strSource.indexOf(strDiv, intIdx + div_length) != -1) {
						intIdex = strSource.indexOf(strDiv, intIdx + div_length);
						returnStr[intCount] = strSource.substring(intIdx + div_length,
								intIdex);
						intIdx = strSource.indexOf(strDiv, intIdx + div_length);
					}
					else {
						returnStr[intCount] = strSource.substring(intIdx + div_length,
								strSource.length());
						break;
					}
				}
			}
			else {
				returnStr[0] = strSource.substring(0, strSource.length());
				return returnStr;
			}
		}
		else {
			return returnStr;
		}
		return returnStr;
	}

	public static String[] div(String strSource,String strDiv){
		if(strSource.indexOf(strDiv)==-1){
			String []simpleArr={strDiv};
			return simpleArr;
		}
		String sourceArr[]=null;
		String tarArr[]=null;
		if(!"".equals(strDiv)){
			sourceArr=strSource.split(strDiv);
			if(null!=sourceArr){
				tarArr=new String[sourceArr.length];
				for(int i=0;sourceArr.length>0&&i<sourceArr.length;i++){
					tarArr[i]=sourceArr[i].trim();
				}
			}
			return tarArr;
		}else{
			String []sourArr={strDiv};
			return sourArr;
		}

	}
	public  static String arrToString(String [] arr)
	{
		if(arr==null) return "";
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i]!=null&&!arr[i].equals(""))
				sb.append(arr[i]+",");
		}
		String returnValue=sb.toString();
		if(returnValue.endsWith(",")) returnValue=returnValue.substring(0,returnValue.length()-1);
		return returnValue;
	}



	public  static String arrToString(String [] arr,String splitChar)
	{
		if(splitChar==null) return arrToString(arr);
		if(arr==null) return "";
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i]!=null&&!arr[i].equals(""))
				sb.append(arr[i]+splitChar);
		}
		String returnValue=sb.toString();
		if(returnValue.endsWith(splitChar)) returnValue=returnValue.substring(0,returnValue.length()-splitChar.length());
		return returnValue;
	}


	public  static String objectToString(Object object)
	{
		if(object==null) return "";
		String returnValue="";
		if(object instanceof String)
		{
			returnValue=(String)object;
		}
		else if(object instanceof String[])
		{
			returnValue=StringHelper.arrToString((String []) object);
		}
		else
		{
			returnValue=object.toString();
		}
		return returnValue;
	}

	public  static String objectToString(Object object,String splitChar)
	{
		if(object==null) return "";
		String returnValue="";
		if(object instanceof String)
		{
			returnValue=(String)object;
		}
		else if(object instanceof String[])
		{
			returnValue=StringHelper.arrToString((String []) object,splitChar);
		}
		return returnValue;
	}

	//		Replace the specified string "strSub" with string "strRpl".
	//eg:replace(txt,"\n","<BR>");
	public static String replace(String str, String strSub, String strRpl) {
		String[] tmp = split(str, strSub);
		String returnstr = "";
		if (tmp.length != 0) {
			returnstr = tmp[0];
			for (int i = 0; i < tmp.length - 1; i++) {
				returnstr = doWithNull(returnstr) + strRpl + tmp[i + 1];
			}
		}
		return doWithNull(returnstr);
	}

	public static String htmlEncode(String txt) {
		if (txt != null) {
			txt = replace(txt, "&", "&amp;");
			txt = replace(txt, "&amp;amp;", "&amp;");
			txt = replace(txt, "&amp;quot;", "&quot;");
			txt = replace(txt, "\"", "&quot;");
			txt = replace(txt, "&amp;lt;", "&lt;");
			txt = replace(txt, "<", "&lt;");
			txt = replace(txt, "&amp;gt;", "&gt;");
			txt = replace(txt, ">", "&gt;");
			txt = replace(txt, "&amp;nbsp;", "&nbsp;");
			//txt = replace(txt," ","&nbsp;");
		}
		return txt;
	}

	//Restore string include HTML tags from storage.
	public static String unHtmlEncode(String txt) {
		if (txt != null) {
			txt = replace(txt, "&amp;", "&");
			txt = replace(txt, "&quot;", "\"");
			txt = replace(txt, "&lt;", "<");
			txt = replace(txt, "&gt;", ">");
			txt = replace(txt, "&nbsp;", " ");
		}
		return txt;
	}

	//Encode the string by GBK
	public static String toGBK(String str) {
		if (str != null) {
			byte[] tmpbyte = null;
			try {
				tmpbyte = str.getBytes("ISO8859_1");
			}
			catch (Exception e) {
				System.out.println("Error: Method: StringUtil.toGBK :" + e.getMessage());
			}
			try {
				str = new String(tmpbyte, "GBK");
			}
			catch (Exception e) {
				System.out.println("Error: Method: StringUtil.toGBK :" + e.getMessage());
			}
		}
		return str;
	}

	//Encode the string by ISO8859_1
	public static String toISO(String str) {
		if (str != null) {
			byte[] tmpbyte = null;
			try {
				tmpbyte = str.getBytes("GBK");
			}
			catch (Exception e) {
				System.out.println("Error: Method: StringUtil.toISO :" + e.getMessage());
			}
			try {
				str = new String(tmpbyte, "ISO8859_1");
			}
			catch (Exception e) {
				System.out.println("Error: Method: StringUtil.toISO :" + e.getMessage());
			}
		}
		return str;
	}

	//Encode the string by GBK
	public static String toUTF8(String str) {
		if (str != null) {
			byte[] tmpbyte = null;
			try {
				tmpbyte = str.getBytes("ISO-8859-1");
			}
			catch (Exception e) {
				System.out.println("Error: Method: StringUtil.toUTF8 :" + e.getMessage());
			}
			try {
				str = new String(tmpbyte, "GBK");
			}
			catch (Exception e) {
				System.out.println("Error: Method: StringUtil.toUTF8 :" + e.getMessage());
			}
		}
		return str;
	}


	//		Encode the string by GBK
	public static String utf8TotoGBK(String str) {
		if (str != null) {
			byte[] tmpbyte = null;
			try {
				tmpbyte = str.getBytes("GBK");
			}
			catch (Exception e) {
				System.out.println("Error: Method: StringUtil.toUTF8 :" + e.getMessage());
			}
			try {
				str = new String(tmpbyte, "ISO-8859-1");
			}
			catch (Exception e) {
				System.out.println("Error: Method: StringUtil.toUTF8 :" + e.getMessage());
			}
		}
		return str;
	}

	//		Encode the string by GBK
	public static String utf8ToISO(String str) {
		if (str != null) {
			byte[] tmpbyte = null;
			try {
				tmpbyte = str.getBytes("GBK");
			}
			catch (Exception e) {
				System.out.println("Error: Method: StringUtil.toUTF8 :" + e.getMessage());
			}
			try {
				str = new String(tmpbyte, "GBK");
			}
			catch (Exception e) {
				System.out.println("Error: Method: StringUtil.toUTF8 :" + e.getMessage());
			}
		}
		return str;
	}

	public static String[] strToArrTo(String src,String delim){
		StringTokenizer s=new StringTokenizer(src,delim);
		ArrayList<String> list=new ArrayList<String>();
		while(s.hasMoreElements()){
			list.add((String)s.nextElement());
		}
		Object obj[]=list.toArray();
		String[] arr=new String[obj.length];
		for(int i=0;i<obj.length;i++){
			arr[i]=(String)obj[i];
		}
		return arr;
	}

	/**
	 * 过虑特殊字符
	 * @author mwy
	 */
	public static  String replace(String str){
		if(str!=null){
			str = str.replaceAll("<","");
			str = str.replaceAll(">","");
			str = str.replaceAll("\r\n","");
			str = str.replaceAll("\n\r","");
			str = str.replaceAll("\n", "");
			str = str.replaceAll("\r", "");
			str = str.replaceAll("'","");
			str = str.replaceAll("\\*","");
			str = str.replaceAll("\\?","");
			str = str.replaceAll("\"","");
			str = str.replaceAll(":","");
			str = str.replaceAll("\\\\","");

			return str;
		}else{
			return "";
		}
	}

	/**
	 * 判断字符串是否是数字
	 * @author mwy
	 */
	public static boolean isNumeric(String str){
		if(str==null){
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()){
			return false;
		}
		return true;
	}


	/**
	 * 转换成int
	 * @param obj
	 * @return
	 */
	public static int convert2Int(Object obj) {
		int result = 0;
		if(obj==null)return result;
		if (obj instanceof Integer)
			result = ((Integer) obj).intValue();
		else {

			if (obj.toString().trim().length() > 0) {
				try {
					result = Integer.parseInt(obj.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;

	}

	/**
	 * 转换成float
	 * @param obj
	 * @return
	 */
	public static float convert2Float(Object obj) {
		float result = 0;
		if(obj==null)return result;
		if (obj instanceof Float)
			result = ((Float) obj).floatValue();
		else {

			if (obj.toString().trim().length() > 0) {
				try {
					result = Float.parseFloat(obj.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;

	}

	/**
	 * 将localhost和127.0.0.1转换为服务器上的ip地址
	 * @param url
	 * @author mwy
	 * @return
	 */
	public static String getIP(String url){
		if(url.toLowerCase().indexOf("localhost")>-1||url.toLowerCase().indexOf("127.0.0.1")>-1){
			try {
				InetAddress localhost = InetAddress.getLocalHost();
				url = url.replaceAll("(localhost)|(127.0.0.1)", localhost.getHostAddress());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return url;
	}

	/**首字母转换成大写
	 *
	 * @param str
	 * @return String
	 * @author mwy
	 */
	public static String changeCase(String str) {
		if(str==null || str.length()==0)
		{
			return "";
		}
		int strLen = str.length();
		String tmpChar = str.substring(0,1).toUpperCase();
		String postString = str.substring(1,strLen);

		return tmpChar+postString;
	}


	public static boolean isNull(String str){
		if(str==null)
			return true;
		else if(str.trim().length()==0)
			return true;
		else
			return false;
	}
	/**
	 * 取字部分字符串数据
	 * @param str
	 * @param length
	 * @return
	 */
	public static String subString(String str,int length){
		if(str==null){
			return "";}
		else if(str.trim().length()==0){
			return "";}
		else if(str.length()>length){
			return str.substring(0,length)+"...";
		}else{
			return str;
		}

	}
	/**
	 * Unicode 转中文
	 * @param s
	 * @return
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed      encoding.");
						}

					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					outBuffer.append(aChar);
				}
			} else {
				outBuffer.append(aChar);
			}

		}
		return outBuffer.toString();
	}



	/**  
	 * 去掉字符串中的html源码。<br>    
	 * @param con  内容  
	 * @param length  截取长度   
	 * @param end   原始字符串超过截取长度时，后面增加字符 
	 * @return 去掉后的内容   
	 */
	public static String subStringHTML(String con) {
		String content = ""; 
		if(null!=con && "".equals(con) ){
			content=con.replaceAll("</?[^>]+>","");//剔出了<html>的标签 
			content=content.replace("&nbsp;",""); 
			content=content.replace(".",""); 
			content=content.replace("\"","‘");
			content=content.replace("'","‘");
		}
		return content;
	}


	
	/**  
	 * 去掉字符串中的html源码。<br>    
	 */
	public static String delHTMLTag(String htmlStr){ 
		String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
		String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
		String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 

		Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
		Matcher m_script=p_script.matcher(htmlStr); 
		htmlStr=m_script.replaceAll(""); //过滤script标签 

		Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
		Matcher m_style=p_style.matcher(htmlStr); 
		htmlStr=m_style.replaceAll(""); //过滤style标签 

		Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
		Matcher m_html=p_html.matcher(htmlStr); 
		htmlStr=m_html.replaceAll(""); //过滤html标签 

		return htmlStr.trim(); //返回文本字符串 
	} 
	
	public static String getnuber(){
		String number = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int i = new Random().nextInt(100)+100;
		return number+i;
		
	}
	
}