package daten;

import java.util.Date;

public class Modul {
	private String name;
	private int credits;
	private float note;
	private int versuche;
	private Date ablaufdatum;
	private int semester;

	public boolean hatQisDaten() {
		return false;
	}
}
