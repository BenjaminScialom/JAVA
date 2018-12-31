package Apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class Metaweather extends Api {
		
		
		
		public Metaweather(String nom) {
			super(nom);
			
		}
		
		
		public ElementMeteo processMeteo(String ville, int day) throws IOException {
			JSONObject json = buildURL(ville);
			float temperature = buildTemperature(json, day);
			float humiditee = buildHumidity(json, day);
			float vent = (float)buildWind(json, day);
	    		String position = buildPosition(json);

	    	return new ElementMeteo(temperature, humiditee, vent, position, day); 
		}
		
		public float buildTemperature(JSONObject json, int day ) {
			JSONArray x = json.getJSONArray("consolidated_weather");
			double maxtemp = (double) x.getJSONObject(day).getNumber("max_temp");
			double mintemp = (double) x.getJSONObject(day).getNumber("min_temp");
			return (float)(maxtemp+mintemp)/2;
		}
		
		public double buildWind(JSONObject json, int day) {
			JSONArray x = json.getJSONArray("consolidated_weather");
			return (double) x.getJSONObject(day).getNumber("wind_speed")	;		
		}
		
		public float buildHumidity(JSONObject json, int day) {
			JSONArray x = json.getJSONArray("consolidated_weather");
			float x1 = (int) x.getJSONObject(day).getNumber("humidity");
			return  x1;
		}
		
		public String buildPosition(JSONObject json) {
			return json.getString("title");
			
		}
		
		public JSONObject buildURL(String ville) throws IOException{
			//URL url = new URL("https://www.metaweather.com/api/location/search/?query="+ville);
			URL url = new URL("https://www.metaweather.com/api/location/search/?query="+ville);

			String inputLine;
			String source = "";
			//ToDo g�rer les erreurs
			URLConnection yc = url.openConnection();
			
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							yc.getInputStream()));
	  	
			while ((inputLine = in.readLine()) != null) {
				source += inputLine;
			}
			String s = "";
			for (char ch : source.toCharArray()){
				if (ch == '[' || ch == ']') {
					continue;
				}
				s = s+ch;	       
			}
	   
	  	
	  	JSONObject x1 = new JSONObject(s);
		String woeid =  String.format("%d", (int) x1.getNumber("woeid"));
		
		URL url1 = new URL("https://www.metaweather.com/api/location/"+woeid);

		String inputLine1;
		String source1 = "";
		//ToDo g�rer les erreurs
		URLConnection yc1 = url1.openConnection();
		
    	BufferedReader in1 = new BufferedReader(
    	new InputStreamReader(
    	yc1.getInputStream()));
    	
    	while ((inputLine1 = in1.readLine()) != null) {
    		source1 += inputLine1;
    	}
    	return new JSONObject(source1);
		}
		
		public float[] GetTemperatures(Api api, String ville) throws IOException  {
			float tab_tmp[] = new float[4];
			
			for(int i=0; i<tab_tmp.length; i++) {
				ElementMeteo eltmeteo = api.processMeteo(ville, i);
				tab_tmp[i] = Math.round(eltmeteo.getTemperature());  
			   }
			return tab_tmp;
		}
		
		public float[] GetWinds(Api api, String ville) throws IOException{
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

		/*
		public void setNom(String nom) {
			this.nom = nom;
		}


		public String getUrl() {
			return url;
		}


		public void setUrl(String url) {
			this.url = url;
		}


		@Override
		public void setPosition(String ville) {
			// TODO Auto-generated method stub
			
		}
*/
	}