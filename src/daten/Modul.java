package daten;

import java.util.Date;

public class Modul {

	private int modulnummer;
	private String name;
	private int credits;
	private float note;
	private int versuche;
	private Date ablaufdatum;
	private int semester;
	private Typ typ;
	private float planNote;

	public Modul(int modulnummer, String name, int credits, float note, int versuche, Date ablaufdatum, int semester,
			Typ typ) {
		this.modulnummer = modulnummer;
		this.name = name;
		this.credits = credits;
		this.note = note;
		this.versuche = versuche;
		this.ablaufdatum = ablaufdatum;
		this.semester = semester;
		this.typ = typ;
		this.planNote = 0;
	}

	public int getModulnummer() {
		return modulnummer;
	}

	public String getName() {
		return name;
	}

	public int getCredits() {
		return credits;
	}

	public float getNote() {
		return note;
	}

	public int getVersuche() {
		return versuche;
	}

	public Date getAblaufdatum() {
		return ablaufdatum;
	}

	public int getSemester() {
		return semester;
	}

	public Typ getTyp() {
		return typ;
	}

	public void setPlanNote(float planNote) {
		this.planNote = planNote;
	}

	public float getPlanNote() {
		return planNote;
	}

	@Override
	public String toString() {
		return "modulnummer: " + modulnummer + " name: " + name + " credits: " + credits + " note: " + note
				+ " versuche: " + versuche + " ablaufdatum: " + ablaufdatum + " semseter: " + semester + " typ: " + typ;
	}

	public boolean isGeschrieben() {
		return note != 0.0f;
	}
}
