package com.messengerchat.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateNow {
	public static String getDateNowFull() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		
		return dtf.format(now);
	}
	
	public static String getDateNow() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();
		
		return dtf.format(now);
	}
}
