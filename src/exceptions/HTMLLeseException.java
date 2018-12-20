package exceptions;

/**
 * wird geworfen wenn beim einlesen oder parsen einer html datei ein fehler
 * auftritt.
 *
 */
public class HTMLLeseException extends Exception {

	public HTMLLeseException(String message) {
		super(message);
	}

}
