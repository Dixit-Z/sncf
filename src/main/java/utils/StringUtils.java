package utils;

public class StringUtils {
	
	public static String formatStationName(String stationName){
		return stationName.toUpperCase().replace("-", "").replace("'","").replace(",", "").replace(" ", "").replace("(","").replace(")","").trim();
	}
}
