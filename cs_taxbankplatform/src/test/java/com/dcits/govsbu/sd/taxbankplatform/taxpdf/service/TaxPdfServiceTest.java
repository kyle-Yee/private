package com.dcits.govsbu.sd.taxbankplatform.taxpdf.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath*:/config/spring/spring-applicationContext.xml","classpath*:/config/spring/spring-mvc.xml"})
@WebAppConfiguration
public class TaxPdfServiceTest {
	/*@Autowired
	private TaxPdfDksqService taxPdfServiceImpl;*/
	@Autowired
	private TaxPdfSwqtService taxPdfSwqtService;
	@Autowired
	private TaxPdfBggsService taxPdfBggsService;
	@Autowired
	private TaxPdfCbxxService taxPdfCbxxService;
	@Autowired
	private TaxPdfGsdjService taxPdfGsdjService;
	@Autowired
	private TaxPdfNsqdService taxPdfNsqdService;
	@Autowired
	private TaxPdfQyjgService taxPdfQyjgService;
	@Autowired
	private TaxPdfSwdjService taxPdfSwdjService;
	/*@Test
	 public void findByIdTest(){
		assertEquals("200",taxPdfServiceImpl.findById(new Long(1)).getLa_amount()+"");
	}*/
	@Test
	 public void findByDjxhSwqtTest(){
		Map<String,Object> params = new HashMap<String,Object>();
		assertEquals("200",taxPdfSwqtService.findByDjxh(params).getCreatorid().toString());
	}
	
	@Test
	 public void findByDjxhSwdjTest(){
		Map<String,Object> params = new HashMap<String,Object>();
		assertEquals("200",taxPdfSwdjService.findByDjxh(params).getCreatorid().toString());
	}
	@Test
	 public void findByDjxhQyjgTest(){
		Map<String,Object> params = new HashMap<String,Object>();
		assertEquals("200",taxPdfQyjgService.findByDjxh(params).getCreatorid().toString());
	}
	/*@Test
	 public void findByDjxhNsqdTest(){
		assertEquals("200",taxPdfNsqdService.findByDjxh(new Long(1)));
	}*/
	@Test
	 public void findByDjxhGsdjTest(){
		Map<String,Object> params = new HashMap<String,Object>();
		assertEquals("华谊兄弟",taxPdfGsdjService.findByDjxh(params).get(0).getTaxGsdjEntity().getNsrztmc());
	}
	@Test
	 public void findByDjxhCbxxTest(){
		Map<String,Object> params = new HashMap<String,Object>();
		assertEquals("200",taxPdfCbxxService.findByDjxh(params).getCreatorid().toString());
	}
	@Test
	 public void findByDjxhBggsTest(){
		String djxh = "1";
		Map<String,Object> params = new HashMap<String,Object>();
		assertEquals("华谊兄弟",taxPdfBggsService.findByDjxh(params).get(0).getId().toString());
	}
	
	
}
