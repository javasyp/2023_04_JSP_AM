package com.KoreaIT.java.jam.config;

public class Config {
	public static String getDBUrl() {
		return "jdbc:mysql://127.0.0.1:3306/JAM?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull;";
	}
	
	public static String getDBUser() {
		return "root";
	}
	
	public static String getDBPswd() {
		return "";
	}
	
	public static String getDBDriverClassName() {
		return "com.mysql.jdbc.Driver";
	}
}