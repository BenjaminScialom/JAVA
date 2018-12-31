package Apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.*;



public class Previsionmeteo extends Api{
	
	
	
	public Previsionmeteo(String nom) {
		super(nom);
	}
	
	
	public ElementMeteo processMeteo(String ville, int day) throws IOException {
		JSONObject json = buildURL(ville);
		  
		float temperature = buildTemperature(json, day);
		float humiditee = buildHumidity(json, day);
		float vent = (float) buildWind(json, day);
    		String position = buildPosition(json);

    	return new ElementMeteo(temperature, humiditee, vent, position, day); 
		
	}
	
	public float buildTemperature(JSONObject json, int day) {
		JSONObject obj1 = json.getJSONObject(buildDay(day));
    	int tmax =  (int) obj1.getNumber("tmax");
    	int tmin = (int) obj1.getNumber("tmin");
    	return (float) (tmax+tmin)/2; 
	}
	
	public double buildWind(JSONObject json, int day) {
		JSONObject obj1 = json.getJSONObject(buildDay(day));
		JSONObject hourly_data = obj1.getJSONObject("hourly_data");
    	double vitesse_vent = 0;
    	for(int i=0;i<24;i++) {
    		String hour = String.format("%1d", i);
    		hour = hour+"H00";
    		JSONObject x2 = hourly_data.getJSONObject(hour);
    		vitesse_vent = (vitesse_vent +(int) x2.getNumber("WNDSPD10m"));
    	}
    	vitesse_vent = (float)vitesse_vent/24; // A redefinir probablement
    	return Math.round(vitesse_vent);
	}
	
	public float buildHumidity(JSONObject json, int day) {
		JSONObject obj1 = json.getJSONObject(buildDay(day));
		JSONObject hourly_data = obj1.getJSONObject("hourly_data");
    	int hum = 0;
    	for(int i=0;i<24;i++) {
    		String hour = String.format("%1d", i);
    		hour = hour+"H00";
    		JSONObject x2 = hourly_data.getJSONObject(hour);
    		hum = hum + (int) x2.getNumber("RH2m");
    	}
    	
    	return (float)hum/24;
	}
	
	public String buildPosition(JSONObject json) /*throws UnknownPosition*/{
		JSONObject city_info = json.getJSONObject("city_info");
    	String city = city_info.getString("name");
    	
    	return city;
	}
	
	private String buildDay(int jour) {
		String day = new String("");
		
		switch(jour) {
		case 0:
			day = "fcst_day_0";
		case 1:
			day = "fcst_day_1";
		case 2:
			day = "fcst_day_2";
		case 3:
			day = "fcst_day_3";
		case 4:
			day = "fcst_day_4";
		}
		return day;
	}
	
	public JSONObject buildURL(String ville) throws IOException {
		
		URL url = new URL("https://www.prevision-meteo.ch/services/json/"+ville);
					
		String inputLine;
		String source = "";
		
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



	public String getNom() {
		return nom;
	}


	/*@Override
	public void setPosition(String ville) {
		// TODO Auto-generated method stub
		
	}*/

	
	
}
