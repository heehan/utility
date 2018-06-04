package com.scyun.utility.tools;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*****************************************************************************
 * 문자열 변환과 관련된 모든 유틸리티성 메소드를 포함한다.                                
 * @author yunhuihan
 *
 */
public class StringTools {
	public static final String DEFAULT_DELIM = ","; 
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd hh:mm:ss";
	public static <T> String join(T[] t) {
		return join(t, DEFAULT_DELIM);
	}
	public static <T> String join(T[] t , String delim) {
		StringBuilder builder = new StringBuilder();
		for ( T elem : t) {
			if (elem.getClass().isPrimitive()) {
				builder.append(elem).append(delim);
			} else {
				builder.append(elem.toString()).append(delim);
			}
		}
		return builder.substring(0 , builder.length() - delim.length()); // 가장 마지막 구분자 제거. 
	}
	public static LocalDate toDate(String dateStr, DateTimeFormatter formatter) {
		return LocalDate.parse(dateStr, formatter);
	}
	public static LocalDate toDate(String dateStr, String formatterPattern) {
		return toDate(dateStr, DateTimeFormatter.ofPattern(formatterPattern));
	}
	public static LocalDateTime toDateTime(String dateStr , DateTimeFormatter formatter) {
		return LocalDateTime.parse(dateStr, formatter);
	}
	public static LocalDateTime toDateTime(String dateStr, String formatterPattern) {
		return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(formatterPattern));
	}

	public static Integer toInt(String source) {
		return Integer.parseInt(source);
	}
	public static Integer toInt(String source, Integer defVal) {
		Integer result = defVal;
		try {
			result = Integer.parseInt(source);
		} catch (NumberFormatException nfe) { }
		
		return result;
	}

	public static Long toLong(String source) {
		return Long.parseLong(source);
	}
	public static Long toLong(String source, Long defVal) {
		Long result = defVal;
		try {
			result = Long.parseLong(source);
		} catch (NumberFormatException nfe) { }
		return result;
	}

}
