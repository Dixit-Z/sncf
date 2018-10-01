package entities;

public class IncidentInfos {
	
	public IncidentInfos(String date, Boolean b){
		this.date=date;
		this.reported = b;
	}
	private String date;
	
	private Boolean reported;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Boolean getReported() {
		return reported;
	}

	public void setReported(Boolean reported) {
		this.reported = reported;
	}
	
	

}
