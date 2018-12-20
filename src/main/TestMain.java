package main;

import java.io.IOException;

import daten.Benutzer;
import exceptions.CSVLeseException;
import exceptions.HTMLLeseException;

public class TestMain {
	public static final String HTML_PATH = "FH Aachen.html";
	public static final String CSV_PATH = "studiengaenge.csv";

	public static void main(String[] args) throws IOException, CSVLeseException, HTMLLeseException {
		Benutzer benutzer = new Benutzer(HTML_PATH, CSV_PATH);
		System.out.println(benutzer);
		System.out.println(benutzer.wunschnotenrechner(3.3f));
	}
}
