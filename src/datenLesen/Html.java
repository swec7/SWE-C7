package datenLesen;

import objekte.Modul;
import objekte.Studiengang;

public class Html {

	String pfad;
	
	
	
	Html(){
		
	}
	
	Html(String pfad){
		
		this.pfad=pfad;
		
	}
	
	Studiengang studiengangAuslesen(String pfad){
		
		Studiengang studiengang = null;
		
		//todo
		
		return studiengang;
		
	}
	
	int semesterAuslesen(String pfad) {
		int semester=0;
		
		//todo
		
		return semester;
	}
	
	String nameAuslesen(String pfad) {
		
		String name = null;
		
		//todo
		
		return name;
	}
	
	int matrikelnummerAuslesen(String pfad) {
		
		int matrikelnummer=0;
		
		//todo
		
		return matrikelnummer;
		
	}
	
	Modul[] bestandeneModuleAuslesen(String pfade, Modul[] module){
		
		//todo
		
		return module;
		
	}
	
}
