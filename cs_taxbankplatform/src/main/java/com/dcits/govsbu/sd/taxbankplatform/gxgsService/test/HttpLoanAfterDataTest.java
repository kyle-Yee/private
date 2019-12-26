/**
 * <p>Description: </p>
 * <p>versions:1.0 
 * <p>file name：HttpLoanAfterDataTest.java
 * <p>Company:dfwyBank</p>
 * <p>@author: Zhongyj 
 * <p>Created: 2018-3-14下午上午10:10:582:57:43 
 * <p>department:深圳IT部门  
 * <p>Copyright Copyright (c) dfwy. All rights reserved.</p>
 */
package com.dcits.govsbu.sd.taxbankplatform.gxgsService.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.common.HttpUtils;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.common.util.JsonUtil;

/**
 * @versions:1.0
 * @filename：HttpLoanAfterDataTest.java
 * @Company:dfwyBank
 * @Created: 2018-3-14下午上午10:10:582:57:43
 * @department:深圳IT部门
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName HttpLoanAfterDataTest
 */
public class HttpLoanAfterDataTest {
	/**
	 * @Author Zhongyj
	 * @date 2018-3-3 上午10:43:16
	 * @param args
	 */
	public static void main(String[] args) {

		String url = "http://127.0.0.1:8080/tbp/loanAfterData";
		Map<String, String> resultMap = new HashMap<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map1 = new HashMap<String, String>();
		// System.out.println(str);
		map1.put("businessID", "JH0012017092623324717604");
		map1.put("indName", "成昆");
		map1.put("indCertType", "10");
		map1.put("indCertID", "566343348747735036");
		map1.put("entName", "未来科技");
		map1.put("entCreditID", "91666300771712161X");
		map1.put("entTaxID", "666300771712161");
		map1.put("approveDate", "2013-10-28");
		map1.put("admissionResult", "0");
		map1.put("contractAmt", "50");
		map1.put("contractTerm", "12");
		map1.put("contractRate", "2");
		map1.put("paybackType", "01");

		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("businessID", "JH0012017092623324717604");
		map2.put("indName", "成昆");
		map2.put("indCertType", "10");
		map2.put("indCertID", "566343348747735036");
		map2.put("entName", "未来科技");
		map2.put("entCreditID", "91666300771712161X");
		map2.put("entTaxID", "666300771712161");
		map2.put("approveDate", "2013-10-28");
		map2.put("admissionResult", "0");
		map2.put("contractAmt", "50");
		map2.put("contractTerm", "12");
		map2.put("contractRate", "2");
		map2.put("paybackType", "01");

		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("businessID", "JH0012017092623324717604");
		map3.put("indName", "成昆");
		map3.put("indCertType", "10");
		map3.put("indCertID", "566343348747735036");
		map3.put("entName", "未来科技");
		map3.put("entCreditID", "91666300771712161X");
		map3.put("entTaxID", "666300771712161");
		map3.put("serialNo", "23456789009876543");
		map3.put("endType", "00");
		map3.put("endDate", "2013-10-28");
		map3.put("endBalance", "0");
		map3.put("overdueCount", "1");
		map3.put("maxOverdueDays", "5");
		map3.put("endClass", "2");

		resultMap.put("loanApply", JSON.toJSONString(map1));
		resultMap.put("creditResults", JSON.toJSONString(map2));
		resultMap.put("endLoan", JSON.toJSONString(map3));
		String str = JSON.toJSONString(resultMap);
		System.out.println("+++++++++++++++str++++++++++++++"+str);
		Map<String, Object> strMap = JSON.parseObject(str);
		String strMap1 = (String) strMap.get("loanApply");
		System.out.println("=============strMap1============="+strMap1);
		String encoding = "UTF-8";
		String rusult = HttpUtils.sendPostMethod(url, str, encoding);
		System.out.println(rusult);
	}

}
