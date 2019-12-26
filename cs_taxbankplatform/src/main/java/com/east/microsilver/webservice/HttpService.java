package com.east.microsilver.webservice;

import java.io.BufferedReader;
import java.io.IOException;  
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Body;
import com.google.gson.Gson;
import com.east.microsilver.common.request.Parmeter;
import com.east.microsilver.common.EncryptTest;


  
public class HttpService extends HttpServlet {  

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        
        String reqBody = sb.toString();
		System.out.println(reqBody);
		
		//对获取到的数据reqBody 进行解密
		Gson gson = new Gson();
		Parmeter parmeter = gson.fromJson(reqBody, Parmeter.class);
		String appid = parmeter.getAppid();		//appid由深圳农商行分配
		String sign = parmeter.getSign();		//data存放报文数据的AES加密字符串
		String data = parmeter.getData();		//sign存放签名数据，签名数据使用字符串格式。
		long timestamp = Long.parseLong(parmeter.getTimestamp());	//timestamp为时间戳(unixtime),有效期5分钟,超过时间后请求无效
		boolean verfiy;
		try {
			verfiy = EncryptTest.verify(timestamp, data, sign);
			System.out.println(verfiy);
			if (!verfiy) {
				return;
			}
			String src = EncryptTest.decrypt(data);
			System.out.println("src:" + src);	//src为解密后的报文数据
			
			Body bodys = gson.fromJson(src, Body.class);	//测试:随便取报文数据里面的一个值
			//System.out.println(bodys.getBankfinal().getNSRSBH());	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","GET,POST,PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials","true");
        out.write("{\"success\":\"true\", \"\":\"\"}");
		out.flush();
		out.close();
    }
}  