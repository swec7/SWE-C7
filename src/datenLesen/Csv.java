package datenLesen;

import objekte.Modul;
import objekte.Studiengang;

public class Csv {

	String pfadStudiengang;
	String pfadModule;
	String studiengang;
	
	
	Csv(){
		
	}
	
	Csv(String pfadStudiengang,String pfadModule, String studiengang){
		
		this.pfadModule=pfadModule;
		this.pfadStudiengang=pfadStudiengang;
		this.studiengang=studiengang;
		
	}
	
	Studiengang studiengangAuslesen(String pfadStudiengang) {
		
		Studiengang studiengang = null;
		
		//todo
		
		return studiengang;
		
	}
	
	
	Modul[] moduleAuslesen(String pfadModule) {
		
		Modul[] module = null;
		
		//todo
		
		return module;
		
	}
}
