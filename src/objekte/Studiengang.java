package objekte;

public class Studiengang {

	String bezeichnung;
	int semester;
	int cp;
	int wahlmodule;
	int softskill;
	int verbesserungsVersuche;
	
	
	public Studiengang(){
		
	}
	
	Studiengang(String bezeichnung, int semester, int cp, int wahlmodule, int softskill,int verbesserungsVersuche){
		this.bezeichnung=bezeichnung;
		this.semester=semester;
		this.cp=cp;
		this.wahlmodule=wahlmodule;
		this.softskill=softskill;
		this.verbesserungsVersuche=verbesserungsVersuche;		
	}
	
	
	
}
