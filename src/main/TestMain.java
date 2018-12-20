package main;

import java.io.IOException;

<<<<<<< HEAD
import einlesen.Factory;
import exceptions.CSVFormattierungsException;
=======
import daten.Benutzer;
>>>>>>> refs/remotes/origin/prototype
import exceptions.CSVLeseException;
import exceptions.HTMLLeseException;

public class TestMain {
	public static final String HTML_PATH = "FHAachen.html";
	public static final String CSV_PATH = "studiengaenge.csv";

<<<<<<< HEAD
	public static void main(String[] args) throws IOException, CSVFormattierungsException, CSVLeseException {
		System.out.println(Factory.loadBenutzer());
		System.out.println();
=======
	public static void main(String[] args) throws IOException, CSVLeseException, HTMLLeseException {
		System.out.println(new Benutzer(HTML_PATH, CSV_PATH));
>>>>>>> refs/remotes/origin/prototype
	}
}
