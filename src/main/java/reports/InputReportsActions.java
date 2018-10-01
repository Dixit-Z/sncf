package reports;

import utils.StringUtils;

public class InputReportsActions {
	
	private String line;
	
	private String station;
	
	private String incidentDate;

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = StringUtils.formatStationName(line);
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = StringUtils.formatStationName(station);
	}

	public String getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(String incidentDate) {
		this.incidentDate = incidentDate;
	}
	
}
