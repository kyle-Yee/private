package com.dcits.govsbu.sd.taxbankplatform.sendmessage.templet;
import java.util.Random;

public class RandomUtil {

	public static String randomFor6() {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}
		return result;
	}
	
	public static String randomFor8() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			stringBuilder.append(new Random().nextInt(10));
		}
		return stringBuilder.toString();
	}
	
}
