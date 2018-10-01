package validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.DateUtils;

public class TimeValidator {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public TimeValidator(){
	}
	
	public Boolean isValidTime(String date){
		Date incidentDate = new Date();
		try {
			incidentDate = sdf.parse(date);
		} catch (ParseException e) {
			return Boolean.FALSE;
		}
		return isValidTime(incidentDate);
	}
	
	public Boolean isValidTime(Date date) throws RuntimeException {
		if(!DateUtils.isValidDate(date)){
			throw new RuntimeException("La date n'était pas valide");
		}
		return Boolean.TRUE;
	}

}
