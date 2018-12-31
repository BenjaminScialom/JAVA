package Apis;

import java.io.IOException;
import org.json.JSONObject;

public abstract class Api {
	
	protected String nom;
	
	//Nom de l'api
	public Api(String nom) {
		this.nom = nom;
	}
	// Passage de Celsius à Fahrenheit
	public Double celsiusToFahrenheit(Float temp){
		return temp*1.8+32;
	}
	

	//Element meteo
	public abstract ElementMeteo processMeteo(String ville, int day) throws IOException;
	
	//Builders
	public abstract float buildTemperature(JSONObject json, int day);
	public abstract double buildWind(JSONObject json, int day);
	public abstract float buildHumidity(JSONObject json, int day);
	public abstract String buildPosition(JSONObject json);
	public abstract JSONObject buildURL(String ville) throws IOException;
	
	//Getters
	public abstract float[] GetWinds(Api api, String ville) throws IOException;
	public abstract float[] GetHumidities(Api api, String ville) throws IOException;
	public abstract float[] GetTemperatures(Api api, String ville) throws IOException;
	public abstract String getNom() throws IOException;
}
