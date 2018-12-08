package exceptions;

public class CSVFormattierungsException extends CSVException {

	public CSVFormattierungsException(String message, int zeile) {
		super(message, zeile);
	}
}
