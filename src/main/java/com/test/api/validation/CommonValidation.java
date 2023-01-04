package com.test.api.validation;

public class CommonValidation{
	
	
	 public static String trim(String string) {
	        return string != null ? string.trim() :"Bad Request";
	    }
}