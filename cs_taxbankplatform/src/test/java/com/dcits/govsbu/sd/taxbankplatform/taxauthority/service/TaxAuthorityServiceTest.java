package com.dcits.govsbu.sd.taxbankplatform.taxauthority.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath*:/config/spring/spring-applicationContext.xml","classpath*:/config/spring/spring-mvc.xml"})
@WebAppConfiguration
public class TaxAuthorityServiceTest {
	 @Autowired  
	 private TaxAuthorityService taxAuthorityService;
	 @Test
		public void testQueryListAll() {
			assertEquals("1", 
					taxAuthorityService.queryListAll(null).get(0).getTaxId().toString()) ;  
		}
}
