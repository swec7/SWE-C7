package daten;

public class Datum {
	private int tag;
	private int monat;
	private int jahr;

	public Datum(int tag, int monat, int jahr) {
		this.tag = tag;
		this.monat = monat;
		this.jahr = jahr;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getMonat() {
		return monat;
	}

	public void setMonat(int monat) {
		this.monat = monat;
	}

	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

	public static Datum parseDatum(String s) {
		String[] ss = s.split("\\.");
		return new Datum(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]), Integer.parseInt(ss[2]));
	}

	@Override
	public String toString() {
		return String.format("%02d", tag) + "." + String.format("%02d", monat) + "." + jahr;
	}
}
