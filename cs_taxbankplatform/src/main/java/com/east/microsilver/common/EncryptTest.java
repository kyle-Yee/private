package com.east.microsilver.common;


import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class EncryptTest {

	private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAOHorBtMC6oNQ2Lz"
			+ "5/kdZdnaQRYm3/UQI/J+8U8U1N9oGuS9/iA1HJceXsOoZ7/ayFSvcvKqPQpSlEyx"
			+ "9BqR0G+Fh9g8CYOpk4impvn49apqdsgKXKNVAiks2IGkbqNR3rfr+TsID09SdqhA"
			+ "fFlxgnzlAlSKFzThs6xIa4Y1aPoXAgMBAAECgYAPpIbWVQuxuL1jyrPTYh+9WAmN"
			+ "uR9k3rs+fV/ENBMRbSn/kNBZ9pOGO0ogjjaw6Dta8dMpznPvlQxDy0a12EbwcKT8"
			+ "PdN/XjONK4dQzDZR2tA/vStcZqkakBPUC2d8LB6VFS429wGJUplFSZdXNMr/v678"
			+ "NEETUWhkkvFm8r+0sQJBAPVUNnyTC/Fej1/y8ONE7+oL5YGDLKS6YtS3ezizyp0K"
			+ "S4EVFVQONmtMTlFVOb8wi8fb8p3PNH80Dgf7G7W7EZ0CQQDrvDaEshWxDA2/2ZZ7"
			+ "K3p3RdjdSxpIn0IL8zYThviLmydE6KbuiD49jlIZw+fXM8l5SV1XDeu15Hwaax6P"
			+ "GXZDAkEAu4UpOMN1R4PN97tM4YsaDz4UqZBImYlWbd3ptAfJgYwS9pHVAbDza8Sh"
			+ "wI1yXG+RCK1Yn8N4DxsM9pC8N4bMCQJAA81QT8mTgfyAyERZIflsiznsKn2Imf0/"
			+ "knPEvoHhxlalRn4+GwDFiDagalv3hEgIitU9FSdbUt6xhbeOkZ5GgwJASbkHpg1r"
			+ "RiJf5vl2c5BB2sBkNp5rBdCpUoLkD9A9zl918jahxRQiQrKbtoZpX0YuK22g5lcv"
			+ "fnsGMUZkZD74RQ==";
	private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDh6KwbTAuqDUNi8+f5HWXZ2kEW"
			+ "Jt/1ECPyfvFPFNTfaBrkvf4gNRyXHl7DqGe/2shUr3Lyqj0KUpRMsfQakdBvhYfY"
			+ "PAmDqZOIpqb5+PWqanbIClyjVQIpLNiBpG6jUd636/k7CA9PUnaoQHxZcYJ85QJU"
			+ "ihc04bOsSGuGNWj6FwIDAQAB";

	private static byte[] aesKey = "1234567812345678".getBytes();

	public static String encrypt(String source) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(aesKey, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(source.getBytes("utf8"));
		String data = Base64.encodeBase64String(encrypted);
		return data;
	}

	public static String sign(long timestamp, String data) throws Exception {
		byte[] buffer = Base64.decodeBase64(privateKey.getBytes()); // PKCS8
		KeyFactory keyFac = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
		RSAPrivateKey rsaPri = (RSAPrivateKey) keyFac.generatePrivate(keySpec);
		Signature signature = Signature.getInstance("SHA256WithRSA");
		signature.initSign(rsaPri);
		signature.update((data + timestamp).getBytes());
		String sign = Base64.encodeBase64String(signature.sign());
		return sign;
	}

	public static String decrypt(String encData) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(aesKey, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] decrypted = cipher.doFinal(Base64.decodeBase64(encData));
		String json = new String(decrypted, "utf8");
		return json;
	}

	public static boolean verify(long timestamp, String data, String sign)
			throws Exception {
		byte[] buffer = Base64.decodeBase64(publicKey.getBytes());
		KeyFactory keyFac = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
		RSAPublicKey pubKey = (RSAPublicKey) keyFac.generatePublic(keySpec);
		Signature signature = Signature.getInstance("SHA256WithRSA");
		signature.initVerify(pubKey);
		signature.update((data + timestamp).getBytes());
		return signature.verify(Base64.decodeBase64(sign));
	}

	public static void main(String[] args) throws Exception {
		String json = "{\"name\":张三,\":\":\"深圳市罗湖区\"}";
		long timestamp = System.currentTimeMillis() / 1000;
		String data = encrypt(json);
		String sign = sign(timestamp, data);
		System.out.println("data:" + data);
		System.out.println("sign:" + sign);

		boolean verfiy = verify(timestamp, data, sign);
		System.out.println(verfiy);
		if (!verfiy) {
			return;
		}
		String src = decrypt(data);
		System.out.println("src:" + src);
	}

}
