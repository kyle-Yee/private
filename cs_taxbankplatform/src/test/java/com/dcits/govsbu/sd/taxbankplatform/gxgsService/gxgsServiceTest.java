/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.gxgsService;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.controller.GLLoanBankApproveController;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.common.Constant;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.service.impl.gxgsServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath*:/config/spring/spring-applicationContext.xml","classpath*:/config/spring/spring-mvc.xml"})
@WebAppConfiguration
/**
 * 
 * @versions:1.0 
 * @filename：gxgsServiceTest.java
 * @Company:dfwyBank
 * @Created: 2018-3-7下午下午11:40:152:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName gxgsServiceTest
 */
public class gxgsServiceTest {
    @Autowired  
    private gxgsServiceImpl gxgsService; 
	@Test
	public void queryBaseDataByCpidTest() {
		String bankCpId = "GXJS001";
		assertEquals("ZZ0012018030114581305002", gxgsService.queryBaseDataByCpid(bankCpId).get("orgId")) ;
	}
	@Test
	public void updateAuRecordTest() {
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("la_id", "1234567890");
		map.put("businessID", "CCB15215477836958085");
		assertEquals("1", gxgsService.updateAuRecord(map)) ;
	}
	
	@Test
	public void getFinalTableDataTest(){
		assertEquals("1", gxgsService.getFinalTableData("2017-11-01").get(0).get("PAYBACKTYPE")) ;
	}
	
	@Test
	public void getEndTableDaraTest(){
		assertEquals("1", gxgsService.getEndTableData("2018-03-10").get(0).get("MAXOVERDUEDAYS")) ;
	}
	
	@Test
	public void getEndTableDara1Test(){
		JSONObject dataxx = new JSONObject();
		dataxx.put("statusCode", Constant.BANK_Response_SucessCode);
		dataxx.put("statusMsg", Constant.BANK_Response_SucessCodeMsg);
		JSONArray result = new JSONArray();
		JSONObject resultjson = new JSONObject();
		resultjson.put("businessid", "12345678888889");
		resultjson.put("entname", "未来方舟");
		resultjson.put("entcreditid", "522121199012213056");
		resultjson.put("enttaxid", "12656746877666");
		resultjson.put("approvedate", "2018-04-20");
		result.add(resultjson);
		dataxx.put("result", result);
		List<Map<String, Object>> dhglsjList = gxgsService.getEndTableData("2018-03-10");
		dataxx.put("dhglsj", dhglsjList);
		String resultString = dataxx.toJSONString();
		
		System.out.println("================"+resultString);
		
		//assertEquals("1", gxgsService.getEndTableData("2018-03-10").get(0).get("MAXOVERDUEDAYS")) ;
	}
	
	@Test
	public void createFinalTableDataTest(){
		GLLoanBankApproveController gLLoanBankApproveController = new GLLoanBankApproveController();
		try {
			gLLoanBankApproveController.automaticFinalrec();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
