package Main;

import Apis.*;


import java.io.IOException;

//import java.io.PrintStream;
	
public class Affichage {
	
	protected Api[] tabApi ;

	private int nbJours;
	private boolean humidity;	   
	private boolean wind;		   
	private boolean inCelsius;  
	private String city;
	

	public Affichage(Api[] tabApi,String city,int nbJours, boolean humidity,boolean wind, boolean inCelsius){

		this.nbJours = nbJours;
		this.tabApi=tabApi;
		this.humidity = humidity;
		this.wind = wind;
		this.inCelsius = inCelsius;
		this.city=city;
	}

	public void writeInConsole() throws IOException {

						
		// Affichage ligne du haut
		System.out.print("+-------------+");
		for (int i=0;i<this.nbJours;i++){
			System.out.print("--------+");
		}
		System.out.print("\n");

		System.out.print("|             |");
		for (int i=0;i<this.nbJours;i++){
			System.out.print("  J+"+i+"   |");
		}
		System.out.print("\n");

		System.out.print("+-------------+");
		for (int i=0;i<this.nbJours;i++){
			System.out.print("--------+");
		}
		System.out.print("\n");

		// Remplissage API par API en ligne

		for (Api api : tabApi) {
			
				//Cas de la température
				if(this.inCelsius) { //si c'est en celsius
					System.out.print("|"+ centerInfos(api.getNom(),13) +"|");
					for (int i=0;i<this.nbJours;i++){
						System.out.print(centerInfos(Float.toString(api.GetTemperatures(api,city)[i])+"°",8)+"|");
				}
				System.out.print("\n");
				}
				else {
					System.out.print("|"+ centerInfos(api.getNom(),13) +"|");
					for (int i=0;i<this.nbJours;i++){
						System.out.print(centerInfos(Double.toString(api.celsiusToFahrenheit(api.GetTemperatures(api,city)[i]))+"F",8)+"|");
				}
				System.out.print("\n");
				}
				
				// Cas de l'humidité
				if(this.humidity) {
				System.out.print("|             |");
				for (int i=0;i<this.nbJours;i++){
					System.out.print(centerInfos(Float.toString(api.GetHumidities(api,city)[i])+"%",8)+ "|");
				}
				System.out.print("\n");
				}
				// Cas du vent
				if(this.wind) {
				System.out.print("|             |");
				for (int i=0;i<this.nbJours;i++){
					System.out.print(centerInfos(Float.toString(api.GetWinds(api,city)[i])+"mph",8)+"|");
				}
				System.out.print("\n");
				}
				
				//Fermeture basse du tableau
				System.out.print("+-------------+");
				for (int i=0;i<this.nbJours;i++){
					System.out.print("--------+");
				}
				System.out.print("\n");				
		}
				
	}
	
	// Méthode pour centrer les données dans le tableau	
	public static String centerInfos(String s, int width) {
		    
		int padSize = width - s.length();
		    int padStart = s.length() + padSize / 2;

		    s = String.format("%" + padStart + "s", s);
		    s = String.format("%-" + width  + "s", s);
		    return s;
	}

	
}





	