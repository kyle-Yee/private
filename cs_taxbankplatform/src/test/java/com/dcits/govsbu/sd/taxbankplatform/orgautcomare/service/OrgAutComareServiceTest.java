package com.dcits.govsbu.sd.taxbankplatform.orgautcomare.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dcits.govsbu.sd.taxbankplatform.orgautcomare.model.OrgAutComareEntity;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath*:/config/spring/spring-applicationContext.xml","classpath*:/config/spring/spring-mvc.xml"})
@WebAppConfiguration
public class OrgAutComareServiceTest {
	@Autowired  
	 private OrgAutComareService orgAutComareService;
	 @Test
	 public void testInsert(){
		 OrgAutComareEntity record = new OrgAutComareEntity();
		 record.setOrgId("1");
		 //record.setTaxId(1);
		
		 assertEquals("1",orgAutComareService.insert(record)+"");
	 }
	 @Test
	 public void testQueryAuthorityByOrgId(){
		 Map<String, Object> parameter = new HashMap<String,Object>();
		 parameter.put("orgId", 58);
		 parameter.put("taxId", 6);
		 assertEquals("58",orgAutComareService.queryAuthorityByOrgId(parameter).getOrgId()+"");
	 }
	 @Test
	 public void testDeleteDataByOrgIdAndTaxId (){
		 Map<String, Object> parameter = new HashMap<String,Object>();
		 parameter.put("orgId", 58);
		 parameter.put("taxId", 7);
		 assertEquals("1",orgAutComareService.deleteDataByOrgIdAndTaxId(parameter)+"");
	 }
}
