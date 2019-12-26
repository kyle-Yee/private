package com.dcits.govsbu.sd.taxbankplatform.gxgsService.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



/**
 * 
 * @versions:1.0 
 * @filename：HttpUtils.java
 * @Company:dfwyBank
 * @Created: 2018-1-25下午下午11:46:382:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName HttpUtils
 */
 
public class HttpUtils {

    /**
     * 通过URLConnect的方式发送post请求，并返回响应结果
     * 
     * @param url
     *            请求地址
     * @param params
     *            参数列表，例如name=小明&age=8里面的中文要经过Uri.encode编码
     * @param encoding
     *            编码格式
     * @return 服务器响应结果
     */
    public static String sendPostMethod(String url, String params,
            String encoding) {
        String result = "";
        PrintWriter out = null;
        BufferedWriter writer = null;
        BufferedReader in = null;

        try {
            URL realUrl = new URL(url);
            // 打开url连接
            HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
            // 5秒后超时
            conn.setConnectTimeout(19000);

            // 设置通用的属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/json");

            // post请求必须有下面两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // post请求不应该使用cache
            conn.setUseCaches(false);

            //显式地设置为POST，默认为GET
           // conn.setRequestMethod("POST");
            // 获取Urlconnection对象的输出流，调用conn.getOutputStream的时候就会设置为POST方法
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
            // 发送参数
            out.print(params);
            
            //writer.write(params);
            // flush输出流的缓冲，这样参数才能发送出去
           // writer.flush();
            out.flush();

            // 读取流里的内容，注意编码问题
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), encoding));

            String line = "";
            while (null != (line = in.readLine())) {
                result += line;
            }

        } catch (IOException e) {
            System.out.println("Send post Exection!");
            e.printStackTrace();
        } finally {
            // 关闭流
            try {
                if (null != writer) {
                	writer.close();
                }
                if (null != in) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }


    /**
     * 设置代理
     * 
     * @param ip
     *            代理ip
     * @param port
     *            代理端口号
     */
    public static void setProxy(String ip, String port) {
        // 如果不设置，只要代理IP和代理端口正确,此项不设置也可以
        System.getProperties().setProperty("http.proxyHost", ip);
        System.getProperties().setProperty("http.proxyPort", port);
    }
    
	@SuppressWarnings("unchecked")
	public  static Map<String, Object> postUrl(final String url, Map<String,Object> mapParams) {
		String result = "";
		JSONObject jsonObject = null;
		Map<String,Object> map = null;
		try {
			HttpClient httpClient = HttpClients.createDefault();
			// 这里默认GET方法，HttpClient同时支持POST、PUT等方法
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>(); 
			Iterator<String> iter = mapParams.keySet().iterator();
			while(iter.hasNext()){ 
	            String key=iter.next(); 
	            String value = (String) mapParams.get(key); 
	            nvps.add(new BasicNameValuePair(key, value)); 
	        }
	        httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));  
	        //设置请求和传输超时时间
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000).build();
	        httpPost.setConfig(requestConfig);
	        int i=0;//设置超时后的重连次数
	        HttpResponse response =null;
	        response = httpClient.execute(httpPost);
			int code = response.getStatusLine().getStatusCode();
			System.out.println("======返回的请求码是"+code);
			HttpEntity entity = response.getEntity();
			if(entity != null)
			if(code==200){
				//请求发送成功
				try {
					 result = EntityUtils.toString(entity, "UTF-8");
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 返回json格式：
			    jsonObject = JSONObject.fromObject(result);
			     map =jsonObject;
			    
			}else{
				return null;
			}
		} catch (Exception e) {
			result = "httperror";
		}
		return map;
	}
}
