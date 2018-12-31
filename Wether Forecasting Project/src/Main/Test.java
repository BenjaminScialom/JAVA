package Main;

import Apis.*;
import java.io.IOException;


public class Test {
   public static void main(String[] args) throws IOException {
	  
	   CommandLign comLign = new CommandLign(args);
	   
	   
	   Api apiY = new Yahoo("Yahoo");
	   Api apiM = new Metaweather("Metaweather");
	   Api apiP = new Previsionmeteo("Prev ch");
	   Api[] tabApi= {apiY,apiM,apiP};
	   
	   
	   Affichage disp = new Affichage(tabApi,comLign.getCity(),comLign.getNbJours(),comLign.getHumidity(),comLign.getWind(),comLign.getInCelsius());
	   disp.writeInConsole();
	      	   
   	}
	   
   }

