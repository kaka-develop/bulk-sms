package com.minhthanh.bulk.admin.manager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationManager {
	
	public static boolean validatePassword(String password) {
		String regex = "\\w{8,}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
}
