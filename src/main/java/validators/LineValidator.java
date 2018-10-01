package validators;

import java.io.IOException;

import loaders.DataLoader;
import utils.StringUtils;

public class LineValidator {
	
	
	private DataLoader dataLoader; 
	
	public LineValidator(){
		try {
			dataLoader = new DataLoader();
		} catch (IOException e) {
			//Throw exception
		}
	}

	public Boolean isValidData(String line, String station){
		if(dataLoader.getMapLigneStation().containsKey(line) && dataLoader.getMapLigneStation().get(line).contains(StringUtils.formatStationName(station))){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
