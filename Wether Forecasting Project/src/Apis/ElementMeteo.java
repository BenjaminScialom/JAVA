package Apis;


public class ElementMeteo {
	private float temperature;
	private float humidite;
	private float vent;
	private String position;
	private int journee;
	
	public ElementMeteo(float tmp, float humidite, float vent, String position, int journee) {
		this.temperature = tmp;;
		this.humidite = humidite;
		this.vent = vent;
		this.position = position;
		this.journee = journee;
	}
	
	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getHumidite() {
		return humidite;
	}

	public void setHumidite(float humidite) {
		this.humidite = humidite;
	}

	public float getVent() {
		return vent;
	}

	public void setVent(float vent) {
		this.vent = vent;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getJournee() {
		return journee;
	}

	public void setJournee(int journee) {
		this.journee = journee;
	}
	
}
