package daten;

import java.util.Date;
import java.util.List;

import exceptions.CSVLeseException;
import exceptions.HTMLLeseException;
import exceptions.TypFormatException;

/**
 * Speichert alle daten die über ein modul verfügbar sind. nur die wunschnote
 * ist veränderbar.
 *
 */
public class Modul {

	private int modulnummer;
	private String name;
	private int credits;
	private float note;
	private int versuche;
	private Date ablaufdatum;
	private Date pruefungsDatum;
	private int semester;
	private Typ typ;
	private float planNote;

	/**
	 * initialisiert das modul mit den werten aus einer csv zeile. um daten aus
	 * einer html datein hinzuzufügen muss loadQIS() benutzt werden
	 * 
	 * @param csvZeile
	 *            die geparste zeile aus der csv datei
	 * @throws CSVLeseException
	 *             wenn beim lesen der CSV daten ein Fehler auftritt.
	 */
	public Modul(List<String> csvZeile) throws CSVLeseException {
		this(0, null, 0, 0, 0, null, null, 0, null, 0);
		try {
			modulnummer = Integer.parseInt(csvZeile.get(0));
			name = csvZeile.get(1);
			credits = Integer.parseInt(csvZeile.get(3));
			semester = Integer.parseInt(csvZeile.get(2));
			typ = Typ.parseTyp(csvZeile.get(4));
		} catch (NumberFormatException | TypFormatException e) {
			throw new CSVLeseException("Ungültiger Wert (" + e.getMessage() + ")");
		} catch (IndexOutOfBoundsException e) {
			throw new CSVLeseException("Fehlender Wert");
		}
	}

	/**
	 * initialisiert das modul mit den gegebenen werten.
	 */
	public Modul(int modulnummer, String name, int credits, float note, int versuche, Date ablaufdatum,
			Date pruefungsDatum, int semester, Typ typ, float planNote) {
		this.modulnummer = modulnummer;
		this.name = name;
		this.credits = credits;
		this.note = note;
		this.versuche = versuche;
		this.ablaufdatum = ablaufdatum;
		this.pruefungsDatum = pruefungsDatum;
		this.semester = semester;
		this.typ = typ;
		this.planNote = planNote;
	}

	/**
	 * läd die daten aus einer html datei
	 * 
	 * @param htmlZeile
	 *            die geparste zeile aus der html datei (eine zeile aus der
	 *            tabelle)
	 * @throws HTMLLeseException
	 *             wenn beim lesen der HTML daten ein Fehler auftritt.
	 */
	public void loadQIS(String[] htmlZeile) throws HTMLLeseException {
		if (htmlZeile == null) {
			return;
		}
		try {
			note = Float.parseFloat(htmlZeile[2].replaceAll(",", "."));
			versuche = Integer.parseInt(htmlZeile[4]);
		} catch (NumberFormatException e) {
			for (int j = 0; j < htmlZeile.length; j++) {
				System.out.println(htmlZeile[j]);
			}
			throw new HTMLLeseException("Ungültiger Wert (" + e.getMessage() + ")");
		}
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

	public Date getPruefungsDatum() {
		return pruefungsDatum;
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
