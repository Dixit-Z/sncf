package utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static Boolean isFuture(Date date){
		if(date.after(Calendar.getInstance().getTime())){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public static Boolean isOlderThanDays(Date date, Integer days){
		Calendar pastDate = Calendar.getInstance();
		pastDate.add(Calendar.DAY_OF_MONTH, days);
		if(date.before(pastDate.getTime())){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public static Boolean isValidDate(Date date){
		return isFuture(date) ? Boolean.FALSE : !isOlderThanDays(date, -7);
	}

}
