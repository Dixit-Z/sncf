package loaders;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import utils.StringUtils;

public class DataLoader {
	
	private ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> mapLigneStation = new ConcurrentHashMap<>();
	
	private String[] headerCSV = {"Libelle","A","B","C","D","E","H","J","K","L","N","P","R","U"};
	
	public DataLoader() throws IOException{
		CSVParser parser = new CSVParser(new InputStreamReader(this.getClass().getResourceAsStream("/lignes.csv")), CSVFormat.EXCEL.withDelimiter(';').withHeader(headerCSV));
		parser.getRecords().parallelStream().forEach(this::loadDataInMap);
		parser.close();
		
	}
	
	private void loadDataInMap(CSVRecord record){
		for(String header : headerCSV){
			if("1".equalsIgnoreCase(record.get(header))){
				if(this.mapLigneStation.get(header) == null){
					this.mapLigneStation.put(header, new ConcurrentLinkedQueue<>(Arrays.asList(StringUtils.formatStationName(record.get("Libelle")))));
				}
				else{
					this.mapLigneStation.get(header).add(StringUtils.formatStationName(record.get("Libelle")));
				}
			}
		}
	}

	public ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> getMapLigneStation() {
		return mapLigneStation;
	}

	public void setMapLigneStation(ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> mapLigneStation) {
		this.mapLigneStation = mapLigneStation;
	}
	
}
