package exceptions;

public class CSVFormattierungsException extends Exception {
	private int zeile;

	public CSVFormattierungsException(String message, int zeile) {
		super(message);
		this.zeile = zeile;
	}

	@Override
	public String getMessage() {
		return super.getMessage() + " bei Zeile " + zeile;
	}

	public int getZeile() {
		return zeile;
	}
}
