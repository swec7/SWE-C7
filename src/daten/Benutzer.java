package daten;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import exceptions.CSVLeseException;
import exceptions.TypFormatException;

public class Benutzer {

	private Studiengang studiengang;
	private float wunschnote;

	public Benutzer(List<List<String>> csvDaten, Map<String, String[]> htmlDaten) throws CSVLeseException {

		List<Modul> module = new ArrayList<>();

		for (int i = 2; i < csvDaten.size(); i++) {
			List<String> list = csvDaten.get(i);
			try {
				int modulnummer = Integer.parseInt(list.get(0));
				String name = list.get(1);
				int credits = Integer.parseInt(list.get(3));
				float note = 0;
				int versuche = 0;
				Date ablaufdatum = null;
				int semester = Integer.parseInt(list.get(2));
				Typ typ = Typ.parseTyp(list.get(4));

				String[] html = htmlDaten.get(list.get(0));

				try {
					if (html != null) {
						note = Float.parseFloat(html[2].replaceAll(",", "."));
						versuche = Integer.parseInt(html[4]);
					}

				} catch (NumberFormatException e) {
					e.printStackTrace();
					return;
				}

				Modul mod = new Modul(modulnummer, name, credits, note, versuche, ablaufdatum, semester, typ);
				module.add(mod);
			} catch (NumberFormatException | TypFormatException e) {
				throw new CSVLeseException("Ungültiger Wert", i + 1);
			} catch (IndexOutOfBoundsException e) {
				throw new CSVLeseException("Fehlender Wert", i + 1);
			}
		}
		try {
			String name = csvDaten.get(0).get(0);
			int benötigteCredits = Integer.parseInt(csvDaten.get(0).get(2));
			int anzSemester = Integer.parseInt(csvDaten.get(0).get(1));
			int anzWahl = Integer.parseInt(csvDaten.get(0).get(3));
			int anzSoftskill = Integer.parseInt(csvDaten.get(0).get(4));
			int maxVerbleibendeVersuche = 0;
			// int maxVerbleibendeVersuche =
			// Integer.parseInt(csvDaten.get(0).get(5));
			studiengang = new Studiengang(module, name, benötigteCredits, anzSemester, anzWahl, anzSoftskill,
					maxVerbleibendeVersuche);
			wunschnote = 0;
		} catch (NumberFormatException e) {
			throw new CSVLeseException("Ungültiger Wert", 1);
		} catch (IndexOutOfBoundsException e) {
			throw new CSVLeseException("Fehlender Wert", 1);
		}

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
		s += "\nbenötigte Credits: " + studiengang.getBenötigteCredits();
		s += "\nanz. Semester: " + studiengang.getAnzSemester();
		s += "\nanz. Wahlmodule: " + studiengang.getAnzWahl();
		s += "\nanz. Softskill: " + studiengang.getAnzSoftskill();
		s += "\nmax. verbleibende Versuche: " + studiengang.getMaxVerbleibendeVersuche() + "\n\n";

		s += "durchschnitts Note: " + durchschnitsNote() + "\n";
		s += "durchschnitts Plan-Note: " + durchschnitsPlanNote() + "\n\n";

		s += "Modul Nr. |Name                                                        |Credits|Note|Plan  Note|Versuche|Ablaufdatum|Semester|Typ"
				+ "\n";
		s += "----------+------------------------------------------------------------+-------+----+----------+--------+-----------+--------+-----------"
				+ "\n";
		for (int i = 0; i < studiengang.getModuleSize(); i++) {
			Modul m = studiengang.getModul(i);
			s += String.format("%-10s", m.getModulnummer()) + "|";
			s += String.format("%-60s", m.getName().substring(0, Math.min(m.getName().length(), 60))) + "|";
			s += String.format("%-7s", m.getCredits()) + "|";
			s += String.format("%-4s", m.getNote()) + "|";
			s += String.format("%-10s", m.getPlanNote()) + "|";
			s += String.format("%-8s", m.getVersuche()) + "|";
			s += String.format("%-11s", m.getAblaufdatum()) + "|";
			s += String.format("%-8s", m.getSemester()) + "|";
			s += String.format("%-10s", m.getTyp());
			s += "\n";
		}
		return s;
	}
}
