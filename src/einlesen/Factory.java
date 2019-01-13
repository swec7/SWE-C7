package einlesen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import daten.Benutzer;
import daten.Modul;
import daten.Studiengang;
import daten.Typ;
import exceptions.CSVLeseException;
import exceptions.HTMLLeseException;
import exceptions.TypFormatException;

public class Factory {
	public static Benutzer loadBenutzer() throws CSVLeseException, IOException, HTMLLeseException {

		HTMLDaten htmlDaten = HTMLParser.loadHTML("Fh Aachen.html");
		List<List<String>> studiengaengeCSV = CSVReader.loadCsv("Studiengaenge.csv");
		List<List<String>> moduleCSV = null;
		int i = 1;
		for (; i < studiengaengeCSV.size(); i++) {

			if (htmlDaten.getStudiengang().equals(studiengaengeCSV.get(i).get(0))) {
				moduleCSV = CSVReader.loadCsv(studiengaengeCSV.get(i).get(6) + ".csv");
				break;
			}
		}
		return new Benutzer(Factory.buildStudiengang(studiengaengeCSV.get(i), moduleCSV, htmlDaten.getMap()), 0, 0);
	}

	public static Modul buildModul(List<String> csvZeile) throws CSVLeseException {
		int modulnummer = 0;
		String name = null;
		int credits = 0, semester = 0;
		Typ typ = null;
		try {
			// System.out.println(csvZeile.get(0));
			// System.out.println(csvZeile.get(1));
			// System.out.println(csvZeile.get(2));
			// System.out.println(csvZeile.get(3));
			// System.out.println(csvZeile.get(4));

			// System.out.println("[b0]DEBUG ");
			modulnummer = Integer.parseInt(csvZeile.get(0));
			// System.out.println("[b1]DEBUG");
			name = csvZeile.get(1);
			// System.out.println("[b2]DEBUG");
			credits = Integer.parseInt(csvZeile.get(3));
			// System.out.println("[b3]DEBUG");
			semester = Integer.parseInt(csvZeile.get(2));
			// System.out.println("[b4]DEBUG");
			typ = Typ.parseTyp(csvZeile.get(4));
		} catch (NumberFormatException | TypFormatException e) {
			throw new CSVLeseException("Ungültiger Wert");
		} catch (IndexOutOfBoundsException e) {
			throw new CSVLeseException("Fehlender Wert");
		}
		return new Modul(modulnummer, name, credits, 0, 0, null, null, semester, typ, 0);
	}

	public static Studiengang buildStudiengang(List<String> studiengangDaten, List<List<String>> csvDaten,
			Map<String, String[]> htmlDaten) throws CSVLeseException, HTMLLeseException {

		// System.out.println("[0]DEBUG");

		Studiengang gang = new Studiengang(null, null, 0, 0, 0, 0, 0);
		List<Modul> module = new ArrayList<>();
		for (int i = 1; i < csvDaten.size(); i++) {
			// System.out.println("[FOR S]DEBUG");
			Modul m = buildModul(csvDaten.get(i));

			// System.out.println(htmlDaten.get(Integer.toString(m.getModulnummer())));
			// System.out.println("[FOR M]DEBUG");
			m.loadQIS(htmlDaten.get(Integer.toString(m.getModulnummer())));

			module.add(m);
			// System.out.println("[FOR E]DEBUG");
		}
		String name = null;
		int benoetigteCredits = 0, anzSemester = 0, anzWahl = 0, anzSoftskill = 0, maxVerbleibendeVersuche = 0;
		// System.out.println("[1]DEBUG");
		try {
			// System.out.println(studiengangDaten.get(0)+ " " +
			// studiengangDaten.get(2)+ " " + studiengangDaten.get(1)+ " " +
			// studiengangDaten.get(3)+ " " + studiengangDaten.get(4));
			// System.out.println(studiengangDaten.get(5)+ " " +
			// studiengangDaten.get(6)+ " " + studiengangDaten.get(7)+ " " +
			// studiengangDaten.get(8)+ " " + studiengangDaten.get(9));
			// System.out.println("[T0]DEBUG");
			name = studiengangDaten.get(0);
			// System.out.println("[T1]DEBUG");
			// System.out.println(studiengangDaten.get(2));
			benoetigteCredits = Integer.parseInt(studiengangDaten.get(2));
			// System.out.println("[T2]DEBUG");
			anzSemester = Integer.parseInt(studiengangDaten.get(1));
			// System.out.println("[T3]DEBUG");
			anzWahl = Integer.parseInt(studiengangDaten.get(3));
			// System.out.println("[T4]DEBUG");
			anzSoftskill = Integer.parseInt(studiengangDaten.get(4));
			// System.out.println("[T5]DEBUG");
			maxVerbleibendeVersuche = 0;
		} catch (NumberFormatException e) {
			throw new CSVLeseException("Ungültiger Wert");
			// throw new CSVLeseException(studiengangDaten.get(0)+ " " +
			// studiengangDaten.get(2)+ " " + studiengangDaten.get(1)+ " " +
			// studiengangDaten.get(3)+ " " + studiengangDaten.get(4));
		} catch (IndexOutOfBoundsException e) {
			throw new CSVLeseException("Fehlender Wert");
		}
		gang = new Studiengang(module, name, benoetigteCredits, anzSemester, anzWahl, anzSoftskill,
				maxVerbleibendeVersuche);
		return gang;
	}
}
