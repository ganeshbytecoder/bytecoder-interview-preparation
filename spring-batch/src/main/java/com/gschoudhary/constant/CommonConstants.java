package com.gschoudhary.constant;

import java.time.format.DateTimeFormatter;

public class CommonConstants {
	
	
	private CommonConstants(){
	
	}

//	public  final static String[] CSV_TOKEN= "Asia/Kolkata";


	public  final static String TIME_ZONE= "Asia/Kolkata";
	public final static String UTF = "UTF-8";
	public final static String AUTHORIZATION = "Authorization";
	public final static String TOKEN = "token";
	
	public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
	public final static DateTimeFormatter DATE_TIME_FORMATTER_FOR_YEARLY_STATS = DateTimeFormatter.ofPattern("MM-yyyy");
	public final static DateTimeFormatter DATE_TIME_FORMATTER_FOR_MONTHLY_STATS = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	public final static DateTimeFormatter DATE_TIME_FORMATTER_FOR_DAILY_STATS = DateTimeFormatter.ofPattern("HH dd-MM-yyyy");
	
	
}
