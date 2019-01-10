package daten;

import java.util.ArrayList;
import java.util.List;

public class Studiengang {
	private List<Modul> module;

	private String name;
	private int benoetigteCredits;
	private int anzSemester;
	private int anzWahl;
	private int anzSoftskill;
	private int maxVerbleibendeVersuche;

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

	public List<Modul> getBModule() {
		List<Modul> l = new ArrayList<>();
		for (Modul modul : module) {
			if (modul.getNote() <= 4.0 && modul.getNote() > 0) {
				l.add(modul);
			}
		}
		return l;
	}

	public List<Modul> getNModule() {
		List<Modul> l = new ArrayList<>();
		for (Modul modul : module) {
			if (modul.getNote() > 4.0 && modul.getNote() > 0) {
				l.add(modul);
			}
		}
		return l;
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
