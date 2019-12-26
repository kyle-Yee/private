package com.webservice.test;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dcits.govsbu.sd.taxbankplatform.datastorage.service.TaxDataSaveService;
import com.dcits.govsbu.sd.taxbankplatform.invokeRecord.service.InvokeRecodeService;
import com.google.gson.Gson;
import com.east.microsilver.common.Commons;
import com.east.microsilver.common.request.Parameter;
import com.east.microsilver.common.request.RequestConfig;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath*:/config/spring/spring-applicationContext.xml","classpath*:/config/spring/spring-mvc.xml"})
@WebAppConfiguration
public class MainTest {
	
	@Autowired
	private TaxDataSaveService taxDataSaveService;
	@Autowired
	private InvokeRecodeService invokeRecodeService;
	@Test
	 public void mainTest(){
		
		RequestConfig requestConfig = new RequestConfig();
		requestConfig.setServiceId("LPBMCX.QUERY.SY.JS");  //服务id
        //requestConfig.setServiceId("YJSF.QUERY.SY.JS");
        requestConfig.setChannelId("dfwyhn");
        requestConfig.setPassword(generateRandomPassword());  //交易密码8位随机数
        
        Parameter parms = new Parameter();
        //测试参数
        parms.setNsrdzdah("10114301000168480963");
        parms.setLpbm("61103693246");
        parms.setSsqq("2012-07-01");
        parms.setSsqz("2012-07-30");
        
        //正式参数
        //parms.setNsrdzdah("10114301010000100196");
        //parms.setLpbm("72653872738");
        //parms.setSsqq("2015-01-01");
        //parms.setSsqz("2015-12-31");
        
        Gson gson = new Gson();
        String v_content = gson.toJson(parms);
		//请求业务参数
        requestConfig.setContent(v_content);
        List<Map<String, String>> dataList = Commons.achieveData(requestConfig, "1","HN1223456789",invokeRecodeService);        
        taxDataSaveService.insertLpbmcx(dataList);
	}
	
	
	/**
	 * 功能:生成8位随机密码
	 * @return
	 */
	public static String generateRandomPassword() {
		//定义变长字符串
		StringBuilder stringBuilder=new StringBuilder();
		//随机生成数字，并添加到字符串
		for(int i = 0; i < 8; i++){
			stringBuilder.append(new Random().nextInt(10));
		}
		return stringBuilder.toString();
	}
}
