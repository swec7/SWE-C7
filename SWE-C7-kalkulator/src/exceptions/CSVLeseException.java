package exceptions;

/**
 * wird geworfen wenn beim einlesen oder parsen einer csv datei ein fehler
 * auftritt.
 *
 */
public class CSVLeseException extends Exception {
	private int zeile;

	public CSVLeseException(String message) {
		super(message);
		zeile = -1;
	}

	@Override
	public String getMessage() {
		if (zeile >= 0) {
			return super.getMessage() + " bei Zeile " + (zeile + 1);
		}
		return super.getMessage();
	}

	public void beiZeile(int zeile) {
		this.zeile = zeile;
	}
}
