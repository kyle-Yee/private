package com.dcits.govsbu.sd.taxbankplatform.user.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.ExtendedModelMap;

import com.dcits.govsbu.sd.taxbankplatform.util.Common;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath*:/config/spring/spring-applicationContext.xml","classpath*:/config/spring/spring-mvc.xml"})
@WebAppConfiguration
public class UserControllerTest {

	// 模拟request,response  
    private MockHttpServletRequest request;  
    @Autowired  
    private UserController userController;
    
	@Before
	public void setUp() throws Exception {
		request = new MockHttpServletRequest();      
        request.setCharacterEncoding("UTF-8");      
        new MockHttpServletResponse(); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testListUI() {
		assertEquals(Common.BACKGROUND_PATH + "/user/list", 
				userController.listUI(new ExtendedModelMap(), request)) ;  
	}

	@Test
	public void testList() throws Exception {

//		// 使用了Shiro，没有访问权限 Common.getloginUserId()
//		Map<String, Object> parameters = (Map<String, Object>) userController.list(gridPager);
//		for(Map.Entry<String, Object> entry : parameters.entrySet()){
//			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//		}
	}
	
	@Test
	public void testAddUI() {
		
	}

	@Test
	public void testAdd() {
		
	}

	@Test
	public void testEditUI() {
		
	}

	@Test
	public void testUpdate() {
		
	}

	@Test
	public void testDeleteBatch() {
		
	}

	@Test
	public void testResetPassword() {
		
	}

	@Test
	public void testResetPassWithoutAuthc() {
		
	}

	@Test
	public void testInfoUI() {
		
	}

	@Test
	public void testInfo() {
		
	}

	@Test
	public void testPasswordUI() {
		
	}

	@Test
	public void testPassword() {
		
	}

	@Test
	public void testValidateAccount() {
		
	}

	@Test
	public void testObject() {
		
	}

	@Test
	public void testGetClass() {
		
	}

	@Test
	public void testHashCode() {
		
	}

	@Test
	public void testEquals() {
		
	}

	@Test
	public void testClone() {
		
	}

	@Test
	public void testToString() {
		
	}

	@Test
	public void testNotify() {
		
	}

	@Test
	public void testNotifyAll() {
		
	}

	@Test
	public void testWaitLong() {
		
	}

	@Test
	public void testWaitLongInt() {
		
	}

	@Test
	public void testWait() {
		
	}

	@Test
	public void testFinalize() {
		
	}

}
