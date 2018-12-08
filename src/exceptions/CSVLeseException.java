package exceptions;

public class CSVLeseException extends CSVException {
	public CSVLeseException(String message, int zeile) {
		super(message, zeile);
	}
}
