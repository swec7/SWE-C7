package daten;

import java.util.List;

public class Studiengang {
	private List<Modul> module;

	private String name;
	private int benötigteCredits;
	private int anzSemester;
	private int anzWahl;
	private int anzSoftskill;
	private int maxVerbleibendeVersuche;

	public Studiengang(List<Modul> module, String name, int benötigteCredits, int anzSemester, int anzWahl,
			int anzSoftskill, int maxVerbleibendeVersuche) {
		this.module = module;
		this.name = name;
		this.benötigteCredits = benötigteCredits;
		this.anzSemester = anzSemester;
		this.anzWahl = anzWahl;
		this.anzSoftskill = anzSoftskill;
		this.maxVerbleibendeVersuche = maxVerbleibendeVersuche;
	}

	public float durchschnittsNote() {
		return 0;
	}

	public List<Modul> getModule() {
		return module;
	}

	public String getName() {
		return name;
	}

	public int getBenötigteCredits() {
		return benötigteCredits;
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

	@Override
	public String toString() {
		String s = "name: " + name + " benötigteCredits: " + benötigteCredits + " anzSemester: " + anzSemester
				+ " anzWahl: " + anzWahl + " anzSoftskill: " + anzSoftskill + " maxVerbleibendeVersuche: "
				+ maxVerbleibendeVersuche + "\n";
		for (Modul modul : module) {
			s = s + modul.toString() + "\n";
		}
		return s;
	}
}
