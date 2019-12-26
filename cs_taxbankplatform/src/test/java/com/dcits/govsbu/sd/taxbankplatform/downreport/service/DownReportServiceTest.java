package com.dcits.govsbu.sd.taxbankplatform.downreport.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dcits.govsbu.sd.taxbankplatform.downreport.model.DownloadsReportEntity;
import com.dcits.govsbu.sd.taxbankplatform.downreport.model.DownloadsReportRecEntity;
import com.dcits.govsbu.sd.taxbankplatform.downreport.service.impl.DownloadsReportRecServiceImpl;
import com.dcits.govsbu.sd.taxbankplatform.downreport.service.impl.DownloadsReportServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath*:/config/spring/spring-applicationContext.xml","classpath*:/config/spring/spring-mvc.xml"})
@WebAppConfiguration
public class DownReportServiceTest {
	@Autowired  
	private DownloadsReportRecServiceImpl downloadsReportRecService;
	@Autowired
	private DownloadsReportServiceImpl downloadsReportService;
	@Test
	 public void findByIdTest(){
		assertEquals("工商银行",downloadsReportService.findById("1").getDownName());
	}
	
	@Test
	public void insertTest(){
		DownloadsReportEntity  downloadsReportEntity = new DownloadsReportEntity();
		downloadsReportEntity.setDownName("test");
		downloadsReportEntity.setDownStatus("1");
		downloadsReportEntity.setEnterpriseName("test");
		downloadsReportEntity.setTotaldowns(new Long(1));
		assertEquals("1",downloadsReportService.insert(downloadsReportEntity)+"");
	}
	
	@Test
	 public void findByIdRecTest(){
		assertEquals("工商银行",downloadsReportService.findById("1").getDownName());
	}
	
	@Test
	public void insertRecTest(){
		DownloadsReportRecEntity  downloadsReportRecEntity = new DownloadsReportRecEntity();
		downloadsReportRecEntity.setDownName("北京工商银行");
		downloadsReportRecEntity.setDownStatus("1");
		downloadsReportRecEntity.setEnterpriseName("深圳地税局");
		assertEquals("1",downloadsReportRecService.insert(downloadsReportRecEntity)+"");
	}
	
}
