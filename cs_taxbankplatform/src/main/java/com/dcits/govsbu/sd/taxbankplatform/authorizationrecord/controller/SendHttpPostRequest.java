package com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class SendHttpPostRequest {
	/**
	 * @param url 访问地址ַ
	 * @param param 使用json格式参数访问
	 * @return
	 */
	public  String sendPost(String url,JSON param){
		return sendPost(url, param.toString());
	}

	/**
	 * @param url 访问地址ַ
	 * @param param 使用json转成字符串格式
	 * @return
	 */
	public  String sendPost(String url,String param){
		PrintWriter out = null;
		BufferedReader in = null;
		String result = ""; 
		try {
			URL relUrl = new URL(url);
			URLConnection conn = relUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
			out.print(param);
			out.flush();

			in =new BufferedReader(
					new InputStreamReader(conn.getInputStream(),"UTF-8"));

			String line ="";
			while((line = in.readLine())!= null){
				result+=line;
			}

			//System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(out != null){
					out.close();
				}
				if(in != null){
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}


	/**
	 * 第二种访问方式
	 * @param url 访问地址ַ
	 * @param param 访问参数
	 * @param sProxyIP 代理的Ip
	 * @param sProxyPort 代理的端口号
	 * @return
	 */
	public  String sendPost2(String url,String param){

		PrintWriter out = null;
		BufferedReader in = null;
		String result = ""; 
		try {
	
			URL relUrl = new URL(url);
			URLConnection conn = relUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT5.1;SV1)");
			conn.setConnectTimeout(120000);
			conn.setReadTimeout(120000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
			out.print(param);
			out.flush(); 

			in =new BufferedReader(
					new InputStreamReader(conn.getInputStream(),"UTF-8"));

			String line ="";
			while((line = in.readLine())!= null){
				result+=line;
			}
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(out != null){
					out.close();
				}
				if(in != null){
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	 /** 
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果 
     */  
	public static String post(String url, String parameter) { 
    	 int statusCode  = 0;
        HttpPost post = null;
        try {
        	CloseableHttpClient httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(50000).setConnectionRequestTimeout(10000)
                    .setSocketTimeout(50000).build();
            // 设置超时时间
              
            post = new HttpPost(url);
            post.setConfig(requestConfig);
            // 构造消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            JSONObject jsonObj = JSONObject.fromObject(parameter);          
            // 构建消息实体
            StringEntity entity = new StringEntity(jsonObj.toString(), Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            post.setEntity(entity);
                
            HttpResponse response = httpClient.execute(post);
            HttpEntity responseentity = response.getEntity();   
            // 检验返回码
            statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != HttpStatus.SC_OK){
            	System.out.println("请求出错: "+statusCode);
              
            }else{
            	return EntityUtils.toString(responseentity);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }finally{
            if(post != null){
                try {
                    post.releaseConnection();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "{\"code\":\""+statusCode+"\"}";
    }

	/*
	 
	重庆市朗然医疗器械有限公司		500903790745647			13708361972			
	重庆欧特亚科技有限公司		    500107753071399			13996062708			
	重庆超海科技有限公司		    500903762673287			13368170984			
	重庆鑫方泰金属制造有限公司		500107676133487			13648300000		
	
	*/	
	/**
     * 调用接口
     * @param destUrl
     * @param params
     * @return
     */
    public static String exeConnection(String destUrl,String params){
		StringBuffer exeResult = new StringBuffer("");
		String tempResult = "";
		try {
			URL serverUrl = new URL(destUrl);
			HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();

			conn.setRequestMethod("POST");//"GET"
			conn.setDoOutput(true);
			conn.setUseCaches(false);								// Post 请求不能使用缓存
			conn.setRequestProperty("Content-type", "text/html");	//可序列化设置 application/x-java-serialized-object
			conn.setRequestProperty("contentType", "utf-8");
			conn.setRequestProperty("Accept-Charset", "utf-8");
			//conn.setConnectTimeout(60*1000);						//设置连接超时时间 30秒钟
			//conn.setReadTimeout(60*1000);							//设置读超时时间 30秒钟
			conn.connect();
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8")); 
		//	params=new String(params.getBytes("GBK"),"utf-8");
			writer.write(params);
			writer.flush();
			writer.close();
			
			//获取接口调用的返回结果

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}else {
					exeResult.append(line);
				}
			}
			tempResult = exeResult.toString();
			
			conn.disconnect();		//关闭链接
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		System.out.println("接口传入参数---"+params);
		System.out.println("接口返回参数---"+tempResult);
		return tempResult;
	}
  
    /**   
     * 发送xml请求到server端   
     * @param url xml请求数据地址   
     * @param xmlString 发送的xml数据流   
     * @return null发送失败，否则返回响应内容   
     */      
    public static String sendXMLPost(String url,String xmlString){      
        //创建httpclient工具对象     
        HttpClient client = new HttpClient();      
        //创建post请求方法     
        PostMethod myPost = new PostMethod(url);      
        //设置请求超时时间     
        client.setConnectionTimeout(3000*1000);    
        String responseString = null;      
        try{      
            //设置请求头部类型     
            myPost.setRequestHeader("Content-Type","text/xml");    
            myPost.setRequestHeader("charset","utf-8");    
            //设置请求体，即xml文本内容，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式     
            myPost.setRequestBody(xmlString);     
            int statusCode = client.executeMethod(myPost);     
            //只有请求成功200了，才做处理  
            if(statusCode == HttpStatus.SC_OK){      
                InputStream inputStream = myPost.getResponseBodyAsStream();  
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));  
                StringBuffer stringBuffer = new StringBuffer();  
                String str = "";  
                while ((str = br.readLine()) != null) {  
                    stringBuffer.append(str);  
                }  
                responseString = stringBuffer.toString();  
            }      
        }catch (Exception e) {   
            e.printStackTrace();      
        }finally{  
             myPost.releaseConnection();   
        }  
        return responseString;      
    }     
	public static void main(String[] args) {
       
		SendHttpPostRequest sp = new  SendHttpPostRequest();
		String url = "http://127.0.0.1:8100/taxbankportal/sypt/soap/queryrzSOAPWebService?wsdl";
		String urls = "http://127.0.0.1:8100/tbp/authorizationrecord/querySQService";
		String param = 
				"{\"SQXH\":\"sq1111111111111111\",\"NSRMC\":\"綦江县速达矿山机械有限责任公司\"," +
				"\"SJHM_FR\":\"13752835183\",\"ND\":\"2017,2016,2015,2014,2013\"," +
				"\"SDJE\":\"500\",\"SDZQ\":\"24\",\"nsrsbh\":\"440300565738707\"" +
				",\"CPID\":\"222\",\"CPMC\":\"好企贷\",\"bh\":\"SQD1111111111\"}";
		//String sProxyIP = "172.16.67.21";
		//String st1 = "3128";;
	//	String content = "<RequestBody><channel>GuiLin</channel><sqdId>123456</sqdId><nsrsbh>440300565738707</nsrsbh><uname>李正元</uname><certType>身份证</certType><certCode>512301196306138494</certCode><businessType>107082</businessType></RequestBody>";
	/*	String reString=sp.sendXMLPost(urls, content);
		*/
          String requestJson= sp.sendPost(urls,param);
	/* Map<String, Object> requestJsonmap =SwsjServiceUtil.Json2Map(requestJson);
		
		String ResponseCode="";
		if(requestJsonmap.get("ResponseCode")!=null){
			ResponseCode=(String) requestJsonmap.get("ResponseCode");
		}
		if(("00").equals(ResponseCode)){//返回成功
			
		}
		
//		JSONObject j1 = JSONObject.parseObject("{\"员工\":{\"s1\":\"姚芳\",\"s2\":\"旺仔\"},\"boss\":\"兵哥\"}");
		JSONObject j1 = JSONObject.fromObject(requestJson);
//		JSONObject j2 = j1.getJSONObject("员工");
		//工商登记表
		JSONArray syptQysdsA3 = j1.getJSONArray("syptQysdsA3");
		for (int i = 0; i < syptQysdsA3.size(); i++) {
			JSONObject j = syptQysdsA3.getJSONObject(i);
			String s1 = j.getString("AQTFFYJNSRQDLJYJDSDSE");
			String s2 = j.getString("ND");
			Map<Object,Object> map1=new HashMap<Object, Object>();
			map1.put("AQTFFYJNSRQDLJYJDSDSE", j.getString("AQTFFYJNSRQDLJYJDSDSE"));
			map1.put("ND", j.getString("ND"));
			map1.put("SQXH", j.getString("SQXH"));
			map1.put("YF", j.getString("YF"));
			map1.put("NSRSBH", j.getString("NSRSBH"));
			System.out.println(s1 + s2);
			
		}
		JSONArray syptQysdsA1 = j1.getJSONArray("syptQysdsA1");
		for (int i = 0; i < syptQysdsA1.size(); i++) {
			JSONObject j = syptQysdsA1.getJSONObject(i);
			String s1 = j.getString("AQTFFYJNSRQDLJYJDSDSE");
			String s2 = j.getString("ND");
			System.out.println(s1 + s2);
			
		}
		JSONArray ss = j1.getJSONArray("ss");
		if(ss.size()!=0&&ss!=null){
			for (int i = 0; i < ss.size(); i++) {
				JSONObject j = ss.getJSONObject(i);
				String s1 = j.getString("AQTFFYJNSRQDLJYJDSDSE");
				String s2 = j.getString("ND");
				System.out.println(s1 + s2);
				
			}
		}else{
			System.out.println("无");
		}
		
//		String s1 = j2.getString("s1");
//		String s2 = j2.getString("s2");
	
		*/
	


	}
	

}
