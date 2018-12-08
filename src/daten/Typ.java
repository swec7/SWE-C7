package daten;

import exceptions.TypFormatException;

public enum Typ {
	PFLICHT("P"), WAHL("W"), SOFTSKILL("S");

	private Typ(String s) {
		symbol = s;
	}

	private String symbol;

	public static Typ parseTyp(String s) throws TypFormatException {
		for (Typ t : values()) {
			if (t.symbol.equals(s)) {
				return t;
			}
		}
		throw new TypFormatException();
	}

}