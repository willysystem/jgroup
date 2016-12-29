package com.jgroup.creditos.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tools {

	private static Tools instance = null;

	public static Tools getInstance() {
		if (instance == null)
			instance = new Tools();
		return instance;
	}

	public String date2String(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmssaaa");
		return formato.format(fecha);
	}
	
	public static Date addToDate(Date date, int days, int month, int year)
	{
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.DATE, days);
	    cal.add(Calendar.MONTH, month); 
	    cal.add(Calendar.YEAR, year); 
	    return cal.getTime();
	}
	

}
