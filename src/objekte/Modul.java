package objekte;

import java.text.DateFormat;

public class Modul {

	int modulnummer;
	String modulname;
	int cp;
	int semester;
	float note;
	int fehlversuche;
	boolean verbessert;
	Studiengang studiengang;
	char typ;
	boolean bestanden;
	DateFormat datumWannBestanden;
	DateFormat ablaufdatumFuerVerbesserung;
	boolean bearbeitbar;//unklar wofür genutzt
	
	
	Modul(){
		
	}
	
	Modul(int modulnummer, String modulname, int cp, int semester, float note, int fehlversuche, boolean verbessert, Studiengang studiengang, char typ, boolean bestanden, DateFormat datumWannBestanden, DateFormat ablaufdatumFürVerbesserung, boolean bearbeitbar){
		
		this.modulnummer=modulnummer;
		this.modulname=modulname;
		this.cp=cp;
		this.semester=semester;
		this.note=note;
		this.fehlversuche=fehlversuche;
		this.verbessert=verbessert;
		this.studiengang=studiengang;
		this.typ=typ;
		this.bestanden=bestanden;
		this.datumWannBestanden=datumWannBestanden;
		this.ablaufdatumFuerVerbesserung=ablaufdatumFürVerbesserung;
		this.bearbeitbar=bearbeitbar;
	
	}
	
	DateFormat ablaufdatumFürVerbesserungBerechnen(DateFormat datumWannBestanden) {
		
		DateFormat ablaufdatumFuerVerbesserung=DateFormat.getDateInstance(DateFormat.MEDIUM);
		
		//todo
		
		return ablaufdatumFuerVerbesserung;
	
	}
	
}
