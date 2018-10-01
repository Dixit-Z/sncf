package entities;

import java.util.ArrayList;
import java.util.List;

public class Incident {
	
	private String line;
	
	private String station;
	
	private List<IncidentInfos> infos;

	public Incident(String string, String string2, String string3, Boolean b) {
		this.line = string;
		this.station = string2;
		this.infos = new ArrayList<>();
		this.setInfos(new IncidentInfos(string3, b));
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public List<IncidentInfos> getInfos() {
		return infos;
	}

	public void setInfos(IncidentInfos infos) {
		this.infos.add(infos);
	}
	
}
