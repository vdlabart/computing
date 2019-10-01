package tollparking.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utility {
	
	final static long MS_FOR_HOUR = 3600000L;
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static int getHour(Date from, Date to) {
		if (from.after(to)) {
			return 0;
		}
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(to); 
		long toMs = calendar.getTimeInMillis();
		calendar.setTime(from); 
		long fromMs = calendar.getTimeInMillis();
		long diff = toMs - fromMs;
		return (int) Math.ceil(((double)diff/MS_FOR_HOUR));
	}
}
