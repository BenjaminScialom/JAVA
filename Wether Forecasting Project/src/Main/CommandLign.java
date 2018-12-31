package Main;

import Error.LignInvalid;

public class CommandLign {

	private String city;
	private int nbJours;
	private boolean humidity;
	private boolean wind;
	private boolean inCelsius;
	//private String saveType;
	//private String nameType;
	
	public CommandLign(String[] args) throws LignInvalid{
		
		// Test sur le nombre d'argument
		if(args.length<1){
			throw new LignInvalid();
		}
		
		this.city = args[0];
		this.nbJours=1;
		this.humidity=false;
		this.wind=false;
		this.inCelsius=true;
		//this.saveType = "";
		//this.nameType = "";
				
		for (int i=1;i<args.length;i++){
			// si le nombre de jour n'est pas spécifié 
			if(args[i].equals("-j")){
				if (args.length==i+1){
					throw new LignInvalid();
				}
				this.nbJours = Integer.parseInt(args[i+1])+1;// récupération du nombre de jour
			}
			// En celsius ou en Farheneit
			if(args[i].equals("-m")){
				if (args.length==i+1){
					throw new LignInvalid();
				}
				if(args[i+1].equals("F")){
					this.inCelsius = false;
				}
			}
			
			// Humidité ou pas humidité
			if(args[i].equals("-h")){
				this.humidity = true;
			}
			
			// Vent ou pas vent
			if(args[i].equals("-w")){
				this.wind = true;
			}

			/*// Ecriture ou non dans un fichier en remplaçant le contenu
			if(args[i].equals("-o")){
				this.saveType = args[i];
				this.nameFile = args[i+1];
			}
			// Ecriture ou non dans un fichier non vide 
			if(args[i].equals("-a")){
				this.saveType = args[i];
				this.nameType = args[i+1];
			}*/
		}
		
	}
	
	
	// Getters et Setters 
	public String getCity(){
		return this.city;
	}
	
	public int getNbJours(){
		return this.nbJours;
	}
	
	public boolean getHumidity(){
		return this.humidity;
	}
	
	public boolean getWind(){
		return this.wind;
	}
	
	public boolean getInCelsius(){
		return this.inCelsius;
	}
/*	
	public String saveType(){
		return this.;
				
	}
	
	public String nameType(){
		return this.;
	}
*/
}
