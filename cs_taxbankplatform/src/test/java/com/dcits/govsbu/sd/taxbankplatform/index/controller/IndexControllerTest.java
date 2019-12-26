package com.dcits.govsbu.sd.taxbankplatform.index.controller;

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

import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.RandomUtil;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath*:/config/spring/spring-applicationContext.xml","classpath*:/config/spring/spring-mvc.xml"})
@WebAppConfiguration
public class IndexControllerTest {

	// 模拟request,response  
    private MockHttpServletRequest request;  
    @Autowired  
    private IndexController indexController; 
    
    // 执行测试方法之前初始化模拟request,response  
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
	public void testRegister() {
		UserEntity userEntity = new UserEntity();
		
		String accountName = RandomUtil.generateString(6) + "@taxbankplatform.com";
		String password = "123456";
		UserInfoEntity userInfo = new UserInfoEntity();
		userEntity.setAccountName(accountName);
		userEntity.setUserName(accountName);
		userEntity.setPassword(password);		
		userEntity.setUserInfo(userInfo);
		
		// request.setParameter("accountName", accountName);
    	// request.setParameter("password", password);
        assertEquals("login", indexController.register(userEntity)) ;  
	}

}
