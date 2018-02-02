package com.ajahsma.carservice.utils;

/** * Copyright (c) 2018 AJAHSMA, Inc. All Rights Reserved*/
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
/**
 * @author AMARESH S A
 */

public class DateUtil {

	public static final long DAY_IN_MILLIS = 86400000;

	public static final String[] PARSE_PATTERNS = new String[] {"yyyy-MM-dd",
			"MM/dd/yyyy", "EEE, dd MMM yyyy HH:mm:ss zzz",
			"yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss",
			"yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS",
			"yyyy-MM-dd HH:mm:ss.SSS", "MM/dd/yyyy hh:mm a", "MM/yyyy",
			"yyyy-MM", "yyyy", "yyyy-MM-dd HH:mm:ss,SSS",
			"yyyy-MM-dd HH:mm:ss", "MMM DD, YYYY"};

	public static final String[] FORMAT_PATTERNS = new String[] {
			"dd MMM yyyy", "yyyy-MM-dd", "MM/dd/yyyy" };

	public static Date convertDate(String value, String inputDatePattern) {
		/*// If the value is null or empty, return null value.
		if (StringUtil.isNullOrEmptyTrimmed(value)) {
			return null;
		}*/
		// If the string ends in -00 (or) -0 meaning 00 months then convert it
		// to
		// a single digit months
		int valueLength = value.length();
		if ((valueLength == 6 || valueLength == 7)
				&& (value.endsWith("-0") || value.endsWith("-00"))) {
			value = value.substring(0, value.length() - 1);
			value += "1";
		}

		SimpleDateFormat parser = null;
		/**
		 * 
		 * Java Doc: ParsePosition is a simple class used by Format and its
		 * subclasses to keep track of the current position during parsing. The
		 * parseObject method in the various Format classes requires a
		 * ParsePosition object as an argument. By design, as you parse through
		 * a string with different formats, you can use the same ParsePosition,
		 * since the index parameter records the current position.
		 * 
		 */
		ParsePosition pos = new ParsePosition(0);
		parser = new SimpleDateFormat(inputDatePattern);
		pos.setIndex(0);
		try {
			Date date = parser.parse(value, pos);
			// Make sure that the applied pattern returns the date instance
			// in the same format.
			if (date != null && pos.getIndex() == value.length()) {
				return checkDate(date);
			}
		} catch (Exception pe) {
			// Empty block
		}
		// If the value doesn't match with the pattern, return null
		// value.
		return null;
	}

	private static Date checkDate(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		if (year < 1753) {
			cal.clear();
			cal.set(1753, 0, 1, 0, 0, 0);
			cal.set(Calendar.MILLISECOND, 0);
		} else if (year > 9999) {
			cal.clear();
			cal.set(9999, 0, 1, 0, 0, 0);
			cal.set(Calendar.MILLISECOND, 0);
		}
		return cal.getTime();
	}

	public static Date midnightize(final Date date) {
		Calendar cal = getMidnightizedCalendar(date);
		return cal.getTime();
	}

	private static Calendar getMidnightizedCalendar(final Date date) {
		return getMidnightizedCalendar(date, TimeZone.getDefault());
	}

	private static Calendar getMidnightizedCalendar(final Date date,
			final TimeZone timeZone) {
		Calendar cal = Calendar.getInstance(timeZone);
		cal.setTime(date);
		midnightize(cal);
		return cal;
	}

	public static void midnightize(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}

	public static String getDateAsString(Date date, String outputDatePattern) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(outputDatePattern);
			return df.format(date);
		} catch (Exception e) {
		}
		return "";
	}

	public static long getNoOfDaysBetweenTwoDates(Date currentDate,
			Date previousDate) {
		if (currentDate == null && previousDate == null) {
			return 0;
		}
		return (currentDate.getTime() - previousDate.getTime()) / DAY_IN_MILLIS;
	}

	public java.sql.Date convertStringToDate(String dateString) {
		java.util.Date date = null;
		if (dateString == null || dateString.trim().length() == 0) {
			return null;
		}
		DateFormat df = new SimpleDateFormat("MM/dd/yy");
		try {
			date = df.parse(dateString);

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return new java.sql.Date(date.getTime());
	}
	public static String customDateFormat(String customDateFormat) {
		if (customDateFormat != null && !"".equals(customDateFormat) &&customDateFormat.contains("/") && customDateFormat.trim() != null) {
				String[] split = customDateFormat.split("/");
				if (split != null) {
					String month = split[0];
					int intMonth = Integer.parseInt(month);
					customDateFormat = new DateFormatSymbols().getShortMonths()[intMonth - 1].concat(" ").concat(split[1])
							.concat(", ").concat(split[2]);
			}
		} else if( customDateFormat != null && !"".equals(customDateFormat) &&customDateFormat.contains("-") && customDateFormat.trim() != null){
			String[] split1 = customDateFormat.split("-");
			if (split1 != null) {
				String month = split1[1];
				int intMonth = Integer.parseInt(month);
				 customDateFormat = new DateFormatSymbols().getShortMonths()[intMonth - 1].concat(" ")
						+ split1[2].concat(", ") + split1[0];

			}

		}
		return customDateFormat;
	}
	
	/*
	 * public static void main( String args ) { System.out.println( convertDate(
	 * "2013-07-15 12:12:05", PARSE_PATTERNS[13] ) );
	 * System.out.println(getDateAsString(new Date(), FORMAT_PATTERNS[0])); }
	 */
	
	
	//Devu added
	public static Map<String, Date> getWeekDifference()
	{
		Calendar cal = Calendar.getInstance();
		Date startDate = cal.getTime();
		 System.out.println(cal.getTime());
		 cal.add(Calendar.WEEK_OF_MONTH, -1);
		 Date endDate = cal.getTime();
		 Map<String, Date> map = new HashMap<>();
		 map.put("startDate", startDate);
		 map.put("endDate", endDate);
		return map;
	}
	
	public static Map<String, Date> getMonthDifference()
	{
		Calendar cal = Calendar.getInstance();
		Date startDate = cal.getTime();
		 System.out.println(cal.getTime());
		 cal.add(Calendar.MONTH, -1);
		 Date endDate = cal.getTime();
		 Map<String, Date> map = new HashMap<>();
		 map.put("startDate", startDate);
		 map.put("endDate", endDate);
		return map;
	}
}