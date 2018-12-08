package daten;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Benutzer {

	private Studiengang studiengang;

	private int wunschnote;

	public Benutzer(List<List<String>> csvDaten) throws CSVLeseException {

		List<Modul> module = new ArrayList<>();

		for (int i = 2; i < csvDaten.size(); i++) {
			List<String> list = csvDaten.get(i);
			try {
				int modulnummer = Integer.parseInt(list.get(0));
				String name = list.get(1);
				int credits = Integer.parseInt(list.get(3));
				float note = 0;
				int versuche = 0;
				Date ablaufdatum = new Date(0, 0, 0);
				int semester = Integer.parseInt(list.get(2));
				String typ = list.get(4);

				Modul mod = new Modul(modulnummer, name, credits, note, versuche, ablaufdatum, semester, typ);
				module.add(mod);
			} catch (NumberFormatException | IndexOutOfBoundsException e) {
				throw new CSVLeseException("Fehlender oder ungültiger Wert", i + 1);
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
		} catch (NumberFormatException | IndexOutOfBoundsException e) {
			throw new CSVLeseException("Fehlender oder ungültiger Wert", 1);
		}

	}

	public Studiengang getStudiengang() {
		return studiengang;
	}

	public int getWunschnote() {
		return wunschnote;
	}

	@Override
	public String toString() {
		String string = "wunschnote: " + wunschnote + "\n";
		string = string + "studiengang: \n" + studiengang.toString();
		return string;
	}
}
