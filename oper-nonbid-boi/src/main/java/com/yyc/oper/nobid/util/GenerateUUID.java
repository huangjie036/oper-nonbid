package com.yyc.oper.nobid.util;

import java.util.UUID;

public class GenerateUUID {

	public static String getUUID(){
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		String t = String.valueOf(System.nanoTime());
		id = id.substring(0, id.length() - t.length()) + t;
		return id;
	}
	
}
