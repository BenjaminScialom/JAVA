package Apis;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.*;

//import Error.UnknownPosition;

public class Yahoo extends Api{
	
	
	
// Nom de l'API
	
	public Yahoo(String nom) {
		super(nom);
	}
	
	
	public ElementMeteo processMeteo(String ville, int jour) throws IOException {
		
		JSONObject json = buildURL(ville);
	// Il faut voir comment on fait pour le jour car sur yahoo on peut seulement avoir la température sur plusieurs jours
		
    	float temperature = buildTemperature(json, jour);
    	float humiditee = buildHumidity(json, jour);
    	float vent = (float)buildWind(json, jour);
    	String position = buildPosition(json);


    	return new ElementMeteo(temperature, humiditee, vent, position, jour); 
		
	}
		
	// Récupération de la température
	public float buildTemperature(JSONObject json, int jour) {
		JSONObject tmp1;
		JSONArray tmparray;
		tmp1 = json.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item");
		tmparray = tmp1.getJSONArray("forecast");
		JSONObject joursObject = tmparray.getJSONObject(jour);
		float temperature = (float)(joursObject.getInt("high")+joursObject.getInt("low"))/2; // En Fahrenheit
		return (float) ((temperature-32)/(1.8)) ; // En Celcius
	}
	
	// Récupération de l'unité de la température
	//String unit_temp=json.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("units").getString("temperature");
	
	// Récupération du lieu
	public String buildPosition(JSONObject json) /*throws UnknownPosition*/{
		String city=json.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("location").getString("city");
		return city;
	}
	
	// Récupération de l'humidité
	public float buildHumidity(JSONObject json, int day) {
		return (float)json.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("atmosphere").getInt("humidity");
	}
	
	// Récupération du vent
	public double buildWind(JSONObject json, int day) {
		return (double)json.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("wind").getInt("speed");
	}
	//Unité du vent
	//String unit_speed=json.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("units").getString("speed");
	
	
	public JSONObject buildURL(String ville) throws IOException {
		URL url = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+ville+"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
		String inputLine;
		String source = "";
		//ToDo gérer les erreurs
		URLConnection yc = url.openConnection();
		
    	BufferedReader in = new BufferedReader(
    	new InputStreamReader(
    	yc.getInputStream()));
    	
    	while ((inputLine = in.readLine()) != null) {
    		source += inputLine;
    	}
    	return new JSONObject(source);
	}
	
	public float[] GetTemperatures(Api api, String ville) throws IOException {
		float tab_tmp[] = new float[4];;
		
		for(int i=0; i<tab_tmp.length; i++) {
			ElementMeteo eltmeteo = api.processMeteo(ville, i);
			tab_tmp[i] = Math.round(eltmeteo.getTemperature());  
		   }
		return tab_tmp;
	}
	
	public float[] GetWinds(Api api, String ville) throws IOException {
		float tab_wind[] = new float[4];
		
		for(int i=0; i<tab_wind.length; i++) {
			ElementMeteo eltmeteo = api.processMeteo(ville, i);
			
			tab_wind[i] = Math.round(eltmeteo.getVent());
  
		   }
		return tab_wind;
	}
	
	public float[] GetHumidities(Api api, String ville) throws IOException {
		float tab_humidity[] = new float[4];
		
		for(int i=0; i<tab_humidity.length; i++) {
			ElementMeteo eltmeteo = api.processMeteo(ville, i);
			
			tab_humidity[i] = Math.round(eltmeteo.getHumidite());
  
		   }
		return tab_humidity;
	}
	/*
	@Override
	public void setPosition(String ville) {
		
		
	}*/
	
	public String getNom() throws IOException{
		return nom;
	}

}
