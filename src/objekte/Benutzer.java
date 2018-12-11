package objekte;

public class Benutzer {

	Studiengang studiengang;
	int semester=0;
	String name;
	int matrikelnummer;
	Modul[] Module;
	int fehlversuche;
	int verbesserungsversuche;
	float durchschnittsnote;
	
	
	Benutzer(){
		
		
		
	}
	
	Benutzer(Studiengang studiengang, int semester, String name, int matrikelnummer, Modul[] Module, int fehlversuche, int verbesserungsversuche, float durchschnittsnote){
		
		this.studiengang=studiengang;
		this.semester=semester;
		this.name=name;
		this.matrikelnummer=matrikelnummer;
		this.Module=Module;
		this.fehlversuche=fehlversuche;//muss geguckt werden ob und für was benötigt
		this.verbesserungsversuche=verbesserungsversuche;
		this.durchschnittsnote=durchschnitsnoteBerechnen(this.Module);
		
	}
	
	float durchschnitsnoteBerechnen(Modul[] bestandeneModule) {
		
		float durchschnitsnote=0;
		
		
		
		return durchschnitsnote;
	
	}
	
	int verbesserungsversucheBerechnen(Modul[] bestandeneModule, Studiengang studiengang) {
		
		int verbesserungsVersuche=0;
		
		
		
		return verbesserungsVersuche;
	
	}
	
}
