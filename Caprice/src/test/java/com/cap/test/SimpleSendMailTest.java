package com.cap.test;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.cap.util.SimpleSendMail;


public class SimpleSendMailTest {

	@Test
	public void testSendStringStringStringArray() {
		SimpleSendMail ssm = new SimpleSendMail();
		String code = createData(6);
		boolean result = ssm.send("验证码", code, "834284006@qq.com");
		Assert.assertEquals(result,true);
	}
	
	public static String createData(int length) {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(rand.nextInt(10));
		}
		return sb.toString();
	}
}
