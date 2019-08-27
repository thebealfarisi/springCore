package com.mycore.thebe.testrun;

import com.mycore.thebe.common.encryption.EncryptionAES;

public class DoAESEncryption {
	public static void main(String[] args) {
		
		final String secretKey = "AINK!!!";
		
		String originalString = "THEBE GANTENG";
		
		String encryptedString = EncryptionAES.doEncrypt(originalString, secretKey);
		String decryptedString = EncryptionAES.doDecrypt(encryptedString, secretKey);
		
		System.out.println("Original String: " + originalString);
		System.out.println("Encrypted String: " + encryptedString);
		System.out.println("Decrypted String: " + decryptedString);
	}
}
