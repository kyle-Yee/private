package com.dcits.govsbu.sd.taxbankplatform.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.web.WebAppConfiguration;


 
@ContextConfiguration({"classpath*:/config/spring/spring-applicationContext.xml","classpath*:/config/spring/spring-mvc.xml"})
@WebAppConfiguration
public class IDGenerate {

	private static final AtomicInteger integer = new AtomicInteger(1);
	private static final String ZDGL = "0";


	/**
	 * 获取当前时间  +  
	 * @return
	 */
	public static String getId() {
		long time = System.currentTimeMillis();
		
		System.out.println(time);
		
		StringBuilder str = new StringBuilder(30);
		str.append("XH");
		str.append(time);
		int intValue = integer.getAndIncrement();
		if (integer.get() >= 1000000) {
			integer.set(0);
		}
		if (intValue < 10) {
			str.append("00000");
		} else if (intValue < 100) {
			str.append("0000");
		} else if (intValue < 1000) {
			str.append("000");
		} else if (intValue < 10000) {
			str.append("00");
		} else if (intValue < 100000) {
			str.append("0");
		}
		

		str.append(intValue);

		return str.toString();

	}
	
	/**
	 * 重庆用获取国税数据用
	 * 生成唯一编号
	 * @return
	 */
	public static synchronized String generateMessageID(){
		StringBuffer sb = new StringBuffer(30);
		try {
			
			sb.append("XH");
			sb.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
			int intValue = integer.getAndIncrement();
			if (integer.get() >= 100) {
				integer.set(0);
			}
			if (intValue < 10) {
				sb.append(ZDGL);
			} 
			sb.append(intValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 湖南用获取税务数据id
	 * 生成编号
	 * 该方法满足暂时用需求，如果需要生成唯一编号
	 * @return
	 */
	public static synchronized String generateMessageHNID(){
		StringBuffer sb = new StringBuffer(30);
		try {
			
			sb.append("HN");
			sb.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
			int intValue = integer.getAndIncrement();
			if (integer.get() >= 100) {
				integer.set(0);
			}
			if (intValue < 10) {
				sb.append(ZDGL);
			} 
			sb.append(intValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 生成唯一主键ID作为主键
	 * @param bm  传入规定的表名编号
	 * @return
	 */
	public static synchronized String getZJID(String bm){
		StringBuffer sb = new StringBuffer(50);
		try {
			Thread.sleep(100);
			sb.append(bm);
			sb.append(MessageUtil.getDqyhDM());
			sb.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
			int intValue = integer.getAndIncrement();
			if (integer.get() >= 100) {
				integer.set(0);
			}
			if (intValue < 10) {
				sb.append(ZDGL);
			} 
			sb.append(intValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
/*	*//**
	 * 运行测试
	 * @param args
	 *//*
	@Test
	public void sendtax() {
		for (int i = 0; i < 1000; i++) {
			new Test2().start();
		}
	}*/
	
	/*public static void main(String[] args) {
		for (int i = 0; i < 1000003; i++) {
			new Test2().start();
		}
	}*/
	
	/**
	 * 线程测试
	 * @author admin
	 *
	 */
	private static class Test1 extends Thread {
		@Override
		public void run() {
			System.out.println(IDGenerate.getId());
		}
	}
	
	
	private static class Test2 extends Thread {
		@Override
		public void run() {
			System.out.println(IDGenerate.generateMessageID());
		}
	}
	
}
