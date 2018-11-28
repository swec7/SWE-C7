package daten;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.CSVLeseException;

public class Studiengang {
	private List<Modul> module;

	private String name;
	private int benoetigteCredits;
	private int anzSemester;
	private int anzWahl;
	private int anzSoftskill;
	private int maxVerbleibendeVersuche;

	public Studiengang(List<List<String>> csvDaten, Map<String, String[]> htmlDaten) throws CSVLeseException {
		this(null, null, 0, 0, 0, 0, 0);
		module = new ArrayList<>();
		for (int i = 2; i < csvDaten.size(); i++) {
			Modul m = new Modul(csvDaten.get(i));
			m.loadQIS(htmlDaten.get(Integer.toString(m.getModulnummer())));
			module.add(m);
		}

		try {
			name = csvDaten.get(0).get(0);
			benoetigteCredits = Integer.parseInt(csvDaten.get(0).get(2));
			anzSemester = Integer.parseInt(csvDaten.get(0).get(1));
			anzWahl = Integer.parseInt(csvDaten.get(0).get(3));
			anzSoftskill = Integer.parseInt(csvDaten.get(0).get(4));
			maxVerbleibendeVersuche = 0;
		} catch (NumberFormatException e) {
			throw new CSVLeseException("Ung�ltiger Wert");
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