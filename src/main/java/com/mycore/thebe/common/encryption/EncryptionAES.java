package com.mycore.thebe.common.encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Simple AES Encryption
 * @author Thebe.Alfarisi
 * @since Oct, 22nd 2018
 * @version 1.0
 *
 */
public class EncryptionAES {

	private static SecretKeySpec secretKey;
	private static byte[] key;
	
	public static void setKey(String myKey) {
		
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static String doEncrypt(String strToEncrypt, String secret) {
		
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while encrypting: " + e.toString());
		}
		
		return null;
	}
	
	public static String doDecrypt(String strToDecrypt, String secret) {
		
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while decrypting: " + e.toString());
		}
		
		return null;
	}
	
}
