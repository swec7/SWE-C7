package daten;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.CSVLeseException;
import exceptions.HTMLLeseException;

public class Studiengang {
	private List<Modul> module;

	private String name;
	private int benoetigteCredits;
	private int anzSemester;
	private int anzWahl;
	private int anzSoftskill;
	private int maxVerbleibendeVersuche;

	/**
	 * 
	 * @param csv
	 * @param csvDaten
	 * @param htmlDaten
	 * @throws CSVLeseException
	 * @throws HTMLLeseException
	 */
	public Studiengang(List<String> csv, List<List<String>> csvDaten, List<List<String>> csvWahlSoft, Map<String, String[]> htmlDaten)
			throws CSVLeseException, HTMLLeseException {
		this(null, null, 0, 0, 0, 0, 0);
		module = new ArrayList<>();
		for (int i = 1; i < csvDaten.size(); i++) {
			try {
				Modul m = new Modul(csvDaten.get(i));
				m.loadQIS(htmlDaten.get(Integer.toString(m.getModulnummer())));
				module.add(m);
			} catch (HTMLLeseException e) {
				throw e;
			} catch (CSVLeseException e) {
				e.beiZeile(i);
				throw e;
			}
		}
		for (int i = 1; i < csvWahlSoft.size(); i++) {
			try {
				Modul m = new Modul(csvWahlSoft.get(i));
				m.loadQIS(htmlDaten.get(Integer.toString(m.getModulnummer())));
				module.add(m);
			} catch (HTMLLeseException e) {
				throw e;
			} catch (CSVLeseException e) {
				e.beiZeile(i);
				throw e;
			}
		}

		try {
			name = csv.get(0);
			benoetigteCredits = Integer.parseInt(csv.get(2));
			anzSemester = Integer.parseInt(csv.get(1));
			anzWahl = Integer.parseInt(csv.get(3));
			anzSoftskill = Integer.parseInt(csv.get(4));
			maxVerbleibendeVersuche = 0;
		} catch (NumberFormatException e) {
			throw new CSVLeseException("Ungültiger Wert");
		} catch (IndexOutOfBoundsException e) {
			throw new CSVLeseException("Fehlender Wert");
		}

	}

	public Studiengang(List<Modul> module, String name, int benoetigteCredits, int anzSemester, int anzWahl,
			int anzSoftskill, int maxVerbleibendeVersuche) {
		this.module = module;
		this.name = name;
		this.benoetigteCredits = benoetigteCredits;
		this.anzSemester = anzSemester;
		this.anzWahl = anzWahl;
		this.anzSoftskill = anzSoftskill;
		this.maxVerbleibendeVersuche = maxVerbleibendeVersuche;
	}

	public float durchschnittsNote() {
		return 0;
	}

	public Modul getModul(int i) {
		return module.get(i);
	}

	public int getModuleSize() {
		return module.size();
	}

	public List<Modul> getModule() {
		return module;
	}

	public String getName() {
		return name;
	}

	public int getBenoetigteCredits() {
		return benoetigteCredits;
	}

	public int getAnzSemester() {
		return anzSemester;
	}

	public int getAnzWahl() {
		return anzWahl;
	}

	public int getAnzSoftskill() {
		return anzSoftskill;
	}

	public int getMaxVerbleibendeVersuche() {
		return maxVerbleibendeVersuche;
	}

}
