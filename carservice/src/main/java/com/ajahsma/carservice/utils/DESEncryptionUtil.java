package com.ajahsma.carservice.utils;


/** * Copyright (c) 2018 AJAHSMA, Inc. All Rights Reserved*/
/**
 * @author AMARESH S A
 */

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class DESEncryptionUtil {
	private static Cipher ecipher;
	private static Cipher dcipher;
	
	static {
		init();
	}

	private static void init() {
		try {
			// 8-byte Salt
			byte[] salt = {(byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
					(byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03 };
			// Iteration count
			int iterationCount = 10;
			// Create the key
			KeySpec keySpec = new PBEKeySpec( PassPharse.getPassPhrase().toCharArray(), salt, iterationCount );
			SecretKey key = SecretKeyFactory.getInstance( "PBEWithMD5AndDES" ).generateSecret( keySpec );
			ecipher = Cipher.getInstance( key.getAlgorithm() );
			dcipher = Cipher.getInstance( key.getAlgorithm() );
			// Prepare the parameter to the ciphers
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec( salt, iterationCount );
			// Create the ciphers
			ecipher.init( Cipher.ENCRYPT_MODE, key, paramSpec );
			dcipher.init( Cipher.DECRYPT_MODE, key, paramSpec );
		} catch( Exception e ) {
		}
	}

	public static String encrypt(String str) {
		try {
			
			init();
			
			// Encode the string into bytes using utf-8
			byte[] utf8 = str.trim().getBytes("UTF8");

			// Encrypt
			byte[] enc = ecipher.doFinal(utf8);

			// Encode bytes to base64 to get a string
			@SuppressWarnings("restriction")
			String encryptedString = new sun.misc.BASE64Encoder().encodeBuffer(enc);
			
			//Since it is BASE64Encoder, it adds a new line character 
			//in the encrypted string when the length of string crosses 
			//64 characters (salt + key), hence we need to replace it
			encryptedString = encryptedString.replace( "\r\n", "");
			encryptedString = encryptedString.replace( "\n", "");
			encryptedString = encryptedString.replace( "\r", "");
			encryptedString = encryptedString.replace( " ", "" );
			encryptedString = encryptedString.replace( "\t", "" );
			
			return encryptedString;
		} catch (Exception e) {
		}
		return null;
	}

	public static String decrypt(String str) {
		try {
			
			init();
			
			// Decode base64 to get bytes
			@SuppressWarnings("restriction")
			byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str.trim());
			// Decrypt
			byte[] utf8 = dcipher.doFinal(dec, 0, dec.length );
			// Decode using utf-8
			return new String(utf8, "UTF8");
		} catch (Exception e) {
		}
		return null;
	}
	
	/*public static void main( String[] arg){
 	    String encrypt = encrypt( "2211899" );
		System.out.println( encrypt );
		System.out.println( decrypt(encrypt) );
	}*/
}