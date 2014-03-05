package com.mcc.myctiycard.common;

public class AllUrl {
	public static String commonUrl = "http://10.0.2.2:8080/mycityCardServer";
//	public static String commonUrl = "http://192.168.4.218:8080/mycityCardServer";
	
	public static String loginUrl = commonUrl + "/user/login.json";
	public static String updateAccountDataUrl = commonUrl + "/user/updateAccountData.json";
}
