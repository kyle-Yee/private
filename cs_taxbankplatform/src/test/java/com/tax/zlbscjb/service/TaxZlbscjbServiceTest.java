package com.tax.zlbscjb.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.dcits.govsbu.sd.taxbankplatform.ajxx.service.TaxAjxxService;
import com.dcits.govsbu.sd.taxbankplatform.bmyxcx.model.TaxBmyxcxEntity;
import com.dcits.govsbu.sd.taxbankplatform.bmyxcx.service.TaxBmyxcxService;
import com.dcits.govsbu.sd.taxbankplatform.invokeRecord.model.InvokeRecordEntity;
import com.dcits.govsbu.sd.taxbankplatform.invokeRecord.service.InvokeRecodeService;
import com.dcits.govsbu.sd.taxbankplatform.invokeRecord.service.impl.InvokeRecodeServiceImpl;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveQueryService;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveService;
import com.dcits.govsbu.sd.taxbankplatform.lrbqykjzd.service.impl.TaxLrbQykjzdServiceImpl;
import com.dcits.govsbu.sd.taxbankplatform.lrbxqykj.mapper.TaxLrbXqykjEntityMapper;
import com.dcits.govsbu.sd.taxbankplatform.lrbxqykj.service.impl.TaxLrbXqykjServiceImpl;
import com.dcits.govsbu.sd.taxbankplatform.lrbybqykj.service.impl.TaxLrbYbqykjServiceImpl;
import com.dcits.govsbu.sd.taxbankplatform.pdf.service.CqPdfService;
import com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.model.SystemEntity;
import com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.service.SystemService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfBggsService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfNsqdService;
import com.dcits.govsbu.sd.taxbankplatform.zcfzbybqykj.service.TaxZcfzbYbqykjService;
import com.dcits.govsbu.sd.taxbankplatform.zlbscjb.service.impl.TaxZlbscjbServiceImpl;
import com.google.gson.Gson;




@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath*:/config/spring/spring-applicationContext.xml","classpath*:/config/spring/spring-mvc.xml"})
@WebAppConfiguration
public class TaxZlbscjbServiceTest {
	@Autowired  
	private TaxZlbscjbServiceImpl taxZlbscjbServiceImpl;
	@Autowired 
	private TaxZcfzbYbqykjService taxZcfzbYbqykjService;
	@Autowired 
	private TaxBmyxcxService taxBmyxcxService;
	@Autowired
	private TaxAjxxService taxAjxxService;
	
	@Autowired
	private TaxPdfBggsService taxPdfBggsService;
	@Test
	 public void findByIdTest(){
		assertEquals("工商银行",taxZlbscjbServiceImpl.findById("1").getCreatorid()+"");
	}
	
	@Test
	 public void findByIdTest1(){
		assertEquals("工商银行",taxZcfzbYbqykjService.findById("1").getCreatorid().toString());
	}
	@Autowired  
	private TaxLrbQykjzdServiceImpl taxLrbQykjzdServiceImpl;
	
	@Test
	 public void findByIdTest2(){
		assertEquals("工商银行",taxLrbQykjzdServiceImpl.findById("1").getCreatorid().toString());
	}
	
	@Autowired  
	private TaxLrbXqykjServiceImpl taxLrbXqykjServiceImpl;
	@Test
	 public void findByIdTest3(){
		assertEquals("工商银行",taxLrbXqykjServiceImpl.findById("1").getCreatorid().toString());
	}
	
	@Autowired  
	private TaxLrbYbqykjServiceImpl taxLrbYbqykjServiceImpl;
	@Test
	 public void findByIdTest4(){
		assertEquals("工商银行",taxLrbYbqykjServiceImpl.findById("1").getCreatorid().toString());
	}
	@Test
	 public void insertTest4(){
		TaxBmyxcxEntity record = new TaxBmyxcxEntity();
		
		record.setDjxh("1234567");
		record.setCreatorid("1");
		record.setCreatetime(new Date());
		record.setUpdatetime(new Date());
		record.setUpdatorid("233");
		assertEquals("1",taxBmyxcxService.insert(record)+"");
	}
	@Test
	 public void adderWithParameterMapTest(){
		Map<String, Object> parms = new HashMap<String, Object>(); 
		Gson gson = new Gson();
        parms.put("addend1", 3);  
        parms.put("addend2", 4);
        String str = gson.toJson(taxAjxxService.adderWithParameterMap(parms).get(0).getTestEntity());
        System.out.println(str);
		assertEquals("1",taxAjxxService.adderWithParameterMap(parms).get(0).getTestEntity().getMt1()+"");
	}
	
	@Autowired  TaxPdfNsqdService taxPdfNsqdService;
	@Test
	 public void findBydjxhTest(){
		assertEquals("1",taxPdfNsqdService.findJtnsqd("10109").getJtnsqdEntity().get(0).getTpjNf()+"");
	}
	@Test
	 public void findZnsedTest(){
		assertEquals("1",taxPdfBggsService.findZnsed("")+"");
	}
	//测试存储过程
//	@Test
//	 public void zzsnsqkPrcdure(){
//		assertEquals("1",taxPdfNsqdService.zzsnsqkPrcdure()+"");
//	}
//	
//	@Test
//	 public void sdsyjnsqkPrcdure(){
//		assertEquals("1",taxPdfNsqdService.sdsyjnsqkPrcdure()+"");
//	}
//	
//	@Test
//	 public void nsqdPrcdure(){
//		assertEquals("1",taxPdfNsqdService.nsqdPrcdure()+"");
//	}
//	@Test
//	 public void jtszPrcdure(){
//		assertEquals("1",taxPdfNsqdService.jtszPrcdure()+"");
//	}
//	@Test
//	 public void sdsyyefxPrcdure(){
//		assertEquals("1",taxPdfNsqdService.sdsyyefxPrcdure()+"");
//	}
//	@Test
//	 public void zzsxsefxPrcdure(){
//		assertEquals("1",taxPdfNsqdService.zzsxsefxPrcdure()+"");
//	}
//	@Test
//	 public void znsedPrcdure(){
//		assertEquals("1",taxPdfNsqdService.znsedPrcdure()+"");
//	}
	@Autowired 
	private TaxLrbXqykjEntityMapper taxLrbXqykjEntityMapper;
	@Test
	 public void zzsxsefxPrcdure(){
		assertEquals("1",taxLrbXqykjEntityMapper.deleteByDjxh("1")+"");
	}
	@Autowired
	private SystemService systemService;
	@Test
	 public void systemTest(){
		assertEquals("10001",systemService.queryFlagByXtcs("10001").getXtcs());
	}
	@Test
	 public void systemInsertTest(){
		SystemEntity  systemEntity = new SystemEntity();
		systemEntity.setCreatedname("张三");
		systemEntity.setXtcs("10006");
		systemEntity.setXtmc("税银平台");
		systemEntity.setEnabled("Y");
		assertEquals("10001",systemService.insert(systemEntity)+"");
		
	}
	
	@Autowired
	private LoanApproveService loanApproveService;
	@Test
	 public void findDownDateByIdTest(){
		Map<String, Object> parameters =new HashMap<String, Object>();
		parameters.put("id", 1);
		parameters.put("creatorid",1);
		assertEquals("1",loanApproveService.findDownDateById(parameters).getRwid()+"");
	}
/*	@Autowired
	private LoanApproveService loanApproveService;
*/	@Test
	 public void findSXSJxByIdTest(){
		assertEquals("1",loanApproveService.findSXSJxById("1").getSxsjxMapList().get(0).getName()+"");
	}
	/*@Autowired
	private TaxPdfBggsService taxPdfBggsService;*/
	@Test
	 public void findUserIdByIdTest(){
		assertEquals("1",taxPdfBggsService.findUserIdById("1"));
	}
	@Test
	 public void findHNSqxhTest(){
		assertEquals("1",taxPdfBggsService.findHNSqxh());
	}
	@Autowired
	InvokeRecodeService inService;
	
	@Test
	public void insertInvoke(){
		InvokeRecordEntity in = new InvokeRecordEntity();
		in.setInvokeId("HN2017032818540843001");
		in.setCreateTime(new Date());
		in.setInvokeTimes(0);
		in.setServiceId("SBXX.QUERY.SY.JS");
		in.setUserId("21");
		in.setInterfaceReturnInfo("请求的授权编码73885503200不存在或者已失效");
		in.setLpbm("73885503200");
		in.setYxqq("2016-11-01");
		in.setYxqz("2016-11-30");
		assertEquals("1",inService.insertRecode(in)+"");
	}
	
	@Autowired
	CqPdfService cqPdfService;
	@Test
	 public void findSqxhByNsrsbhTest(){
		assertEquals("XH16900001548060",cqPdfService.findSqxhByNsrsbh("91500108073695173E"));
	}
	@Test
	public void findCqQyjcxxBySqxhTest(){
		assertEquals("XH16900001548060",cqPdfService.findCqQyjcxxBySqxh("XH16900001543157").getCwfzrsfzjhm());
	}
	
	@Test
	public void findCqNsqdTest(){
		Map<String , Object> params = new HashMap<String , Object>();
		params.put("sqxh", "XH16900001543157");
		params.put("starttime", "2015");
		params.put("endtime", "2015");
		params.put("yfstarttime", "01");
		params.put("yfendtime", "12");
		assertEquals("XH16900001548060",cqPdfService.findCqNsqd(params).getZzssjnsed()+"");
	}
	
	@Test
	public void findCqSwpjxxTest(){
		Map<String , Object> params = new HashMap<String , Object>();
		params.put("sqxh", "XH17900001822019");
		params.put("starttime", "2015");
		params.put("endtime", "2015");
		assertEquals("B",cqPdfService.findCqSwpjxx(params).get(0).getSwxypj());
	}
	
	@Test
	public void findCqSsyhzcTest(){
		Map<String , Object> params = new HashMap<String , Object>();
		params.put("sqxh", "XH16900001536016");
		params.put("starttime", "2015-01-01");
		params.put("endtime", "2015-12-31");
		assertEquals("2",cqPdfService.findCqSsyhzc(params).get(0).getJmlxdm());
	}
	@Test
	public void findCqWfwzmxTest(){
		Map<String , Object> params = new HashMap<String , Object>();
		params.put("sqxh", "XH16900001543016");
		params.put("starttime", "2015-01-01");
		params.put("endtime", "2015-12-31");
		assertEquals("019",cqPdfService.findCqWfwzmx(params).get(0).getWfxwclztdm());
	}
	@Test
	public void findCqLjqsxxTest(){
		assertEquals("0.000000",cqPdfService.findCqLjqsxx("XH15900001429005"));
	}
	@Autowired
	LoanApproveQueryService loanApproveQueryService;
	@Test
	public void exportExcelTest(){
		Map<String ,Object> params = new HashMap<String,Object>();
		params.put("orgId", 11);
		params.put("status", 3);
		assertEquals("8",loanApproveQueryService.exportExcel(params).get(0).getLaAmount());
	}
}
