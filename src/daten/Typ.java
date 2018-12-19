package daten;



/**
 * Der Type eines Moduls (Wahl, Pfilicht oder Softskill).
 *
 */
public enum Typ {
	PFLICHT("P"), WAHL("W"), SOFTSKILL("S");

	private Typ(String s) {
		symbol = s;
	}

	private String symbol;

	
}