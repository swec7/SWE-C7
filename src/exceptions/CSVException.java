package exceptions;

public class CSVException extends Exception {
	private int zeile;

	public CSVException(String message, int zeile) {
		super(message);
		this.zeile = zeile;
	}

	public int getZeile() {
		return zeile;
	}

	@Override
	public String getMessage() {
		return super.getMessage() + " bei zeile " + zeile + ".";
	}
}
