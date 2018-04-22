package com.minhthanh.bulk.admin.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

	private static String DATE_FORMAT_STRING = "dd-MM-yyyy hh:mm";
	public static Date stringToDate(String str){
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_STRING);
		Date parsedDate = null;
		try {
			parsedDate = formatter.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parsedDate;
	}
	
	public static String dateToString(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_STRING);
		String str = "";
		str = formatter.format(date);
		return str;
	}
}
