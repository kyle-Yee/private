package com.dcits.govsbu.sd.taxbankplatform.organization.service;
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
public class OrganizationsServiceTest {
	@Autowired  
	 private OrganizationsService organizationsService;
	@Test
	 public void findTaxAuthorityListByIdTest(){
		assertEquals("深圳国税局",organizationsService.findTaxAuthorityListById("57").get(0).getTaxName());
	}
}
