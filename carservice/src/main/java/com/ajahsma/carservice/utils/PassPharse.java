package com.ajahsma.carservice.utils;


/** * Copyright (c) 2018 AJAHSMA, Inc. All Rights Reserved*/

/**
 * @author AMARESH S A
 */

public class PassPharse {
	
	public static final String ENCRYPT_SQL_FUNCTION = "dbo.lcfn_Encrypt";
	private static final byte[] PASS_PHRASE = {0x54, 0x72, 0x69, 0x70, 0x54, 0x68, 0x65, 0x4c, 0x69, 0x67, 0x68, 0x74, 0x46, 0x61, 0x6e, 0x64, 0x61, 0x6e, 0x67, 0x6f};
	private static final int PASS_PHRASE_LENGTH = PASS_PHRASE.length;
	
	
	public static String getPassPhrase() {
		StringBuilder sb = new StringBuilder( PASS_PHRASE_LENGTH );
		for( int index = 0; index < PASS_PHRASE_LENGTH; ++index ) {
			sb.append( ( char ) PASS_PHRASE[index] );
		}
		return sb.toString();
	}
}