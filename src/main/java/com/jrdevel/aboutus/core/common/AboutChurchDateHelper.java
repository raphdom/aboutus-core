package com.jrdevel.aboutus.core.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Raphael Domingues
 *
 */
public class AboutChurchDateHelper {
	
	public static Date getDate(String date){
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date result = null;
		
		try {
			result = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Date getDateWithTimeToMidnight(Date date){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR,23);
		cal.set(Calendar.MINUTE,59);
		cal.set(Calendar.SECOND,59);
		
		return cal.getTime();
		
	}

}
