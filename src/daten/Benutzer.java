package daten;

import java.io.IOException;
import java.util.List;

import einlesen.CSVReader;
import einlesen.HTMLDaten;
import einlesen.HTMLParser;
import exceptions.CSVLeseException;
import exceptions.HTMLLeseException;

public class Benutzer {

	private Studiengang studiengang;
	private float wunschnote;

	public Benutzer(String htmlPath, String csvPath) throws CSVLeseException, HTMLLeseException, IOException {
		HTMLDaten htmlDaten = HTMLParser.loadHTML(htmlPath);
		List<List<String>> studiengaengeCSV = CSVReader.loadCsv(csvPath);
		List<List<String>> moduleCSV = null;
		int i = 0;
		for (i = 1; i < studiengaengeCSV.size(); i++) {
			if (htmlDaten.getStudiengang().equals(studiengaengeCSV.get(i).get(0))) {
				break;
			}
		}
		moduleCSV = CSVReader.loadCsv(studiengaengeCSV.get(i).get(6) + ".csv");

		if (moduleCSV == null) {
			throw new CSVLeseException("Es wurde kein Eintrag für " + htmlDaten.getStudiengang() + " gefunden.");
		}

		studiengang = new Studiengang(studiengaengeCSV.get(i), moduleCSV, htmlDaten.getMap());
		wunschnote = 0;
	}

	public float durchschnitsNote() {
		float summe = 0;
		float credits = 0;
		for (Modul m : studiengang.getModule()) {
			if (m.isGeschrieben() && m.getTyp() != Typ.SOFTSKILL) {
				summe += m.getCredits() * m.getNote();
				credits += m.getCredits();
			}
		}
		if (credits == 0) {
			return 0;
		}
		return summe / credits;
	}

	public float durchschnitsPlanNote() {
		float summe = 0;
		float credits = 0;
		for (Modul m : studiengang.getModule()) {
			if (m.isGeschrieben() && m.getTyp() != Typ.SOFTSKILL) {
				summe += m.getCredits() * m.getPlanNote();
				credits += m.getCredits();
			}
		}
		if (credits == 0) {
			return 0;
		}
		return summe / credits;
	}

	public Studiengang getStudiengang() {
		return studiengang;
	}

	public void setWunschNote(float wunschnote) {
		this.wunschnote = wunschnote;
	}

	public float getWunschnote() {
		return wunschnote;
	}

	@Override
	public String toString() {
		String s = "<<Benutzer>>\n";

		s += "Studiengang: " + studiengang.getName();
		s += "\nwunschnote: " + wunschnote;
		s += "\nbenoetigte Credits: " + studiengang.getBenoetigteCredits();
		s += "\nanz. Semester: " + studiengang.getAnzSemester();
		s += "\nanz. Wahlmodule: " + studiengang.getAnzWahl();
		s += "\nanz. Softskill: " + studiengang.getAnzSoftskill();
		s += "\nmax. verbleibende Versuche: " + studiengang.getMaxVerbleibendeVersuche() + "\n\n";

		s += "durchschnitts Note: " + durchschnitsNote() + "\n";
		s += "durchschnitts Plan-Note: " + durchschnitsPlanNote() + "\n\n";

		s += "Modul Nr. |Name                                                        |Credits|Note|Plan  Note|Versuche|Pruefungsdatum|Ablaufdatum|Semester|Typ"
				+ "\n";
		s += "----------+------------------------------------------------------------+-------+----+----------+--------+--------------+--------+-----------"
				+ "\n";
		for (int i = 0; i < studiengang.getModuleSize(); i++) {
			Modul m = studiengang.getModul(i);
			s += String.format("%-10s", m.getModulnummer()) + "|";
			s += String.format("%-60s", m.getName().substring(0, Math.min(m.getName().length(), 60))) + "|";
			s += String.format("%-7s", m.getCredits()) + "|";
			s += String.format("%-4s", m.getNote()) + "|";
			s += String.format("%-10s", m.getPlanNote()) + "|";
			s += String.format("%-8s", m.getVersuche()) + "|";
			s += String.format("%-14s", m.getPruefungsDatum()) + "|";
			s += String.format("%-11s", m.getAblaufdatum()) + "|";
			s += String.format("%-8s", m.getSemester()) + "|";
			s += String.format("%-10s", m.getTyp());
			s += "\n";
		}
		return s;
	}
}
