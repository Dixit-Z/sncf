package entities;

import java.util.List;
import java.util.stream.Collectors;

import entities.Station;

public class Line {
	
	private String lineName;
	
	private List<Station> stations;

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	
	public List<String> getStationsNames(){
		return this.stations.stream().map(Station::getStationName).collect(Collectors.toList());
	}

}
