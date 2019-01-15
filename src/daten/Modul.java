package daten;

import exceptions.HTMLLeseException;

/**
 * Speichert alle daten die �ber ein modul verf�gbar sind. nur die wunschnote
 * ist ver�nderbar.
 */
public class Modul {

	private int modulnummer;
	private String name;
	private int credits;
	private float note;
	private int versuche;
	private Datum ablaufdatum;
	private Datum pruefungsDatum;
	private int semester;
	private Typ typ;
	private float planNote;
	private float verbesserungspotenzial;

	public Modul(int modulnummer, String name, int credits, float note, int versuche, Datum ablaufdatum,
			Datum pruefungsDatum, int semester, Typ typ, float planNote) {
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
	 * l�d die daten aus einer html datei
	 * 
	 * @param htmlZeile
	 *            die geparste zeile aus der html datei (eine zeile aus der
	 *            tabelle)
	 * @throws HTMLLeseException
	 *             wenn beim lesen der HTML daten ein Fehler auftritt.
	 */
	public void loadQIS(String[] htmlZeile) throws HTMLLeseException {
		// System.out.println("[00]DEBUG");
		if (htmlZeile == null) {
			return;
		}
		// System.out.println("[01]DEBUG");
		// for (String string : htmlZeile) {
		// System.out.print(string + "|");
		// }
		// System.out.println();
		try {
			// System.out.println("[02]DEBUG");
			if (htmlZeile[3].isEmpty()) {
				note = 0;
			} else {
				note = Float.parseFloat(htmlZeile[3].replaceAll(",", "."));
			}
			// System.out.println("[03]DEBUG");
			if(note != 0) {versuche = (int) Float.parseFloat(htmlZeile[6].replaceAll(",", "."));}
			pruefungsDatum = Datum.parseDatum(htmlZeile[2]);
			if(note != 0) {ablaufdatum = new Datum(pruefungsDatum.getTag(), pruefungsDatum.getMonat(), pruefungsDatum.getJahr() + 1);}
			this.verbesserungspotenzial = (this.note - 1.0f) * this.credits;
			// System.out.println("[04]DEBUG");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new HTMLLeseException(
					"Ung�ltiger Wert (" + e.getMessage() + ")\"" + note + "\"+\"" + versuche + "\"");
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

	public Datum getAblaufdatum() {
		return ablaufdatum;
	}

	public float getVerbesserungspotenzial() {
		return verbesserungspotenzial;
	}

	public Datum getPruefungsDatum() {
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
